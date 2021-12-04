package moe.quill.nadare.bukkitcommon

import org.bukkit.plugin.Plugin

class Module(override val plugin: Plugin) : ModuleBase {
    constructor(module: ModuleBase) : this(module.plugin)
}