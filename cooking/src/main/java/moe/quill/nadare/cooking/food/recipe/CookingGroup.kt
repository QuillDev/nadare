package moe.quill.nadare.cooking.food.recipe

enum class CookingGroup(vararg cookables: Cookable) : CookableSupplier{
    GOLDEN(Cookable.GOLDEN_APPLE,Cookable.GOLDEN_CARROT, Cookable.ENCHANTED_GOLDEN_APPLE),
    MEAT(
        Cookable.COD, Cookable.SALMON, Cookable.BEEF, Cookable.CHICKEN,
        Cookable.MUTTON, Cookable.RABBIT, Cookable.PORKCHOP, Cookable.TROPICAL_FISH
    ),
    COOKED(
        Cookable.COOKED_COD, Cookable.COOKED_SALMON, Cookable.COOKED_BEEF, Cookable.COOKED_CHICKEN,
        Cookable.COOKED_MUTTON, Cookable.COOKED_RABBIT, Cookable.BAKED_POTATO, Cookable.COOKED_PORKCHOP
    ),
    VEGETABLE(
        Cookable.POTATO, Cookable.CARROT, Cookable.BEETROOT, Cookable.DRIED_KELP,
        Cookable.CACTUS, Cookable.BAMBOO, Cookable.WARPED_FUNGUS, Cookable.CRIMSON_FUNGUS,
        Cookable.BROWN_MUSHROOM, Cookable.RED_MUSHROOM, Cookable.KELP, Cookable.SEA_PICKLE,
        Cookable.SEAGRASS, Cookable.PUMPKIN, Cookable.CRIMSON_ROOTS, Cookable.HANGING_ROOTS,
        Cookable.WARPED_ROOTS
    ),
    FRUIT(Cookable.CHORUS_FRUIT, Cookable.SWEET_BERRIES, Cookable.GLOW_BERRIES, Cookable.MELON_SLICE, Cookable.POPPED_CHORUS_FRUIT),
    SPOILED(Cookable.ROTTEN_FLESH, Cookable.POISONOUS_POTATO, Cookable.SPIDER_EYE, Cookable.FERMENTED_SPIDER_EYE),
    BAKED(Cookable.COOKIE, Cookable.BREAD, Cookable.BAKED_POTATO, Cookable.CAKE),
    FISH(Cookable.COD, Cookable.SALMON, Cookable.PUFFERFISH, Cookable.TROPICAL_FISH),
    BERRIES(Cookable.GLOW_BERRIES, Cookable.SWEET_BERRIES),
    SWEET(
        Cookable.GLOW_BERRIES, Cookable.SWEET_BERRIES, Cookable.HONEY_BOTTLE, Cookable.MELON_SLICE,
        Cookable.APPLE, Cookable.PUMPKIN_PIE, Cookable.SUGAR_CANE, Cookable.SUGAR, Cookable.CAKE
    ),
    EGG(Cookable.EGG, Cookable.DRAGON_EGG, Cookable.TURTLE_EGG),
    WATER(Cookable.ICE, Cookable.BLUE_ICE, Cookable.PACKED_ICE, Cookable.SNOWBALL, Cookable.POTION),
    MUSHROOM(Cookable.CRIMSON_FUNGUS, Cookable.WARPED_FUNGUS, Cookable.RED_MUSHROOM, Cookable.BROWN_MUSHROOM),
    ROOT(Cookable.WARPED_ROOTS, Cookable.HANGING_ROOTS, Cookable.CRIMSON_ROOTS, Cookable.BEETROOT),
    SHELLS(Cookable.NAUTILUS_SHELL, Cookable.SHULKER_SHELL, Cookable.SCUTE),
    PLANT(
        Cookable.KELP, Cookable.DRIED_KELP, Cookable.SEAGRASS, Cookable.BAMBOO,
        Cookable.WARPED_ROOTS, Cookable.HANGING_ROOTS, Cookable.CRIMSON_ROOTS, Cookable.BEETROOT,
        Cookable.CACTUS, Cookable.SEA_PICKLE,Cookable.SUGAR_CANE
    );

    override val cookables = cookables.toList()
}