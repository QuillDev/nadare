package moe.quill.nadare.attributes.test

import moe.quill.nadare.attributes.attributes.Attribute
import moe.quill.nadare.attributes.events.AttributeConsumeEvent
import moe.quill.nadare.attributes.events.AttributeContactDamageEvent
import moe.quill.nadare.attributes.events.AttributeListener
import moe.quill.nadare.attributes.events.AttributeProjectileDamageEvent
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.event.EventHandler

class TestAttributeListener : AttributeListener {

    @Attribute(name = "bowflame")
    fun bowFire(event: AttributeProjectileDamageEvent) {
        event.victim.isVisualFire = true
    }

    @Attribute(name = "swordfire")
    fun swordFire(event: AttributeContactDamageEvent) {
        event.victim.isVisualFire = true
    }

    @Attribute(name = "firefood")
    fun fireFood(event: AttributeConsumeEvent) {
        event.player.isVisualFire = true
        event.player.damage(5.0)
    }
}