package moe.quill.nadare.bukkitcommon.commands

import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.command.defaults.BukkitCommand

abstract class Command constructor(
    name: String,
    description: String = "no description.",
    usageMessage: String = "/${name}",
    aliases: List<String>
) : BukkitCommand(name, description, usageMessage, aliases), CommandBase {

    val subCommands = mutableMapOf<String, SubCommand>()

    override fun execute(sender: CommandSender, args: List<String>) {
        TODO("Not yet implemented")
    }

    override fun tabComplete(sender: CommandSender, args: List<String>) {
        TODO("Not yet implemented")
    }

    fun registerSubcommand(vararg subCommands: SubCommand) {
        subCommands.forEach { this.subCommands[it.name] = it }
    }

    //Overwritten default behavior
    override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean {

        subCommands[args[0]]?.let {

        }.run {  }

        if (args.isNotEmpty()) {
            val sub = subCommands[args[0]]
            sub?.let { retunr  }
        }

        if (args.isEmpty()) {
            execute(sender, args.toList())
            return true
        }

        return true
    }

    override fun tabComplete(
        sender: CommandSender,
        alias: String,
        args: Array<out String>,
        location: Location?
    ): MutableList<String> {
        return super.tabComplete(sender, alias, args, location)
    }


}