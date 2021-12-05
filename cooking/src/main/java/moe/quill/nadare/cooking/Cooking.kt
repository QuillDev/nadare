package moe.quill.nadare.cooking

import com.comphenix.protocol.ProtocolLibrary
import moe.quill.nadare.attributes.attributes.AttributeRegistry
import moe.quill.nadare.attributes.test.TestAttributeListener
import moe.quill.nadare.bukkitcommon.BukkitLambda
import moe.quill.nadare.bukkitcommon.ModuleBase
import moe.quill.nadare.cooking.core.CampfireManager
import moe.quill.nadare.cooking.core.PlayerListener
import moe.quill.nadare.cooking.events.CustomEventListener
import moe.quill.nadare.cooking.food.FoodListeners
import moe.quill.nadare.cooking.food.KeyManager
import moe.quill.nadare.cooking.temperature.PlayerRespawnListener
import moe.quill.nadare.cooking.temperature.TempHandler
import moe.quill.nadare.cooking.temperature.WeatherChannel
import moe.quill.nadare.cooking.temperature.TempPacketListener
import org.bukkit.Bukkit
import org.bukkit.plugin.ServicePriority
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class Cooking : JavaPlugin(), ModuleBase {
    override val plugin = this

    override fun onEnable() {
        // Plugin startup logic
        val campfireManager = CampfireManager(this)
        val tempHandler = TempHandler(campfireManager)
        val weatherChannel = WeatherChannel()
        val tempPacketListener = TempPacketListener(this, tempHandler)

        registerListeners(
            CustomEventListener(),
            PlayerListener(this, campfireManager),
            PlayerRespawnListener(tempHandler, tempPacketListener),
        )

        val registry = AttributeRegistry(this)
        server.servicesManager.register(AttributeRegistry::class.java, registry, this, ServicePriority.Highest)

        registry.register(FoodListeners(KeyManager(this)))

        val uuid = UUID.randomUUID()
        ProtocolLibrary.getProtocolManager().addPacketListener(tempPacketListener)

        BukkitLambda{
            Bukkit.getOnlinePlayers().forEach{
                tempHandler.modifyFreezeTicks(it.uniqueId, weatherChannel.getFreezeDelta(it.uniqueId))
            }
        }.runTaskTimer(this, 0, 10)
    }

    override fun onDisable() {
        // Plugin disable logic
    }
}
