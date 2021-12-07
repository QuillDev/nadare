package moe.quill.nadare.cooking.food.recipe

import org.bukkit.Material

class CookingChoice(vararg cookables: CookableSupplier) {
    val cookables = cookables.toList()

    fun matches(ingredients: List<Cookable>) : Boolean{
        var matches = false
        ingredients.forEach{
            if (cookables.contains(it)) {
                matches = true
                return@forEach
            }
        }
        return matches
    }
}