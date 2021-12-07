package moe.quill.nadare.attributes.attributes

import moe.quill.nadare.attributes.events.management.AttributeListener
import org.bukkit.NamespacedKey
import kotlin.reflect.KFunction

class AttributeData(
    val name: String,
    val minLevel: Int,
    val maxLevel: Int,
    val tags: List<String>,
    val key: NamespacedKey,
    val runner: KFunction<*>,
    val parentInstance: AttributeListener
) {
    constructor(attribute: Attribute, key: NamespacedKey, runner: KFunction<*>, parentInstance: AttributeListener) :
            this(attribute.name, attribute.minLevel, attribute.maxLevel, attribute.tags.toList(), key, runner, parentInstance)

    fun hasFlags(vararg tags: String): Boolean {
        return this.tags.containsAll(tags.toList())
    }
}