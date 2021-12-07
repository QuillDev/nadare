package moe.quill.nadare.cooking.food

import moe.quill.nadare.cooking.core.EWCampfire
import moe.quill.nadare.cooking.util.PrettyNameGen
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffectType

class FoodItemGenerator(private val plugin: JavaPlugin) {

    private val adjMap = mapOf<Int, String>(
        1 to "Small ",
        2 to "Medium ",
        3 to "Large "
    )

    fun generateFoodItem(campfire: EWCampfire): ItemStack? {
        val ingredients = campfire.ingredients
        if (ingredients.isEmpty()) return null

        val stew = ItemStack(Material.SUSPICIOUS_STEW)
        val stewMeta = stew.itemMeta
        stewMeta.displayName(adjMap[ingredients.size]?.let { Component.text(it + "Stew") })
        stew.setItemMeta(stewMeta)

        val food = ItemStack(Material.RABBIT_STEW)
        val foodMeta = food.itemMeta
        foodMeta.displayName(adjMap[ingredients.size]?.let { Component.text(it + "Meal") })
        food.setItemMeta(foodMeta)

        val item = if (campfire.ingredients.contains(Material.POTION)) stew else food
        val itemMeta = item.itemMeta
        var lore = mutableListOf<Component>()

        lore += Component.text("Created From: ").color(NamedTextColor.GREEN)

        ingredients.forEach {
            if (it != null) {
                if (it == Material.POTION) Component.text("Broth").color(NamedTextColor.WHITE)
                else lore += prettify(it).color(NamedTextColor.WHITE)
            }
        }
        itemMeta.lore(lore)
        item.itemMeta = itemMeta

        return item
    }

    private fun prettify(material: Material): Component {
        return Component.text(PrettyNameGen.asPretty(material))
    }

    private fun ingredientMatch(ingredients: List<Material>,vararg recipe: Material) : Boolean{
        if (recipe.size != 3) return false
        return ingredients.contains(recipe[0]) && ingredients.contains(recipe[1]) && ingredients.contains(recipe[2])
    }
}