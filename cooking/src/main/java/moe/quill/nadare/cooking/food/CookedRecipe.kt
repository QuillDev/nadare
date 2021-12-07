package moe.quill.nadare.cooking.food

import net.kyori.adventure.text.Component
import org.bukkit.Material

enum class CookedRecipe(val ingredients: List<Material>, val displayName: Component, val description: List<Component>, val removesBowl: Boolean = true) {

}