package moe.quill.nadare.bukkitcommon.lib

import org.bukkit.scheduler.BukkitRunnable

class BukkitLambda(val runner: () -> Unit) : BukkitRunnable() {

    override fun run() {
        runner()
    }
}