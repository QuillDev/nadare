package moe.quill.nadare.attributes.events

import moe.quill.nadare.attributes.attributes.AttributeRegistryImpl
import moe.quill.nadare.bukkitcommon.BukkitLambda
import org.bukkit.Bukkit
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.Plugin

class AttributeEventTranslator(val registry: AttributeRegistryImpl, val plugin: Plugin) : Listener {

    //Cache arrows for arrow based events
    val arrowCache = mutableMapOf<Projectile, ItemStack>()

    @EventHandler
    fun onSpawn(event: EntityShootBowEvent) {
        val bow = event.bow ?: return
        bow.itemMeta ?: return

        val projectile = event.projectile
        if (projectile !is Projectile) return
        arrowCache[projectile] = bow

        BukkitLambda { arrowCache.remove(projectile) }.runTaskLater(plugin, 500)
    }

    //Listen for arrow
    @EventHandler(ignoreCancelled = true)
    fun projectileEvent(event: ProjectileHitEvent) {
        val shooter = event.entity.shooter as? LivingEntity ?: return
        val victim = event.hitEntity as? LivingEntity ?: return

        val bow = arrowCache[event.entity] ?: return
        val dataContainer = bow.itemMeta?.persistentDataContainer ?: return

        val attributeEvent = AttributeProjectileDamageEvent(shooter, victim, event)
        registry.parameterBindings[AttributeProjectileDamageEvent::class]?.let { it ->
            it.forEach { data ->
                if (!dataContainer.has(data.key, PersistentDataType.INTEGER)) return@forEach
                data.runner.call(data.parentInstance, attributeEvent)
            }
        }

        arrowCache.remove(event.entity)
    }

    //Listen for contact damage events
    @EventHandler(ignoreCancelled = true)
    fun onEntityDamageEntity(event: EntityDamageByEntityEvent) {
        val attacker = event.damager as? LivingEntity ?: return
        val victim = event.entity as? LivingEntity ?: return

        val item = attacker.equipment?.itemInMainHand ?: return
        val dataContainer = item.itemMeta?.persistentDataContainer ?: return

        val attributeEvent = AttributeContactDamageEvent(attacker, victim, event)
        registry.parameterBindings[AttributeContactDamageEvent::class]?.let { it ->
            it.forEach { data ->
                if (!dataContainer.has(data.key, PersistentDataType.INTEGER)) return@forEach
                data.runner.call(data.parentInstance, attributeEvent)
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    fun onConsume(event: PlayerItemConsumeEvent) {

        Bukkit.getLogger().info("fsadasdg")
        Bukkit.getLogger().info(("" + registry.parameterBindings[AttributeConsumeEvent::class]?.size))
        Bukkit.getLogger().info(event.item.itemMeta?.persistentDataContainer.toString())
        val item = event.item
        val dataContainer = item.itemMeta?.persistentDataContainer ?: return

        val attributeEvent = AttributeConsumeEvent(event.player, event.item, event)
        registry.parameterBindings[AttributeConsumeEvent::class]?.let { it ->
            it.forEach { data ->
                if (!dataContainer.has(data.key, PersistentDataType.INTEGER)) return@forEach
                Bukkit.getLogger().info("yyyy")
                data.runner.call(data.parentInstance, attributeEvent)
            }
        }

    }
}