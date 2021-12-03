package moe.quill.nadare.everwinter;

import moe.quill.nadare.entries.DynamicEntry
import moe.quill.nadare.entries.StaticEntry
import moe.quill.nadare.holograms.DynamicHologram
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.plugin.java.JavaPlugin

class EverWinter : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic

        Bukkit.getWorlds().first()?.spawnLocation?.let {
            val hologram = DynamicHologram(this, it, interval = 1)
            hologram.addEntry(StaticEntry(Component.text("Press [Right] to do some shit")))
            hologram.addEntry(DynamicEntry { Component.text(System.currentTimeMillis()) })
        }
    }

    override fun onDisable() {
        super.onDisable()
    }
}
