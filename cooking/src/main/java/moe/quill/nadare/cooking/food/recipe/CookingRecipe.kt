package moe.quill.nadare.cooking.food.recipe

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit

enum class CookingRecipe(val prefix: Component, val description: List<Component>, vararg choices: CookingChoice) {
    GILDED(
        Component.text("Gilded"),
        listOf(
            Component.text("Gilded:").color(NamedTextColor.GOLD)
        ),
        CookingChoice(CookingGroup.GOLDEN)
    ),
    OVERCOOKED(
        Component.text("Overcooked"),
        listOf(
            Component.text("Overcooked:").color(NamedTextColor.GRAY)
        ),
        CookingChoice(CookingGroup.COOKED)
    ),
    VEGETARIAN(
        Component.text("Vegetarian"),
        listOf(
            Component.text("Vegetarian:")
        ),
        CookingChoice(CookingGroup.VEGETABLE),
        CookingChoice(CookingGroup.VEGETABLE),
        CookingChoice(CookingGroup.VEGETABLE)
    ),
    BALANCED(
        Component.text("Balanced"),
        listOf(
            Component.text("Balanced:").color(NamedTextColor.BLUE)
        ),
        CookingChoice(CookingGroup.MEAT),
        CookingChoice(CookingGroup.VEGETABLE, CookingGroup.PLANT),
        CookingChoice(CookingGroup.FRUIT)
    ),
    BROTHY(
        Component.text("Brothy"),
        listOf(
            Component.text("Brothy:").color(NamedTextColor.GREEN)
        ),
        CookingChoice(CookingGroup.WATER)
    ),
    GROSS(
        Component.text("Gross"),
        listOf(
            Component.text("Gross:").color(NamedTextColor.GRAY)
        ),
        CookingChoice(CookingGroup.SPOILED)
    );

    val choices = choices.toList()

    companion object {

        @JvmStatic
        fun findMatches(cookable: List<Cookable?>): List<CookingRecipe> {
            val validCookables = cookable.filterNotNull()
            return values().filter { recipe ->
                Bukkit.getLogger().info("Checking ${recipe.name}")
                recipe.choices.forEach { choice ->
                    if (choice.matchesAny(validCookables)) return@filter true
                }
                return@filter false
            }
        }

//        @JvmStatic
//        fun findMatches(ingredients: List<Cookable?>) : List<CookingRecipe> {
//            var recipies = mutableListOf<CookingRecipe>()
//            values().forEach {
//                var test = true
//                it.choices.forEach loop@{choice ->
//                    if (!choice.matchesOne(ingredients)) {
//                        test = false
//                        return@loop
//                    }
//                }
//                Bukkit.getLogger().info("Testing ${it.name}: ${test}")
//                if(test) recipies.add(it)
//            }
//            return recipies
//        }
    }
}