package moe.quill.nadare.cooking;

import moe.quill.nadare.cooking.core.CampfireManager
import moe.quill.nadare.cooking.events.CustomEventListener
import moe.quill.nadare.cooking.core.PlayerListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin;

public final class Cooking : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        val campfireManager = CampfireManager(this)

        Bukkit.getServer().pluginManager.registerEvents(CustomEventListener(), this)
        Bukkit.getServer().pluginManager.registerEvents(PlayerListener(campfireManager), this)
    }

    override fun onDisable() {
        // Plugin disable logic
    }
}
