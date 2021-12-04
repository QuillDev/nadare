package moe.quill.nadare.bukkitcommon.commands

import org.bukkit.command.CommandSender

interface SubCommand : CommandBase {
    val name: String
    val description: String
    val aliases: List<String>
}