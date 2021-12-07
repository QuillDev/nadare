package moe.quill.nadare.cooking.food.recipe

interface CookableSupplier {
    val cookables: List<Cookable>

    fun matches(cookable: Cookable) : Boolean{
        return cookables.contains(cookable)
    }
}