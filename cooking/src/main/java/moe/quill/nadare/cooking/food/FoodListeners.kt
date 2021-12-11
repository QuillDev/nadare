package moe.quill.nadare.cooking.food

import moe.quill.nadare.attributes.attributes.Attribute
import moe.quill.nadare.attributes.events.AttributeConsumeEvent
import moe.quill.nadare.attributes.events.management.AttributeListener
import moe.quill.nadare.bukkitcommon.lib.BukkitLambda
import moe.quill.nadare.bukkitcommon.lib.ModuleBase
import moe.quill.nadare.cooking.food.runnables.EarthyRunnable
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.PortalType
import org.bukkit.block.BlockFace
import org.bukkit.entity.*
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class FoodListeners(val module: ModuleBase) : AttributeListener {

    @Attribute(name = "gilded")
    fun onEatGilded(event: AttributeConsumeEvent){
        event.player.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 20 * 30, 0, true))
    }

    @Attribute(name = "overcooked")
    fun onEatOvercooked(event: AttributeConsumeEvent){
        event.player.addPotionEffect(PotionEffect(PotionEffectType.SLOW, 20 * 12, 2, true))
    }

    @Attribute(name = "veggy")
    fun onEatVeggy(event: AttributeConsumeEvent){
        Bukkit.broadcast(
            Component.text()
                .append(Component.text("${event.player.name} ").decorate(TextDecoration.BOLD))
                .append(Component.text("just ate veggies!"))
                .color(NamedTextColor.GREEN)
                .build()
        )
    }

    @Attribute(name = "balanced")
    fun onEatBalanced(event: AttributeConsumeEvent){
        event.player.saturation += 8
    }

    @Attribute(name = "brothy")
    fun onEatBrothy(event: AttributeConsumeEvent){
        event.player.addPotionEffect(PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 300, 0, true))
    }

    @Attribute(name = "gross")
    fun onEatGross(event: AttributeConsumeEvent){
        event.player.addPotionEffect(PotionEffect(PotionEffectType.CONFUSION, 20 * 8, 0, true))
    }

    @Attribute(name = "extra_sweet")
    fun onEatSweet(event: AttributeConsumeEvent){
        event.player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 20 * 30, 0, true))
    }

    @Attribute(name = "breakfasty")
    fun onEatBreakfast(event: AttributeConsumeEvent){
        val player = event.player
        val world = player.world
        if( world.isDayTime ) player.addPotionEffect(PotionEffect(PotionEffectType.JUMP, 20 * 60, 0, true))
        else player.sendActionBar(Component.text("Breakfasty effect not applied."))
    }

    @Attribute(name = "carnivorous")
    fun onEatCarnivorous(event: AttributeConsumeEvent){
        event.player.addPotionEffect(PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 60, 0, true))
    }

    @Attribute(name = "creamy")
    fun OnEatCreamy(event: AttributeConsumeEvent){
        event.player.removePotionEffect(PotionEffectType.SLOW)
        event.player.removePotionEffect(PotionEffectType.HUNGER)
        event.player.removePotionEffect(PotionEffectType.BAD_OMEN)
        event.player.removePotionEffect(PotionEffectType.BLINDNESS)
        event.player.removePotionEffect(PotionEffectType.CONFUSION)
        event.player.removePotionEffect(PotionEffectType.POISON)
        event.player.removePotionEffect(PotionEffectType.UNLUCK)
        event.player.removePotionEffect(PotionEffectType.WEAKNESS)
        event.player.removePotionEffect(PotionEffectType.WITHER)
    }

    @Attribute(name = "caramelized")
    fun OnEatCaramelized(event: AttributeConsumeEvent){
        event.player.getNearbyEntities(10.0, 10.0, 10.0).filter {
            it is LivingEntity
        }.forEach{
            if(it != event.player) {
                (it as LivingEntity).addPotionEffect(PotionEffect(PotionEffectType.SLOW, 20 * 30, 2, false))
                if (it is Player) it.sendActionBar(
                    Component.text("You have been slowed by ${event.player.name}").color(NamedTextColor.YELLOW)
                )
            }
        }
    }

    @Attribute(name = "earthy")
    fun onEatEarthy(event: AttributeConsumeEvent){
        val startTime = System.currentTimeMillis()
        val duration = 1000 * 30
        val endTime = startTime + duration
        EarthyRunnable(event.player, endTime).runTaskTimer(module.plugin, 0, 20)
    }

}