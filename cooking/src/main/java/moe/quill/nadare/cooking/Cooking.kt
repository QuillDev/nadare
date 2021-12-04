package moe.quill.nadare.cooking

import com.comphenix.protocol.ProtocolLibrary
import moe.quill.nadare.bukkitcommon.BukkitLambda
import moe.quill.nadare.bukkitcommon.ModuleBase
import moe.quill.nadare.cooking.core.CampfireManager
import moe.quill.nadare.cooking.core.PlayerListener
import moe.quill.nadare.cooking.events.CustomEventListener
import moe.quill.nadare.cooking.temperature.TempHandler
import moe.quill.nadare.cooking.temperature.TempPacketListener
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class Cooking : JavaPlugin(), ModuleBase {
    override val plugin = this

    override fun onEnable() {
        // Plugin startup logic
        val campfireManager = CampfireManager(this)

        registerListeners(
            CustomEventListener(),
            PlayerListener(this, campfireManager)
        )

        val tempHandler = TempHandler() //TODO: Modify temps here in various places :thumbs_up:

        val uuid = UUID.randomUUID()
        ProtocolLibrary.getProtocolManager().addPacketListener(TempPacketListener(this, tempHandler))

        BukkitLambda{
            Bukkit.getOnlinePlayers().forEach{
                tempHandler.modifyFreezeTicks(it.uniqueId, 1)
            }
        }.runTaskTimer(this, 0, 10)
    }

    override fun onDisable() {
        // Plugin disable logic
    }
}
