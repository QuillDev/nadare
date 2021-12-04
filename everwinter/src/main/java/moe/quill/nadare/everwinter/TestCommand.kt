package moe.quill.nadare.everwinter

import moe.quill.nadare.bukkitcommon.commands.Command
import net.kyori.adventure.text.Component
import org.bukkit.command.CommandSender

class TestCommand : Command("test") {

    init {
        registerSubcommand(SecondCommand())
    }
    override fun execute(sender: CommandSender, args: List<String>) {
        sender.sendMessage(Component.text("Hello World!"))
    }

    override fun tabComplete(sender: CommandSender, args: List<String>): MutableList<String> {
        return mutableListOf("test", "test2", "a", "b")
    }
}