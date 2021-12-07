package moe.quill.nadare.cooking.food.recipe

import jdk.jfr.Description
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.entity.Player

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
}