package moe.quill.nadare.cooking.events

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.player.PlayerInteractEvent

class CustomEventListener : Listener {
    @EventHandler
    fun onPlayerInteractWithBlock(event: PlayerInteractEvent){
        if(event.action != Action.RIGHT_CLICK_BLOCK) return
        val player = event.player
        val block = event.clickedBlock
        val item = player.inventory.itemInMainHand

        block ?: return

        if(item.type == Material.AIR) Bukkit.getServer().pluginManager.callEvent(
            PlayerRightClickBlockEvent(player, block, event)
        )
        if(item.type != Material.AIR) Bukkit.getServer().pluginManager.callEvent(
            PlayerRightClickBlockWithItemEvent(player, block, item, event)
        )
    }
}