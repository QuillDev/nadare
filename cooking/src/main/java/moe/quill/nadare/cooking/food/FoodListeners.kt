package moe.quill.nadare.cooking.food

import moe.quill.nadare.attributes.attributes.Attribute
import moe.quill.nadare.attributes.events.AttributeConsumeEvent
import moe.quill.nadare.attributes.events.AttributeListener
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.potion.Potion
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class FoodListeners(private val keyManager: KeyManager) : AttributeListener {

    val foodValues = mapOf<Material, Double>(
        Material.GOLDEN_CARROT to 2.4,
        Material.GOLDEN_APPLE to 2.4, //
        Material.ENCHANTED_GOLDEN_APPLE to 2.4, //

        Material.COOKED_MUTTON to 1.6,
        Material.COOKED_PORKCHOP to 1.6,
        Material.COOKED_SALMON to 1.6,
        Material.COOKED_BEEF to 1.6,

        Material.BAKED_POTATO to 1.2,
        Material.BEETROOT to 1.2,
        Material.BEETROOT_SOUP to 1.2,
        Material.BREAD to 1.2,
        Material.CARROT to 1.2,
        Material.COOKED_CHICKEN to 1.2,
        Material.COOKED_COD to 1.2,
        Material.COOKED_RABBIT to 1.2,
        Material.MUSHROOM_STEW to 1.2,
        Material.RABBIT_STEW to 1.2,
        Material.SUSPICIOUS_STEW to 1.2,

        Material.APPLE to .6,
        Material.CHORUS_FRUIT to .6, //
        Material.DRIED_KELP to .6,
        Material.MELON to .6,
        Material.POISONOUS_POTATO to .6, //
        Material.POTATO to .6,
        Material.PUMPKIN_PIE to .6,
        Material.BEEF to .6,
        Material.CHICKEN to .6,
        Material.MUTTON to .6,
        Material.PORKCHOP to .6,
        Material.RABBIT to .6,
        Material.SWEET_BERRIES to .6,

        Material.COOKIE to .2,
        Material.GLOW_BERRIES to .2,
        Material.HONEY_BOTTLE to .2,
        Material.PUFFERFISH to .2, //
        Material.COD to .2,
        Material.SALMON to .2,
        Material.ROTTEN_FLESH to .2,
        Material.SPIDER_EYE to .2,
        Material.TROPICAL_FISH to .2

    )

    @Attribute(name = "custom_food")
    fun onPlayerEat(event: AttributeConsumeEvent) {
        val player = event.player
        val item = event.item
        val ingredients = keyManager.getIngredients(item)
        val isStew = ingredients.contains(Material.POTION)

        var saturation = 0.0
        ingredients.forEach{
            if(foodValues.contains(it)) {
                saturation += foodValues[it]!! / 2
            }
        }
        player.saturation += saturation.toFloat()

        Bukkit.getLogger().info("food this work? : ${saturation}")

        if(ingredients.isNotEmpty()) {
            player.addPotionEffect(PotionEffect(PotionEffectType.ABSORPTION, 20 * 120, ingredients.size, false, true))
        }
    }

    private fun ingredientMatch(ingredients: List<Material>,vararg recipe: Material) : Boolean{
        if (recipe.size != 3) return false
        return ingredients.contains(recipe[0]) && ingredients.contains(recipe[1]) && ingredients.contains(recipe[2])
    }
}