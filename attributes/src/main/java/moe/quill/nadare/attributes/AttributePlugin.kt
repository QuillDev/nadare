package moe.quill.nadare.attributes

import moe.quill.nadare.attributes.attributes.AttributeRegistry
import moe.quill.nadare.attributes.attributes.AttributeRegistryImpl
import moe.quill.nadare.attributes.events.management.AttributeEventTranslator
import moe.quill.nadare.bukkitcommon.ModuleBase
import org.bukkit.plugin.ServicePriority
import org.bukkit.plugin.java.JavaPlugin

class AttributePlugin : JavaPlugin(), ModuleBase {
    override val plugin = this

    private lateinit var registry: AttributeRegistryImpl

    override fun onEnable() {
        this.registry = AttributeRegistryImpl(this)
        server.servicesManager.register(AttributeRegistry::class.java, registry, this, ServicePriority.Highest)
        registerListeners(AttributeEventTranslator(registry, this))
    }

}