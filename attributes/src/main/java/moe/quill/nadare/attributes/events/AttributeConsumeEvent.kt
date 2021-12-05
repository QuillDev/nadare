package moe.quill.nadare.attributes.events

import moe.quill.nadare.attributes.attributes.AttributeType
import moe.quill.nadare.attributes.events.management.AttributeEvent
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.ItemStack

class AttributeConsumeEvent(val player: Player, val item: ItemStack, val event: PlayerItemConsumeEvent) :
    AttributeEvent(AttributeType.CONSUME)