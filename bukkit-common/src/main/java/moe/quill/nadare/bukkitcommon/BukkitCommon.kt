package moe.quill.nadare.bukkitcommon

import moe.quill.nadare.bukkitcommon.lib.ModuleBase
import moe.quill.nadare.bukkitcommon.lib.keys.KeyManager
import moe.quill.nadare.bukkitcommon.lib.keys.KeyManagerImpl
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.ServicePriority
import org.bukkit.plugin.java.JavaPlugin

class BukkitCommon : JavaPlugin(), ModuleBase {
    override val plugin = this

    override fun onEnable() {
        val keyManager: KeyManager = KeyManagerImpl()
        server.servicesManager.register(KeyManager::class.java, keyManager, this, ServicePriority.High)
    }
}