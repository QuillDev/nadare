package moe.quill.nadare.bukkitcommon.commands

import org.bukkit.command.CommandSender

interface CommandBase {
    fun execute(sender: CommandSender, args: List<String>)
    fun tabComplete(sender: CommandSender, args: List<String>): MutableList<String>

    fun tabFilter(target: String, options: Collection<String>): MutableList<String> {
        return options.filter { it.lowercase().contains(target.lowercase()) }.toMutableList()
    }
}