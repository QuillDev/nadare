package moe.quill.nadare.cooking.events

import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEvent

class PlayerLeftClickArmorStandEvent(val player: Player, val armorStand: ArmorStand, val parent: EntityDamageByEntityEvent) : Event(){
    companion object{
        @JvmStatic
        private val HANDLERS: HandlerList = HandlerList()

        @JvmStatic
        fun getHandlerList() : HandlerList {
            return HANDLERS
        }
    }
    override fun getHandlers(): HandlerList {
        return HANDLERS
    }
}