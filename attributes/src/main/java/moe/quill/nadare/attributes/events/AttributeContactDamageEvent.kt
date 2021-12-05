package moe.quill.nadare.attributes.events

import moe.quill.nadare.attributes.attributes.AttributeType
import moe.quill.nadare.attributes.events.management.AttributeEvent
import org.bukkit.entity.LivingEntity
import org.bukkit.event.entity.EntityDamageByEntityEvent

class AttributeContactDamageEvent(
    val attacker: LivingEntity,
    val victim: LivingEntity,
    val event: EntityDamageByEntityEvent
) : AttributeEvent(AttributeType.CONTACT_DAMAGE)