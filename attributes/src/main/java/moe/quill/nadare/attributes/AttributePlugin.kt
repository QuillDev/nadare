package moe.quill.nadare.attributes

import moe.quill.nadare.attributes.attributes.AttributeRegistry
import moe.quill.nadare.attributes.events.AttributeConsumeEvent
import moe.quill.nadare.attributes.events.AttributeContactDamageEvent
import moe.quill.nadare.attributes.events.AttributeEventTranslator
import moe.quill.nadare.attributes.events.AttributeProjectileDamageEvent
import moe.quill.nadare.attributes.test.TestAttributeListener
import moe.quill.nadare.bukkitcommon.ModuleBase
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.ServicePriority
import org.bukkit.plugin.java.JavaPlugin

class AttributePlugin : JavaPlugin(), Listener, ModuleBase {
    override val plugin = this

    private lateinit var registry: AttributeRegistry

    override fun onEnable() {
        this.registry = AttributeRegistry(this)
        server.servicesManager.register(AttributeRegistry::class.java, registry, this, ServicePriority.Highest)

        registry.register(TestAttributeListener())

        registerListeners(this, AttributeEventTranslator(registry, this))
    }


    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val axe = ItemStack(Material.DIAMOND_AXE)
        val meta = axe.itemMeta ?: return
        val data = meta.persistentDataContainer

        registry.parameterBindings[AttributeContactDamageEvent::class]?.let { bind ->
            bind.forEach {
                data.set(it.key, PersistentDataType.INTEGER, it.minLevel)
            }
        }
        axe.itemMeta = meta
        event.player.inventory.addItem(axe)

        val bow = ItemStack(Material.CROSSBOW)
        val bowMeta = bow.itemMeta ?: return
        val bowData = bowMeta.persistentDataContainer

        registry.parameterBindings[AttributeProjectileDamageEvent::class]?.let { bind ->
            bind.forEach {
                bowData.set(it.key, PersistentDataType.INTEGER, it.minLevel)
            }
        }
        bow.itemMeta = bowMeta
        event.player.inventory.addItem(bow)

        val food = ItemStack(Material.COOKED_BEEF)
        val foodMeta = food.itemMeta ?: return
        val foodData = foodMeta.persistentDataContainer

        registry.parameterBindings[AttributeConsumeEvent::class]?.let { bind ->
            bind.forEach {
                foodData.set(it.key, PersistentDataType.INTEGER, it.minLevel)
            }
        }
        food.itemMeta = foodMeta
        event.player.inventory.addItem(food)
    }
}