package moe.quill.nadare.attributes

import moe.quill.nadare.attributes.attributes.AttributeRegistry
import moe.quill.nadare.attributes.events.AttributeDamageEvent
import moe.quill.nadare.attributes.events.AttributeEventTranslator
import moe.quill.nadare.bukkitcommon.ModuleBase
import org.bukkit.Bukkit
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

        registerListeners(this, AttributeEventTranslator(registry))
    }


    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        var item = ItemStack(Material.DIAMOND_AXE)
        val meta = item.itemMeta ?: return
        val data = meta.persistentDataContainer

        registry.parameterBindings[AttributeDamageEvent::class]?.let { bind ->
            bind.forEach {
                data.set(it.key, PersistentDataType.INTEGER, it.level)
                Bukkit.getLogger().info("added key ${it.key.key}")
            }
        }

        item.itemMeta = meta

        event.player.inventory.addItem(item)
    }
}