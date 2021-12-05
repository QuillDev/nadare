package moe.quill.nadare.attributes.events

import org.bukkit.entity.LivingEntity
import org.bukkit.event.entity.EntityDamageByEntityEvent

class AttributeDamageEvent(val attacker: LivingEntity, val victim: LivingEntity, val event: EntityDamageByEntityEvent)