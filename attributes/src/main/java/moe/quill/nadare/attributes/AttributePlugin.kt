package moe.quill.nadare.attributes

import moe.quill.nadare.attributes.attributes.AttributeRegistry
import moe.quill.nadare.attributes.attributes.AttributeRegistryImpl
import moe.quill.nadare.attributes.events.management.AttributeEventTranslator
import moe.quill.nadare.bukkitcommon.lib.ModuleBase
import moe.quill.nadare.bukkitcommon.lib.keys.KeyManager
import org.bukkit.plugin.ServicePriority
import org.bukkit.plugin.java.JavaPlugin

class AttributePlugin : JavaPlugin(), ModuleBase {
    override val plugin = this

    override fun onEnable() {


        val keyManager = server.servicesManager.load(KeyManager::class.java) ?: run {
            logger.severe("Could not load KeyManager from BukkitCommon!")
            return
        }

        val registry = AttributeRegistryImpl(this, keyManager)

        server.servicesManager.register(AttributeRegistry::class.java, registry, this, ServicePriority.Highest)
        registerListeners(AttributeEventTranslator(registry, this))
    }

}