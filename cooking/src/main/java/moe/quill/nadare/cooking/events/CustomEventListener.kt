package moe.quill.nadare.cooking.events

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDamageByEntityEvent
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

    @EventHandler
    fun onPlayerHitArmorStand(event: EntityDamageByEntityEvent){
        if (event.damager !is Player || event.entity !is ArmorStand) return
        val player = event.damager as Player
        val armorStand = event.entity as ArmorStand

        Bukkit.getServer().pluginManager.callEvent(PlayerLeftClickArmorStandEvent(player, armorStand, event))

    }
}