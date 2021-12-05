package moe.quill.nadare.cooking.temperature

import moe.quill.nadare.cooking.core.CampfireManager
import org.bukkit.*
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.inventory.ItemStack
import java.util.*
import kotlin.math.absoluteValue

class TempHandler(private val campfireManager: CampfireManager) {
    private val freezeStates = mutableMapOf<UUID, Int>()
    val freezeSaturation = 10.0
    private val maxFreezeTicks = (140 * freezeSaturation).toInt()

    fun setFreezeTicks(uuid: UUID, amount: Int) {
        freezeStates[uuid] = amount.coerceAtMost(maxFreezeTicks).coerceAtLeast(0)
        val player = Bukkit.getPlayer(uuid) ?: return

        val freezeTicks = getFreezeTicks(uuid)
        if(freezeTicks >= maxFreezeTicks) {
            player.damage(1.0)
            return
        }
        if(amount > 0 && freezeTicks > maxFreezeTicks * .9) player.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 0.5f, 0.1f)

    }

    fun modifyFreezeTicks(uuid: UUID, amount: Int) {
        setFreezeTicks(uuid, getFreezeTicks(uuid) + amount)
    }

    fun getFreezeTicks(uuid: UUID): Int {
        return freezeStates.getOrDefault(uuid, 0)
    }

}