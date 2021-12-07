package moe.quill.nadare.cooking.food

import moe.quill.nadare.bukkitcommon.lib.items.ItemBuilder
import moe.quill.nadare.cooking.core.EWCampfire
import moe.quill.nadare.cooking.food.recipe.CookingGroup
import moe.quill.nadare.cooking.food.recipe.CookingRecipe
import moe.quill.nadare.cooking.util.PrettyNameGen
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class FoodItemGenerator() {

    private val adjMap = mapOf<Int, String>(
        0 to "Void ",
        1 to "Small ",
        2 to "Medium ",
        3 to "Large "
    )

    fun generateFoodItem(campfire: EWCampfire): ItemStack? {
        val ingredients = campfire.ingredients
        if (ingredients.isEmpty()) return null

        val stew = ItemStack(Material.SUSPICIOUS_STEW)
        val food = ItemStack(Material.RABBIT_STEW)

        val item = if (CookingGroup.WATER.matchesAny(ingredients)) stew else food
        var lore = mutableListOf<Component>(Component.text("Created From: ").color(NamedTextColor.WHITE))

        ingredients.forEach {
            if (it != null) {
                if (it.material == Material.POTION) Component.text("Water").color(NamedTextColor.WHITE)
                else lore += prettify(it.material).color(NamedTextColor.WHITE)
            }
        }
        var name = Component.text()
        CookingRecipe.findMatches(ingredients).forEach {
            lore += Component.text("")
            lore += it.description
            name.append(it.prefix).append(Component.text(" "))
        }
        name.append(Component.text(adjMap[ingredients.size]!!))
        name.append(Component.text("Meal"))

        return ItemBuilder(item)
            .displayName(name.build())
            .lore(*lore.toTypedArray())
            .build()
    }

    private fun prettify(material: Material): Component {
        return Component.text(PrettyNameGen.asPretty(material))
    }

    private fun ingredientMatch(ingredients: List<Material>, vararg recipe: Material): Boolean {
        if (recipe.size != 3) return false
        return ingredients.contains(recipe[0]) && ingredients.contains(recipe[1]) && ingredients.contains(recipe[2])
    }
}