package moe.quill.nadare.bukkitcommon.lib

import moe.quill.nadare.bukkitcommon.lib.commands.Command
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

interface ModuleBase {
    val plugin: Plugin

    fun registerListeners(vararg listeners: Listener) {
        listeners.forEach { plugin.server.pluginManager.registerEvents(it, plugin) }
    }

    fun registerCommand(vararg commands: Command) {
        commands.forEach { plugin.server.commandMap.register(plugin.name, it) }
    }
}