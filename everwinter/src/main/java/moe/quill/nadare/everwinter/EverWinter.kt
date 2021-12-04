package moe.quill.nadare.everwinter;

import moe.quill.nadare.bukkitcommon.ModuleBase
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

class EverWinter : JavaPlugin(), ModuleBase {
    override val plugin = this

    override fun onEnable() {
        // Plugin startup logic
        registerCommand(TestCommand())
    }

    override fun onDisable() {
        super.onDisable()
    }


}
