package moe.quill.nadare.attributes.events

import moe.quill.nadare.attributes.attributes.AttributeRegistry
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.persistence.PersistentDataType

class AttributeEventTranslator(val registry: AttributeRegistry) : Listener {

    @EventHandler
    fun onEntityDamageEntity(event: EntityDamageByEntityEvent) {
        val attacker = event.damager as? LivingEntity ?: return
        val victim = event.entity as? LivingEntity ?: return

        val item = attacker.equipment?.itemInMainHand ?: return
        val dataContainer = item.itemMeta?.persistentDataContainer ?: return

        val attributeEvent = AttributeDamageEvent(attacker, victim, event)
        registry.parameterBindings[AttributeDamageEvent::class]?.let { it ->
            it.forEach { data ->
                if (!dataContainer.has(data.key, PersistentDataType.INTEGER)) return@forEach
                data.runner.call(data.parentInstance, attributeEvent)
            }
        }
    }
}