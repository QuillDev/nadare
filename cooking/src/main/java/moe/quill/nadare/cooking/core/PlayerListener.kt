package moe.quill.nadare.cooking.core

import org.bukkit.Tag
import org.bukkit.block.data.type.Campfire
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent

class PlayerListener(private val campfireManager: CampfireManager) : Listener {
    @EventHandler
    private fun onPlayerPlaceCampfire(event: BlockPlaceEvent){
        val block = event.block
        if(!Tag.CAMPFIRES.values.contains(block.type)) return
        val blockData = block.blockData as Campfire
        val location = block.location

        campfireManager.registerCampfire(EWCampfire(location, blockData, false).create())
    }
}