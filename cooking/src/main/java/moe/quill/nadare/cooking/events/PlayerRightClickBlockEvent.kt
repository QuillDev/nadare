package moe.quill.nadare.cooking.events

import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerInteractEvent

class PlayerRightClickBlockEvent(val player: Player, val block: Block, val parent: PlayerInteractEvent) : Event() {
    companion object{
        @JvmStatic
        private val HANDLERS: HandlerList = HandlerList()

        @JvmStatic
        fun getHandlerList() : HandlerList{
            return HANDLERS
        }
    }
    override fun getHandlers(): HandlerList {
        return HANDLERS
    }
}