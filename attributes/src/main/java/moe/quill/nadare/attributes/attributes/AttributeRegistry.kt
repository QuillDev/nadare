package moe.quill.nadare.attributes.attributes

import moe.quill.nadare.attributes.events.AttributeContactDamageEvent
import moe.quill.nadare.attributes.events.AttributeListener
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.Plugin
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

class AttributeRegistry(val plugin: Plugin) {

    val listenerBindings = mutableMapOf<KClass<out AttributeListener>, List<AttributeData>>()
    val parameterBindings = mutableMapOf<KClass<*>, MutableList<AttributeData>>()
    val nameBindings = mutableMapOf<String, AttributeData>()

    fun register(vararg listeners: AttributeListener) {

        listeners.forEach { listener ->
            Bukkit.getLogger().info("Trying to register ${listener::class.simpleName}")
            //Get all the valid annotated methods
            val executors = listener::class::declaredMemberFunctions.get()
                .filter { it.hasAnnotation<Attribute>() && it.parameters.size == 2 }
                .map {
                    //Get the annotation
                    val annotation = it.findAnnotation<Attribute>()
                    annotation ?: return@map null

                    val klass = it.parameters[1].type.classifier as KClass<*>

                    //Build the attribute data for this attribute
                    val data = AttributeData(
                        annotation.name,
                        annotation.minLevel,
                        annotation.maxLevel,
                        NamespacedKey(plugin, annotation.name),
                        it,
                        listener
                    )

                    //Get the class the parameter is and make sure we're all matching
                    parameterBindings.getOrPut(klass) { mutableListOf() } += data
                    nameBindings.getOrPut(data.name) { data }

                    return@map data
                }.filterNotNull()

            listenerBindings[listener::class] = executors
        }


    }

    //TODO: Handle Later
    fun unregister() {

    }
}