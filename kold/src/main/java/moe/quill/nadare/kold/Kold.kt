package moe.quill.nadare.kold;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

class Kold : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Loading Kotlin Deps");
    }
}
