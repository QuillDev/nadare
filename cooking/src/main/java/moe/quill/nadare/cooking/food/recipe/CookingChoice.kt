package moe.quill.nadare.cooking.food.recipe

import org.bukkit.Material

class CookingChoice(vararg cookables: CookableSupplier) {
    val cookables = cookables.toList()

    fun matchesOne(ingredients: List<Cookable?>) : Boolean{
        ingredients.forEach{
            it ?: return@forEach
            if (cookables.contains(it)) {
                return true
            }
        }
        return false
    }
}