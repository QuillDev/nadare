package moe.quill.nadare.cooking.core

import com.destroystokyo.paper.profile.ProfileProperty
import moe.quill.nadare.bukkitcommon.lib.ModuleBase
import moe.quill.nadare.bukkitcommon.lib.keys.KeyManager
import moe.quill.nadare.cooking.food.FoodItemGenerator
import moe.quill.nadare.cooking.food.recipe.Ingredient
import moe.quill.nadare.cooking.util.PrettyNameGen
import moe.quill.nadare.entries.DynamicEntry
import moe.quill.nadare.entries.StaticEntry
import moe.quill.nadare.holograms.DynamicHologram
import moe.quill.nadare.holograms.Hologram
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.*
import org.bukkit.block.data.type.Campfire
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.collections.ArrayList

class EWCampfire(
    private val module: ModuleBase,
    val location: Location,
    val blockData: Campfire,
    private var hasPot: Boolean
) {
    var itemGenerator: FoodItemGenerator? = null

    var pot: ArmorStand? = null
    val SIZE: Int = 3
    var servings = 3
    var ingredients: MutableList<Ingredient?> = ArrayList()

    val listener: CampfireListener = CampfireListener(this)

    private var hologram = initHologram()

    init {
        module.registerListeners(listener)
    }

    private fun initHologram() : Hologram{
        if(hologram != null) hologram.clear()
        val holo = DynamicHologram(module.plugin, location.clone().add(.5,.5,.5))
        if(!hasPot) {
            holo.addEntry(StaticEntry(Component.text("Add a cauldron for cooking.")))
            return holo
        }
        holo.addEntry(
            DynamicEntry {
                if (ingredients.isNotEmpty()) Component.text("Servings remaining: ${servings}")
                else Component.text()
                    .append(Component.text("[").color(NamedTextColor.DARK_GRAY))
                    .append(Component.keybind("key.use").color(NamedTextColor.GREEN))
                    .append(Component.text("]").color(NamedTextColor.DARK_GRAY))
                    .append(Component.text(" to add ingredients.").color(NamedTextColor.WHITE))
                    .build()
            },
            DynamicEntry{prettify(ingredients.getOrNull(0))},
            DynamicEntry{prettify(ingredients.getOrNull(1))},
            DynamicEntry{prettify(ingredients.getOrNull(2))}
        )
        return holo
    }

    fun prettify(cookable: Ingredient?) : Component{
        cookable?: return Component.text("[Empty Ingredient Slot]")
        return if (cookable.material == Material.POTION) Component.text("Water").color(NamedTextColor.WHITE)
        else Component.text(PrettyNameGen.asPretty(cookable.material))
    }


    fun addIngredient(player: Player) {
        if (ingredients.size == SIZE) return

        val item = player.inventory.itemInMainHand
        ingredients.add(Ingredient.valueOf(item.type))

        item.subtract()

        val world = location.world

        val litSound = Sound.BLOCK_HONEY_BLOCK_PLACE
        val unlitSound = Sound.BLOCK_HONEY_BLOCK_BREAK
        world.playSound(location, (if ((location.block.blockData as Campfire).isLit) litSound else unlitSound), 1f, 1f)

        val rand = Random()

        world.spawnParticle(
            Particle.CRIT_MAGIC, Location(world, location.x, location.y + 1, location.z), 20,
            -1 + rand.nextDouble(1.0),
            -1 + rand.nextDouble(1.0),
            -1 + rand.nextDouble(1.0)
        )
    }

    fun create(): EWCampfire {
        //block stuff
        location.block.blockData = blockData

        //armor stand stuff
        if (!hasPot) return this
        addPot()

        return this
    }

    fun addPot() {
        this.pot = location.world.spawnEntity(
            location.clone().add(.5,-1.0,.5),
            EntityType.ARMOR_STAND,
            CreatureSpawnEvent.SpawnReason.CUSTOM
        ) {
            val armorStand = it as ArmorStand
            armorStand.isInvulnerable = true
            armorStand.isVisible = false
            armorStand.setGravity(false)

            val head = ItemStack(Material.PLAYER_HEAD)
            val skullMeta = head.itemMeta as SkullMeta

            val profile = Bukkit.createProfile(UUID.randomUUID(), null)
            val propertyMap = profile.properties
            propertyMap.add(
                ProfileProperty(
                    "textures",
                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTZjMGEyMjU3Y2UwMzQyMjA5NTg2MWMzMTE0NTUwYzg5YmQzYzkyMzk5NmExMzc1NDdlMDY3MjMyZGU1ZDU2MiJ9fX0="
                )
            )
            skullMeta.playerProfile = profile
            head.itemMeta = skullMeta

            armorStand.equipment.helmet = head
        } as ArmorStand
        hasPot = true
        hologram = initHologram()
    }

    fun destroy() {
        removePot()
        location.block.breakNaturally()
        hologram.destroy()
    }

    fun removePot() {
        pot?.isInvulnerable = false
        pot?.remove()
        if (!hasPot) return
        location.world.dropItemNaturally(location, ItemStack(Material.CAULDRON)).isInvulnerable = true
        hasPot = false
        hologram = initHologram()
    }

    fun grabServing(player: Player) {
        if (ingredients.size == 0 || servings == 0) {
            player.sendActionBar(Component.text("That's just an empty pot!").color(NamedTextColor.GREEN))
            return
        }
        if (!(location.block.blockData as Campfire).isLit) {
            player.sendActionBar(Component.text("This food seems a little cold...").color(NamedTextColor.GREEN))
            return
        }
        val world = player.world

        itemGenerator?.let {
            val item = it.generateFoodItem(this) ?: return
            world.dropItemNaturally(location, item).isInvulnerable = true
        }

        servings -= 1

        location.world.playSound(location, Sound.BLOCK_HONEY_BLOCK_BREAK, 1f, 1f)

        if (servings != 0) return
        ingredients = ArrayList()
        servings = 3
    }

}