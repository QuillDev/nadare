package moe.quill.nadare.cooking.food.recipe

class CookingChoice(vararg cookables: Cookable) {
    val cookables = cookables.toList()

    fun matchesAny(cookables: List<Cookable?>): Boolean {

        cookables.filterNotNull().forEach { cookable ->
            this.cookables.forEach { localCookable ->
                if (localCookable.matchesAny(cookable)) return true
            }
        }
        return false
    }

}