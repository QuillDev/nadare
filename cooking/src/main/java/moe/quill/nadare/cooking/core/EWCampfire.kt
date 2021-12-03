package moe.quill.nadare.cooking.core

import com.destroystokyo.paper.profile.ProfileProperty
import org.bukkit.*
import org.bukkit.block.data.type.Campfire
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.util.*
import kotlin.collections.ArrayList

class EWCampfire (
    val location: Location,
    val blockData: Campfire,
    private var hasPot: Boolean) {

    var pot: ArmorStand? = null
    val SIZE: Int = 3
    var ingredients: MutableList<Material?> = ArrayList()

    val listener: CampfireListener = CampfireListener(this)

    fun addIngredient(player: Player){
        if(ingredients.size == SIZE) return

        val item = player.inventory.itemInMainHand
        ingredients.add(item.type)

        if(item.amount > 1) item.subtract()
        else player.inventory.remove(item)

        val world = location.world

        val litSound = Sound.BLOCK_FIRE_AMBIENT
        val unlitSound = Sound.BLOCK_HONEY_BLOCK_BREAK
        world.playSound(location, (if((location.block.blockData as Campfire).isLit) litSound else unlitSound), 1f, 1f)

        val rand = Random()

        world.spawnParticle(Particle.CRIT_MAGIC, Location(world, location.x, location.y + 1, location.z), 20,
            -1 + rand.nextDouble(1.0),
            -1 + rand.nextDouble(1.0),
            -1 + rand.nextDouble(1.0)
        )
    }

    fun create() : EWCampfire {
        //block stuff
        location.block.blockData = blockData

        //armor stand stuff
        if(!hasPot) return this
        addPot()

        return this
    }

    fun addPot(){
        this.pot = location.world.spawnEntity(
            Location(location.world, location.x + .5, location.y - 1, location.z + .5),
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
    }

    fun destroy(){
        pot?.isInvulnerable = false
        pot?.remove()
        if(hasPot) location.world.dropItemNaturally(location, ItemStack(Material.CAULDRON))
        location.block.breakNaturally()
    }

}