package moe.quill.nadare.bukkitcommon

import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

interface ModuleBase {
    val plugin: Plugin

    fun registerListeners(vararg listeners: Listener) {
        listeners.forEach { plugin.server.pluginManager.registerEvents(it, plugin) }
    }
}