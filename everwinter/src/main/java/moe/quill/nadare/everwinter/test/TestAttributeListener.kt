package moe.quill.nadare.everwinter.test

import moe.quill.nadare.attributes.attributes.Attribute
import moe.quill.nadare.attributes.attributes.AttributeFlag
import moe.quill.nadare.attributes.events.AttributeConsumeEvent
import moe.quill.nadare.attributes.events.AttributeContactDamageEvent
import moe.quill.nadare.attributes.events.management.AttributeListener
import moe.quill.nadare.attributes.events.AttributeProjectileDamageEvent
import org.bukkit.Bukkit

class TestAttributeListener : AttributeListener {

    @Attribute(name = "bowflame")
    fun bowFire(event: AttributeProjectileDamageEvent) {
        Bukkit.getLogger().info("ran")
        event.victim.isVisualFire = true
    }

    @Attribute(name = "swordfire")
    fun swordFire(event: AttributeContactDamageEvent) {
        event.victim.isVisualFire = true
    }

    @Attribute(name = "firefood")
    fun fireFood(event: AttributeConsumeEvent) {
        event.player.isVisualFire = !event.player.isVisualFire
        event.player.damage(5.0)
    }
}