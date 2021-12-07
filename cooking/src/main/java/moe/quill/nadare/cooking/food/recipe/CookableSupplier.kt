package moe.quill.nadare.cooking.food.recipe

interface CookableSupplier {
    val cookables: List<Cookable>

    fun matches(cookable: Cookable) : Boolean{
        return cookables.contains(cookable)
    }

    fun matchesOne(cookables: List<Cookable?>) : Boolean{
        cookables.forEach {
            it ?: return@forEach
            if(cookables.contains(it)) return true
        }
        return false
    }
}