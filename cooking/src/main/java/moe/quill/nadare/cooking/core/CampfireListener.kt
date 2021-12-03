package moe.quill.nadare.cooking.core;

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
    fun onPlayerGetFood(event: PlayerInteractAtEntityEvent){
        val player = event.player
        val entity = event.rightClicked
        val item = player.inventory.itemInMainHand

        if(entity != campfire.pot) return
        event.isCancelled = true

        if(item.type != Material.BOWL) return

        if(campfire.ingredients.size == 0) {
            player.sendActionBar(Component.text("That's just an empty pot!"))
            return
        }
        val location = campfire.location

        if(!(location.block.blockData as Campfire).isLit) {
            player.sendActionBar(Component.text("This food seems a little cold..."))
            return
        }

        var slot = player.inventory.heldItemSlot
        player.inventory.addItem(ItemStack(if(campfire.ingredients.contains(Material.POTION)) Material.SUSPICIOUS_STEW else Material.RABBIT_STEW))

        location.world.playSound(location, Sound.BLOCK_HONEY_BLOCK_BREAK, 1f, 1f)
    }

    @EventHandler
    fun onPlayerAddIngredient(event: PlayerInteractAtEntityEvent){
        val player = event.player
        val entity = event.rightClicked
        val item = player.inventory.itemInMainHand

        if(entity != campfire.pot) return
        event.isCancelled = true

        if(item.type == Material.BOWL || item.type == Material.FLINT_AND_STEEL) return
        campfire.addIngredient(player)
    }
}
