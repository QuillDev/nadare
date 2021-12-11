package moe.quill.nadare.cooking.food

import moe.quill.nadare.attributes.attributes.AttributeType
import moe.quill.nadare.attributes.attributes.registry.AttributeRegistry
import moe.quill.nadare.bukkitcommon.lib.ModuleBase
import moe.quill.nadare.bukkitcommon.lib.items.ItemBuilder
import moe.quill.nadare.bukkitcommon.lib.keys.KeyManager
import moe.quill.nadare.cooking.core.EWCampfire
import moe.quill.nadare.cooking.food.recipe.Cookable
import moe.quill.nadare.cooking.food.recipe.CookingGroup
import moe.quill.nadare.cooking.food.recipe.CookingRecipe
import moe.quill.nadare.cooking.food.recipe.Ingredient
import moe.quill.nadare.cooking.util.PrettyNameGen
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class FoodItemGenerator(private val module: ModuleBase, private val registry: AttributeRegistry) {

    private val adjMap = mapOf<Int, String>(
        0 to "Void ",
        1 to "Small ",
        2 to "Medium ",
        3 to "Large "
    )

    private val attributes = registry.getAttributes(AttributeType.CONSUME)

    fun generateFoodItem(campfire: EWCampfire): ItemStack? {
        val ingredients = campfire.ingredients
        if (ingredients.isEmpty()) return null

        val stew = ItemStack(Material.SUSPICIOUS_STEW)
        val food = ItemStack(Material.RABBIT_STEW)

        val item = if (CookingGroup.WATER.matchesAny(Ingredient.valueOf(Material.POTION))) stew else food
        var lore = mutableListOf<Component>(Component.text("Created From: ").color(NamedTextColor.AQUA))
        val itemMeta = item.itemMeta
        val itemData = itemMeta.persistentDataContainer

        ingredients.forEach {
            if (it != null) {
                lore +=
                if (it.material == Material.POTION) Component.text("• Water").color(NamedTextColor.AQUA)
                else Component.text("• ").append(prettify(it.material)).color(NamedTextColor.AQUA)
            }
        }
        var name = Component.text()
        val matches = CookingRecipe.findMatches(ingredients)
            if(matches.isNotEmpty()){
                lore += Component.empty()
                matches.forEach {
                    lore += it.description
                    name.append(it.prefix).append(Component.text(" "))
                    attributes.filter{ attributeData -> attributeData.name.lowercase() == it.name.lowercase() }.forEach{ data ->
                        itemData.set(data.key, PersistentDataType.INTEGER, data.minLevel)
                    }
                }
            }
        item.itemMeta = itemMeta

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