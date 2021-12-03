package moe.quill.nadare.everwinter;

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class EverWinter : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("anything?")
    }

    override fun onDisable() {
        super.onDisable()
    }
}
