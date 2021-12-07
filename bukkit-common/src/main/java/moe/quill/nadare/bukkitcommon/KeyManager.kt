package moe.quill.nadare.bukkitcommon

import org.bukkit.NamespacedKey

class KeyManager {

    val keys = mutableMapOf<String, NamespacedKey>()

    fun addKey(key: NamespacedKey) {
        this.keys[key.key] = key
    }

    fun removeKey(key: NamespacedKey) {
        removeKey(key.key)
    }

    fun removeKey(identifier: String) {
        keys -= identifier
    }

    fun getKey(identifier: String): NamespacedKey? {
        return keys[identifier]
    }
}