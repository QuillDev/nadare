package moe.quill.nadare.bukkitcommon.commands

import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.command.defaults.BukkitCommand

abstract class Command constructor(
    name: String,
    description: String = "no description.",
    usageMessage: String = "/${name}",
    aliases: List<String> = listOf()
) : BukkitCommand(name, description, usageMessage, aliases), CommandBase {

    private val subCommands = mutableMapOf<String, SubCommand>()

    fun registerSubcommand(vararg subCommands: SubCommand) {
        subCommands.forEach { this.subCommands[it.name] = it }
    }

    //Overwritten default behavior
    override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            execute(sender, args.toList())
            return true
        }

        subCommands[args[0]]?.let {
            it.execute(sender, args.toList().drop(0))
            return true
        }.run {
            execute(sender, args.toList())
            return true
        }
    }

    override fun tabComplete(
        sender: CommandSender,
        alias: String,
        args: Array<out String>,
        location: Location?
    ): MutableList<String> {

        if (subCommands.isNotEmpty()) {
            return when (args.size) {
                0 -> mutableListOf()
                1 -> tabFilter(args[0], subCommands.keys)
                else -> tabFilter(
                    args[args.size - 1],
                    subCommands[args[0]]?.tabComplete(sender, args.toList().drop(1)) ?: listOf()
                )
            }
        }

        return tabComplete(sender, args.toList())
    }
}