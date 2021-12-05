package moe.quill.nadare.attributes.attributes

import moe.quill.nadare.attributes.events.AttributeListener
import org.bukkit.NamespacedKey
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

class AttributeData(
    val name: String,
    val level: Int,
    val key: NamespacedKey,
    val parameterType: KClass<*>,
    val runner: KFunction<*>,
    val parentInstance: AttributeListener
)