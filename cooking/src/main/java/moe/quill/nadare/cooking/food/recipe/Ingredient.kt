package moe.quill.nadare.cooking.food.recipe

import org.bukkit.Material


enum class Ingredient(val material: Material, val saturation: Double) : Cookable {
    GOLDEN_CARROT(Material.GOLDEN_CARROT, 2.4),
    GOLDEN_APPLE(Material.GOLDEN_APPLE, 2.4),
    ENCHANTED_GOLDEN_APPLE(Material.ENCHANTED_GOLDEN_APPLE, 2.4),

    COOKED_MUTTON(Material.COOKED_MUTTON, 1.6 /2),
    COOKED_PORKCHOP(Material.COOKED_PORKCHOP, 1.6 /2),
    COOKED_SALMON(Material.COOKED_SALMON, 1.6 /2),
    COOKED_BEEF(Material.COOKED_BEEF, 1.6 /2),

    BAKED_POTATO(Material.BAKED_POTATO, 1.2 /2),
    BEETROOT(Material.BEETROOT, 1.2),
    BREAD(Material.BREAD, 1.2),
    CARROT(Material.CARROT, 1.2),
    COOKED_CHICKEN(Material.COOKED_CHICKEN, 1.2 /2),
    COOKED_COD(Material.COOKED_COD, 1.2 /2),
    COOKED_RABBIT(Material.COOKED_RABBIT, 1.2 /2),

    APPLE(Material.APPLE, .6),
    CHORUS_FRUIT(Material.CHORUS_FRUIT, .6),
    POPPED_CHORUS_FRUIT(Material.POPPED_CHORUS_FRUIT, .6),
    DRIED_KELP(Material.DRIED_KELP, .6),
    MELON_SLICE(Material.MELON_SLICE, .6),
    POISONOUS_POTATO(Material.POISONOUS_POTATO, .6),
    POTATO(Material.POTATO, .6),
    PUMPKIN_PIE(Material.PUMPKIN_PIE, .6),
    BEEF(Material.BEEF, .6),
    CHICKEN(Material.CHICKEN, .6),
    MUTTON(Material.MUTTON, .6),
    PORKCHOP(Material.PORKCHOP, .6),
    RABBIT(Material.RABBIT, .6),
    SWEET_BERRIES(Material.SWEET_BERRIES, .6),

    COOKIE(Material.COOKIE, .2),
    GLOW_BERRIES(Material.GLOW_BERRIES, .2),
    HONEY_BOTTLE(Material.HONEY_BOTTLE, .2),
    PUFFERFISH(Material.PUFFERFISH, .2),
    COD(Material.COD, .2),
    SALMON(Material.SALMON, .2),
    ROTTEN_FLESH(Material.ROTTEN_FLESH, .2),
    SPIDER_EYE(Material.SPIDER_EYE, .2),
    TROPICAL_FISH(Material.TROPICAL_FISH, .2),

    WARPED_ROOTS(Material.WARPED_ROOTS, .3),
    CRIMSON_ROOTS(Material.CRIMSON_ROOTS, .3),
    HANGING_ROOTS(Material.HANGING_ROOTS, .3),
    SEA_PICKLE(Material.SEA_PICKLE, .6),
    FERMENTED_SPIDER_EYE(Material.FERMENTED_SPIDER_EYE, .2),

    PUMPKIN_SEEDS(Material.PUMPKIN_SEEDS, .2),
    WHEAT_SEEDS(Material.WHEAT_SEEDS, .2),
    MELON_SEEDS(Material.MELON_SEEDS, .2),
    BEETROOT_SEEDS(Material.BEETROOT_SEEDS, .2),

    BONE(Material.BONE, .3),
    BONE_MEAL(Material.BONE_MEAL, .1),
    SUGAR(Material.SUGAR, .2),
    SUGAR_CANE(Material.SUGAR_CANE, .2),
    MILK(Material.MILK_BUCKET, .2),

    PACKED_ICE(Material.PACKED_ICE, .0),
    BLUE_ICE(Material.BLUE_ICE, .0),
    ICE(Material.ICE, .0),
    SNOWBALL(Material.SNOWBALL, .0),
    POTION(Material.POTION, .0),

    NAUTILUS_SHELL(Material.NAUTILUS_SHELL, .2),
    SCUTE(Material.SCUTE, .2),
    SHULKER_SHELL(Material.SHULKER_SHELL, .2),

    CAKE(Material.CAKE, .6),
    PUMPKIN(Material.PUMPKIN, .6),
    CACTUS(Material.CACTUS, .3),
    BAMBOO(Material.BAMBOO, .3),
    WHEAT(Material.WHEAT, .3),

    BROWN_MUSHROOM(Material.BROWN_MUSHROOM, .3),
    RED_MUSHROOM(Material.RED_MUSHROOM, .3),
    CRIMSON_FUNGUS(Material.CRIMSON_FUNGUS, .3),
    WARPED_FUNGUS(Material.WARPED_FUNGUS, .3),

    SEAGRASS(Material.SEAGRASS, .2),
    KELP(Material.KELP, .2),

    EGG(Material.EGG, .6),
    DRAGON_EGG(Material.DRAGON_EGG, 5.0),
    TURTLE_EGG(Material.TURTLE_EGG, 5.0),

    GLISTENING_MELON(Material.GLISTERING_MELON_SLICE, .3),
    WATER_BUCKET(Material.WATER_BUCKET, .0);

    override val ingredients = listOf(this)

    companion object{
        @JvmStatic
        fun valueOf(material: Material) : Ingredient?{
           values().forEach {
                if(it.material == material) return it
            }
            return null
        }
    }

}