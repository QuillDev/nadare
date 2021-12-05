package moe.quill.nadare.attributes.attributes

import moe.quill.nadare.attributes.events.management.AttributeListener
import org.bukkit.NamespacedKey
import kotlin.reflect.KFunction

class AttributeData(
    val name: String,
    val minLevel: Int,
    val maxLevel: Int,
    val key: NamespacedKey,
    val runner: KFunction<*>,
    val parentInstance: AttributeListener
)