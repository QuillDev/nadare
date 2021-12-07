package moe.quill.nadare.cooking.food.recipe

enum class CookingGroup(vararg cookables: Ingredient) : Cookable{
    GOLDEN(Ingredient.GOLDEN_APPLE,Ingredient.GOLDEN_CARROT, Ingredient.ENCHANTED_GOLDEN_APPLE),
    MEAT(
        Ingredient.COD, Ingredient.SALMON, Ingredient.BEEF, Ingredient.CHICKEN,
        Ingredient.MUTTON, Ingredient.RABBIT, Ingredient.PORKCHOP, Ingredient.TROPICAL_FISH
    ),
    COOKED(
        Ingredient.COOKED_COD, Ingredient.COOKED_SALMON, Ingredient.COOKED_BEEF, Ingredient.COOKED_CHICKEN,
        Ingredient.COOKED_MUTTON, Ingredient.COOKED_RABBIT, Ingredient.BAKED_POTATO, Ingredient.COOKED_PORKCHOP
    ),
    VEGETABLE(
        Ingredient.POTATO, Ingredient.CARROT, Ingredient.BEETROOT, Ingredient.DRIED_KELP,
        Ingredient.CACTUS, Ingredient.BAMBOO, Ingredient.WARPED_FUNGUS, Ingredient.CRIMSON_FUNGUS,
        Ingredient.BROWN_MUSHROOM, Ingredient.RED_MUSHROOM, Ingredient.KELP, Ingredient.SEA_PICKLE,
        Ingredient.SEAGRASS, Ingredient.PUMPKIN, Ingredient.CRIMSON_ROOTS, Ingredient.HANGING_ROOTS,
        Ingredient.WARPED_ROOTS
    ),
    FRUIT(Ingredient.CHORUS_FRUIT, Ingredient.SWEET_BERRIES, Ingredient.GLOW_BERRIES, Ingredient.MELON_SLICE, Ingredient.POPPED_CHORUS_FRUIT),
    SPOILED(Ingredient.ROTTEN_FLESH, Ingredient.POISONOUS_POTATO, Ingredient.SPIDER_EYE, Ingredient.FERMENTED_SPIDER_EYE),
    BAKED(Ingredient.COOKIE, Ingredient.BREAD, Ingredient.BAKED_POTATO, Ingredient.CAKE),
    FISH(Ingredient.COD, Ingredient.SALMON, Ingredient.PUFFERFISH, Ingredient.TROPICAL_FISH),
    BERRIES(Ingredient.GLOW_BERRIES, Ingredient.SWEET_BERRIES),
    SWEET(
        Ingredient.GLOW_BERRIES, Ingredient.SWEET_BERRIES, Ingredient.HONEY_BOTTLE, Ingredient.MELON_SLICE,
        Ingredient.APPLE, Ingredient.PUMPKIN_PIE, Ingredient.SUGAR_CANE, Ingredient.SUGAR, Ingredient.CAKE
    ),
    EGG(Ingredient.EGG, Ingredient.DRAGON_EGG, Ingredient.TURTLE_EGG),
    WATER(Ingredient.ICE, Ingredient.BLUE_ICE, Ingredient.PACKED_ICE, Ingredient.SNOWBALL, Ingredient.POTION),
    MUSHROOM(Ingredient.CRIMSON_FUNGUS, Ingredient.WARPED_FUNGUS, Ingredient.RED_MUSHROOM, Ingredient.BROWN_MUSHROOM),
    ROOT(Ingredient.WARPED_ROOTS, Ingredient.HANGING_ROOTS, Ingredient.CRIMSON_ROOTS, Ingredient.BEETROOT),
    SHELLS(Ingredient.NAUTILUS_SHELL, Ingredient.SHULKER_SHELL, Ingredient.SCUTE),
    PLANT(
        Ingredient.KELP, Ingredient.DRIED_KELP, Ingredient.SEAGRASS, Ingredient.BAMBOO,
        Ingredient.WARPED_ROOTS, Ingredient.HANGING_ROOTS, Ingredient.CRIMSON_ROOTS, Ingredient.BEETROOT,
        Ingredient.CACTUS, Ingredient.SEA_PICKLE,Ingredient.SUGAR_CANE
    );

    override val ingredients = cookables.toList()

}