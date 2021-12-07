package moe.quill.nadare.attributes.attributes

import moe.quill.nadare.attributes.events.management.AttributeListener
import moe.quill.nadare.bukkitcommon.lib.keys.KeyManager

interface AttributeRegistry {

    val keyManager: KeyManager

    fun register(vararg listeners: AttributeListener)
    fun getAttributes(type: AttributeType): List<AttributeData>
}