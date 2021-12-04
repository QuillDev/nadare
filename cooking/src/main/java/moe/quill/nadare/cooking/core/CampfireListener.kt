package moe.quill.nadare.cooking.core;

import moe.quill.nadare.cooking.events.PlayerLeftClickArmorStandEvent
import moe.quill.nadare.cooking.events.PlayerRightClickBlockWithItemEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material;
import org.bukkit.Sound
import org.bukkit.Tag
import org.bukkit.block.data.type.Campfire
import org.bukkit.entity.ArmorStand
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.ItemStack

public class CampfireListener(private val campfire: EWCampfire) : Listener {

    @EventHandler
    fun onPlayerAddPot(event: PlayerRightClickBlockWithItemEvent){
        val item = event.item
        val block = event.block

        if(block.location != campfire.location) return

        if(!Tag.CAMPFIRES.values.contains(block.type)) return
//
        if(item.type != Material.CAULDRON) return

        if(item.amount > 1) item.subtract()
        else event.player.inventory.remove(item)

        event.parent.isCancelled = true
        campfire.addPot()
    }

    @EventHandler
    fun onPlayerBreak(event: BlockBreakEvent){
        if(event.block.location != campfire.location) return
        campfire.destroy()
    }

    @EventHandler
    fun onPlayerHitArmorStand(event: PlayerLeftClickArmorStandEvent){
        if(event.armorStand != campfire.pot) return
        campfire.removePot()
    }

    @EventHandler
    fun onPlayerGetFood(event: PlayerInteractAtEntityEvent){
        val player = event.player
        val entity = event.rightClicked
        val item = player.inventory.itemInMainHand

        if(entity != campfire.pot) return
        event.isCancelled = true

        if(item.type != Material.BOWL) return
        campfire.grabServing(player)
    }

    @EventHandler
    fun onPlayerAddIngredient(event: PlayerInteractAtEntityEvent){
        val player = event.player
        val entity = event.rightClicked
        val item = player.inventory.itemInMainHand

        if(entity != campfire.pot) return
        event.isCancelled = true

        if(item.type == Material.BOWL) return

        if(item.type.isEdible || item.type == Material.POTION) {
            campfire.addIngredient(player)
            return
        }

        player.sendActionBar(Component.text("That wouldn't taste so good...").color(NamedTextColor.GREEN))

    }
}
