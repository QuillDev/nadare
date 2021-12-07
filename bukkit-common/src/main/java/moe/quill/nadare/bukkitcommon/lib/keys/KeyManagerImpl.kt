package moe.quill.nadare.bukkitcommon.lib.keys

import org.bukkit.NamespacedKey
import org.bukkit.plugin.Plugin

internal class KeyManagerImpl : KeyManager {

    override val keys = mutableMapOf<String, NamespacedKey>()

    override fun addKey(key: NamespacedKey) {
        this.keys[key.key] = key
    }

    override fun removeKey(key: NamespacedKey) {
        removeKey(key.key)
    }

    override fun removeKey(identifier: String) {
        keys -= identifier
    }

    override fun getKey(identifier: String): NamespacedKey? {
        return keys[identifier]
    }

    override fun getKey(plugin: Plugin, identifier: String): NamespacedKey? {
        val key = getKey(identifier) ?: return null
        val testKey = NamespacedKey.fromString(identifier, plugin) ?: return null

        return if (key.namespace == testKey.namespace) key else null
    }
}