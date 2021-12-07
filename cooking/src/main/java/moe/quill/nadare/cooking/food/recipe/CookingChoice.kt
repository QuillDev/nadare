package moe.quill.nadare.cooking.food.recipe

import org.bukkit.Material

class CookingChoice(vararg materials: Material) {
    val materialList = materials.toList()

    fun matches() : Boolean{
        return false
    }
}