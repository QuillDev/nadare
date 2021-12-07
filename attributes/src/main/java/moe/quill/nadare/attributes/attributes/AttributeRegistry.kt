package moe.quill.nadare.attributes.attributes

import moe.quill.nadare.attributes.events.management.AttributeListener
<<<<<<< HEAD
import moe.quill.nadare.bukkitcommon.KeyManager

=======
import moe.quill.nadare.bukkitcommon.lib.keys.KeyManager
>>>>>>> origin/master

interface AttributeRegistry {

    val keyManager: KeyManager

    fun register(vararg listeners: AttributeListener)
    fun getAttributes(type: AttributeType): List<AttributeData>
}