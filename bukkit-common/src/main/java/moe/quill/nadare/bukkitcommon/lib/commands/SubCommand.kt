package moe.quill.nadare.bukkitcommon.lib.commands

interface SubCommand : CommandBase {
    val name: String
    val description: String
    val aliases: List<String>
}