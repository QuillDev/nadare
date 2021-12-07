package moe.quill.nadare.bukkitcommon.lib.keys

import org.bukkit.NamespacedKey
import org.bukkit.plugin.Plugin

interface KeyManager {
    val keys: Map<String, NamespacedKey>

    fun addKey(key: NamespacedKey)
    fun removeKey(key: NamespacedKey)
    fun removeKey(identifier: String)
    fun getKey(identifier: String): NamespacedKey?
    fun getKey(plugin: Plugin, identifier: String): NamespacedKey?
}