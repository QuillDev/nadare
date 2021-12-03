package moe.quill.nadare.bukkitcommon

import org.bukkit.scheduler.BukkitRunnable

class BukkitLambda(val runner: () -> Unit) : BukkitRunnable() {

    override fun run() {
        runner()
    }
}