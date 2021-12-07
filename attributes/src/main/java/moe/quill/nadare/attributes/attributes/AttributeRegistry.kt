package moe.quill.nadare.attributes.attributes

import moe.quill.nadare.attributes.events.management.AttributeListener
import org.w3c.dom.Attr
import kotlin.reflect.KClass

interface AttributeRegistry {
    fun register(vararg listeners: AttributeListener)
    fun getAttributes(type: AttributeType): List<AttributeData>
}