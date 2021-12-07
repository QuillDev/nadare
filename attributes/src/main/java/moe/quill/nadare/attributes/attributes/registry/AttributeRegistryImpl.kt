package moe.quill.nadare.attributes.attributes.registry

import moe.quill.nadare.attributes.attributes.Attribute
import moe.quill.nadare.attributes.attributes.AttributeData
import moe.quill.nadare.attributes.attributes.AttributeType
import moe.quill.nadare.attributes.events.AttributeConsumeEvent
import moe.quill.nadare.attributes.events.AttributeContactDamageEvent
import moe.quill.nadare.attributes.events.AttributeProjectileDamageEvent
import moe.quill.nadare.attributes.events.management.AttributeListener
import moe.quill.nadare.bukkitcommon.lib.keys.KeyManager
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.plugin.Plugin
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

class AttributeRegistryImpl(private val plugin: Plugin, override val keyManager: KeyManager) : AttributeRegistry {

    val listenerBindings = mutableMapOf<KClass<out AttributeListener>, List<AttributeData>>()
    val parameterBindings = mutableMapOf<KClass<*>, MutableList<AttributeData>>()
    val nameBindings = mutableMapOf<String, AttributeData>()

    val attributeMap = mapOf<AttributeType, KClass<*>>(
        AttributeType.CONTACT_DAMAGE to AttributeContactDamageEvent::class,
        AttributeType.PROJECTILE_DAMAGE to AttributeProjectileDamageEvent::class,
        AttributeType.CONSUME to AttributeConsumeEvent::class

    )

    override fun register(vararg listeners: AttributeListener) {

        listeners.forEach { listener ->
            Bukkit.getLogger().info("Trying to register ${listener::class.simpleName}")
            //Get all the valid annotated methods
            val executors = listener::class::declaredMemberFunctions.get()
                .filter { it.hasAnnotation<Attribute>() && it.parameters.size == 2 }
                .map {
                    //Get the annotation
                    val annotation = it.findAnnotation<Attribute>()
                    annotation ?: return@map null

                    val klass = it.parameters[1].type.classifier as? KClass<*> ?: return@map null

                    val key = NamespacedKey(plugin, annotation.name)
                    keyManager.addKey(key)
                    //Build the attribute data for this attribute
                    val data = AttributeData(
                        annotation.name,
                        annotation.minLevel,
                        annotation.maxLevel,
                        key,
                        it,
                        listener
                    )

                    Bukkit.getLogger().info("Registered attribute -> ${annotation.name} type: ${klass.simpleName}")
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

    override fun getAttributes(type: AttributeType): List<AttributeData> {
        return parameterBindings[attributeMap[type]]?.toList() ?: listOf()
    }
}