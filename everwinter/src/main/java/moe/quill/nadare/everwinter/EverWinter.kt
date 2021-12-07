package moe.quill.nadare.everwinter;

import moe.quill.nadare.attributes.attributes.AttributeRegistry
import moe.quill.nadare.attributes.attributes.AttributeType
import moe.quill.nadare.bukkitcommon.lib.ModuleBase
import moe.quill.nadare.everwinter.test.TestAttributeListener
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin

class EverWinter : JavaPlugin(), ModuleBase, Listener {
    override val plugin = this

    lateinit var registry: AttributeRegistry
    override fun onEnable() {
        // Plugin startup logic
        registerCommand(TestCommand())

        Bukkit.getLogger().info("Getting attributes registry!")
        this.registry = server.servicesManager.load(AttributeRegistry::class.java) ?: return
        registry.register(TestAttributeListener())
        registerListeners(this)
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {

        val axe = ItemStack(Material.DIAMOND_AXE)
        val meta = axe.itemMeta ?: return
        val data = meta.persistentDataContainer

        registry.getAttributes(AttributeType.CONTACT_DAMAGE).forEach {
            data.set(it.key, PersistentDataType.INTEGER, it.minLevel)
        }
        axe.itemMeta = meta
        event.player.inventory.addItem(axe)

        val bow = ItemStack(Material.CROSSBOW)
        val bowMeta = bow.itemMeta ?: return
        val bowData = bowMeta.persistentDataContainer

        registry.getAttributes(AttributeType.PROJECTILE_DAMAGE).forEach {
            bowData.set(it.key, PersistentDataType.INTEGER, it.minLevel)
        }
        bow.itemMeta = bowMeta
        event.player.inventory.addItem(bow)

        val food = ItemStack(Material.COOKED_BEEF)
        val foodMeta = food.itemMeta ?: return
        val foodData = foodMeta.persistentDataContainer

        registry.getAttributes(AttributeType.CONSUME).forEach {
            foodData.set(it.key, PersistentDataType.INTEGER, it.minLevel)
        }
        food.itemMeta = foodMeta
        event.player.inventory.addItem(food)
    }
}
