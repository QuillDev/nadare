package moe.quill.nadare.cooking

import com.comphenix.protocol.ProtocolLibrary
import moe.quill.nadare.attributes.attributes.registry.AttributeRegistry
import moe.quill.nadare.bukkitcommon.BukkitCommon
import moe.quill.nadare.bukkitcommon.lib.BukkitLambda
import moe.quill.nadare.bukkitcommon.lib.ModuleBase
import moe.quill.nadare.bukkitcommon.lib.keys.KeyManager
import moe.quill.nadare.cooking.core.CampfireManager
import moe.quill.nadare.cooking.core.PlayerListener
import moe.quill.nadare.cooking.events.CustomEventListener
import moe.quill.nadare.cooking.food.FoodItemGenerator
import moe.quill.nadare.cooking.food.FoodListeners
import moe.quill.nadare.cooking.temperature.PlayerRespawnListener
import moe.quill.nadare.cooking.temperature.TempHandler
import moe.quill.nadare.cooking.temperature.WeatherChannel
import moe.quill.nadare.cooking.temperature.TempPacketListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class Cooking : JavaPlugin(), ModuleBase {
    override val plugin = this
    lateinit var registry: AttributeRegistry

    override fun onEnable() {
        // Plugin startup logic
        this.registry = server.servicesManager.load(AttributeRegistry::class.java) ?: return
        registry.register(FoodListeners(this))

        val keyManager = server.servicesManager.load(KeyManager::class.java) ?: run {
            logger.severe("Could not load KeyManager from BukkitCommon!")
        }

        val foodItemGenerator = FoodItemGenerator(this, registry)

        val campfireManager = CampfireManager(foodItemGenerator)
        val tempHandler = TempHandler(campfireManager)
        val weatherChannel = WeatherChannel()
        val tempPacketListener = TempPacketListener(this, tempHandler)

        registerListeners(
            CustomEventListener(),
            PlayerListener(this, campfireManager),
            PlayerRespawnListener(tempHandler, tempPacketListener),
        )
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
