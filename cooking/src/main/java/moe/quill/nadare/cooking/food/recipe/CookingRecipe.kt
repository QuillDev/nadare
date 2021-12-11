package moe.quill.nadare.cooking.food.recipe

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import java.text.CollationKey
import javax.naming.Name

enum class CookingRecipe(val prefix: Component, val description: List<Component>, vararg choices: CookingChoice) {
    GILDED(
        Component.text("Gilded"),
        listOf(
            Component.text("Gilded:").color(NamedTextColor.GREEN),
            Component.text("Grants a short regen effect.").color(NamedTextColor.WHITE),
        ),
        CookingChoice(CookingGroup.GOLDEN)
    ),
    OVERCOOKED(
        Component.text("Overcooked"),
        listOf(
            Component.text("Overcooked:").color(NamedTextColor.RED),
            Component.text("Applies a short slowness effect. ").color(NamedTextColor.WHITE)
        ),
        CookingChoice(CookingGroup.COOKED)
    ),
    VEGGIE(
        Component.text("Veggie"),
        listOf(
            Component.text("Veggie:").color(NamedTextColor.GREEN),
            Component.text("Does nothing but make you better ").color(NamedTextColor.WHITE),
            Component.text("than others. ").color(NamedTextColor.WHITE)
        ),
        CookingChoice(CookingGroup.VEGETABLE),
        CookingChoice(CookingGroup.VEGETABLE),
        CookingChoice(CookingGroup.VEGETABLE)
    ),
    BALANCED(
        Component.text("Balanced"),
        listOf(
            Component.text("Balanced:").color(NamedTextColor.BLUE),
            Component.text("Grants you bonus saturation, keeping ").color(NamedTextColor.WHITE),
            Component.text("you full for longer. ").color(NamedTextColor.WHITE),
        ),
        CookingChoice(CookingGroup.MEAT),
        CookingChoice(CookingGroup.VEGETABLE, CookingGroup.PLANT),
        CookingChoice(CookingGroup.FRUIT)
    ),
    BROTHY(
        Component.text("Brothy"),
        listOf(
            Component.text("Brothy:").color(NamedTextColor.GREEN),
            Component.text("Grants a short fire resistance effect, ").color(NamedTextColor.WHITE),
            Component.text("which also helps you stay warm! ").color(NamedTextColor.WHITE),
        ),
        CookingChoice(CookingGroup.WATER),
        CookingChoice(CookingGroup.BONE, CookingGroup.EGG)
    ),
    GROSS(
        Component.text("Gross"),
        listOf(
            Component.text("Gross:").color(NamedTextColor.RED),
            Component.text("Applies a short nausea effect.").color(NamedTextColor.WHITE),
        ),
        CookingChoice(CookingGroup.SPOILED)
    ),
    EXTRA_SWEET(
        Component.text("Extra-Sweet"),
        listOf(
            Component.text("Extra-Sweet:").color(NamedTextColor.GREEN),
            Component.text("Grants a short speed effect.").color(NamedTextColor.WHITE)
        ),
        CookingChoice(CookingGroup.SWEET),
        CookingChoice(CookingGroup.SWEET)
    ),
    SOGGY(
        Component.text("Soggy"),
        listOf(
            Component.text("Soggy:").color(NamedTextColor.GREEN),
            Component.text("Does nothing but make you rethink").color(NamedTextColor.WHITE),
            Component.text("your choices.").color(NamedTextColor.WHITE),

        ),
        CookingChoice(CookingGroup.BAKED),
        CookingChoice(CookingGroup.WATER)
    ),
    BREAKFASTY(
        Component.text("Breakfasty"),
        listOf(
            Component.text("Breakfasty:").color(NamedTextColor.GREEN),
            Component.text("Grants a short jump boost effect ").color(NamedTextColor.WHITE),
            Component.text("but only if you eat it in the morning.").color(NamedTextColor.WHITE),
        ),
        CookingChoice(CookingGroup.EGG),
        CookingChoice(Ingredient.PORKCHOP, Ingredient.COOKED_PORKCHOP, CookingGroup.FRUIT),
        CookingChoice(CookingGroup.BAKED, Ingredient.MILK, Ingredient.WHEAT, CookingGroup.SEEDS)
    ),
    CARNIVOROUS(
        Component.text("Carnivorous"),
        listOf(
            Component.text("Carnivorous:").color(NamedTextColor.GREEN),
            Component.text("Grants a short strength effect.").color(NamedTextColor.WHITE)
        ),
        CookingChoice(CookingGroup.MEAT),
        CookingChoice(CookingGroup.MEAT),
        CookingChoice(CookingGroup.MEAT)
    ),
    CREAMY(
        Component.text("Creamy"),
        listOf(
            Component.text("Creamy:").color(NamedTextColor.BLUE),
            Component.text("Removes any negative status effects.").color(NamedTextColor.WHITE),
        ),
        CookingChoice(Ingredient.MILK),
        CookingChoice(CookingGroup.MUSHROOM, Ingredient.WHEAT, CookingGroup.EGG)
    ),
    CARAMELIZED(
        Component.text("Caramelized"),
        listOf(
            Component.text("Caramelized:").color(NamedTextColor.LIGHT_PURPLE),
            Component.text("Slows all nearby entities.").color(NamedTextColor.WHITE),
        ),
        CookingChoice(Ingredient.SUGAR),
        CookingChoice(CookingGroup.MEAT, CookingGroup.MUSHROOM),
        CookingChoice(CookingGroup.MEAT, CookingGroup.MUSHROOM)
    ),
    EARTHY(
        Component.text("Earthy"),
        listOf(
            Component.text("Earthy:").color(NamedTextColor.BLUE),
            Component.text("Grants a short effect that removes ").color(NamedTextColor.WHITE),
            Component.text("snow and applies bonemeal to grass ").color(NamedTextColor.WHITE),
            Component.text("that you walk over.").color(NamedTextColor.WHITE)
        ),
        CookingChoice(CookingGroup.MUSHROOM),
        CookingChoice(CookingGroup.ROOT)
    ),
    FISHY(
        Component.text("Fishy"),
        listOf(
            Component.text("Fishy:").color(NamedTextColor.GREEN)
        ),
        CookingChoice(CookingGroup.FISH),
        CookingChoice(CookingGroup.SUSHI)
    ),
    FRUITY(
        Component.text("Fruity"),
        listOf(
            Component.text("Fruity:").color(NamedTextColor.GREEN)
        ),
        CookingChoice(CookingGroup.FRUIT),
        CookingChoice(CookingGroup.FRUIT),
        CookingChoice(CookingGroup.FRUIT)
    ),
    OATMEALIE(
        Component.text("Oatmealie"),
        listOf(
            Component.text("Oatmealie:").color(NamedTextColor.LIGHT_PURPLE)
        ),
        CookingChoice(Ingredient.MILK),
        CookingChoice(Ingredient.WHEAT),
        CookingChoice(CookingGroup.FRUIT, CookingGroup.SEEDS)
    ),
    NOODLIE(
        Component.text("Noodlie"),
        listOf(
            Component.text("Noodlie:").color(NamedTextColor.BLUE)
        ),
        CookingChoice(Ingredient.WHEAT),
        CookingChoice(CookingGroup.EGG)
    ),
    EXOTIC(
        Component.text("Exotic"),
        listOf(
            Component.text("Exotic:").color(NamedTextColor.GOLD)
        ),
        CookingChoice(CookingGroup.GOLDEN),
        CookingChoice(CookingGroup.SHELLS, CookingGroup.ROOT, CookingGroup.PLANT),
        CookingChoice(CookingGroup.SHELLS, CookingGroup.ROOT, CookingGroup.PLANT)
    ),
    ENCHANTED(
        Component.text("Enchanted"),
        listOf(
            Component.text("Enchanted:")
        ),
        CookingChoice(Ingredient.ENCHANTED_GOLDEN_APPLE)
    );


    val choices = choices.toList()

    companion object {
        @JvmStatic
        fun findMatches(cookables: List<Cookable?>): List<CookingRecipe> {
            val validCookables = cookables.filterNotNull()
            return values().filter { recipe ->
                var choiceMap = mutableMapOf<CookingChoice, Cookable?>()
                val validCookablesCopy = validCookables.toMutableList()
                recipe.choices.forEach { choice ->
                    choiceMap[choice] = null
                    if(validCookablesCopy.isEmpty()) return@filter !choiceMap.containsValue(null)
                    var toBeRemoved = validCookablesCopy.first()
                    validCookablesCopy.forEach cookable@{ cookable ->
                        if (choice.matchesAny(listOf(cookable)) && choiceMap[choice] == null) {
                            choiceMap[choice] = cookable
                            toBeRemoved = cookable
                            return@cookable
                        }
                    }
                    validCookablesCopy.remove(toBeRemoved)
                }
                return@filter !choiceMap.containsValue(null)
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