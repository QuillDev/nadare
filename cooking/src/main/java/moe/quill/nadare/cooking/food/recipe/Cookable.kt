package moe.quill.nadare.cooking.food.recipe

interface Cookable {
    val ingredients: List<Ingredient>

    fun matchesAny(cookable: Cookable): Boolean {
        cookable.ingredients.forEach {
            ingredients.forEach { ingredient ->
                if (it.ingredients.contains(ingredient)) return true
            }
        }
        return false
    }
}