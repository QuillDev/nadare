package moe.quill.nadare.cooking.food.runnables

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class EarthyRunnable(private val player: Player,private val endTime: Long) : BukkitRunnable(){
    override fun run() {
        val topBlock = player.location.block
        val block = topBlock.location.clone().add(0.0, -1.0, 0.0).block
        if(topBlock.type == Material.SNOW) topBlock.breakNaturally()
        if(block.type == Material.GRASS_BLOCK) block.applyBoneMeal(BlockFace.UP)
        if(System.currentTimeMillis() < endTime) return
        player.sendActionBar(Component.text("Earthy effect ended!").color(NamedTextColor.GREEN))
        cancel()
    }
}