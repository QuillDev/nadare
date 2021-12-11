package moe.quill.nadare.cooking.food.runnables

import moe.quill.nadare.bukkitcommon.lib.BukkitLambda
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class EarthyRunnable(private val player: Player,private val endTime: Long) : BukkitRunnable(){
    override fun run() {
        val block = player.location.clone().add(0.0,-1.0,0.0).block
        if(block.type == Material.GRASS_BLOCK) block.applyBoneMeal(BlockFace.UP)
        if(System.currentTimeMillis() < endTime) return
        player.sendActionBar(Component.text("Earthy effect ended!").color(NamedTextColor.GREEN))
        cancel()
    }
}