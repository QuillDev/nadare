package moe.quill.nadare.everwinter

import moe.quill.nadare.bukkitcommon.lib.commands.SubCommand
import org.bukkit.command.CommandSender

class SecondCommand : SubCommand {
    override val name = "second"
    override val description = ""
    override val aliases = listOf<String>()

    override fun execute(sender: CommandSender, args: List<String>) {
        sender.sendMessage("Number two!")
    }

    override fun tabComplete(sender: CommandSender, args: List<String>): MutableList<String> {
        return mutableListOf()
    }
}