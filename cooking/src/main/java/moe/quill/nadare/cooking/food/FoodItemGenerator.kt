package moe.quill.nadare.cooking.food

import moe.quill.nadare.cooking.core.EWCampfire
import moe.quill.nadare.cooking.food.recipe.Cookable
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

class FoodItemGenerator() {

    private val adjMap = mapOf<Int, String>(
        1 to "Small ",
        2 to "Medium ",
        3 to "Large "
    )

    fun generateFoodItem(campfire: EWCampfire): ItemStack? {
        val ingredients = campfire.ingredients
        if (ingredients.isEmpty()) return null

        val stew = ItemStack(Material.SUSPICIOUS_STEW)
        val food = ItemStack(Material.RABBIT_STEW)

        val item = if (campfire.ingredients.contains(Cookable.valueOf(Material.POTION))) stew else food
        val itemMeta = item.itemMeta
        var lore = mutableListOf<Component>()

        lore += Component.text("Created From: ").color(NamedTextColor.WHITE)

        ingredients.forEach {
            if (it != null) {
                if (it.material == Material.POTION) Component.text("Water").color(NamedTextColor.WHITE)
                else lore += prettify(it.material).color(NamedTextColor.WHITE)
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