package moe.quill.nadare.cooking

import com.comphenix.protocol.ProtocolLibrary
import moe.quill.nadare.bukkitcommon.BukkitLambda
import moe.quill.nadare.cooking.core.CampfireManager
import moe.quill.nadare.cooking.core.PlayerListener
import moe.quill.nadare.cooking.events.CustomEventListener
import moe.quill.nadare.cooking.temperature.TempHandler
import moe.quill.nadare.cooking.temperature.TempPacketListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class Cooking : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        val campfireManager = CampfireManager(this)

        server.pluginManager.registerEvents(CustomEventListener(), this)
        server.pluginManager.registerEvents(PlayerListener(campfireManager), this)

        val tempHandler = TempHandler() //TODO: Modify temps here in various places :thumbs_up:

        val uuid = UUID.randomUUID()
        ProtocolLibrary.getProtocolManager().addPacketListener(TempPacketListener(this, tempHandler))

        BukkitLambda{
            Bukkit.getOnlinePlayers().forEach{
                tempHandler.modifyFreezeTicks(it.uniqueId, 5)
            }
        }.runTaskTimer(this, 0, 5)
    }

    override fun onDisable() {
        // Plugin disable logic
    }
}
