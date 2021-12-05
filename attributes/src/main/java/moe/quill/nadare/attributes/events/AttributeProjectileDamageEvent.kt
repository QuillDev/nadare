package moe.quill.nadare.attributes.events

import org.bukkit.entity.LivingEntity
import org.bukkit.event.entity.ProjectileHitEvent

class AttributeProjectileDamageEvent(
    val shooter: LivingEntity,
    val victim: LivingEntity,
    val event: ProjectileHitEvent
) : AttributeEvent()