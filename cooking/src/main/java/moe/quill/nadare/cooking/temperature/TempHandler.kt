package moe.quill.nadare.cooking.temperature

import moe.quill.nadare.cooking.core.CampfireManager
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import java.util.*

class TempHandler(private val campfireManager: CampfireManager) {
    private val freezeStates = mutableMapOf<UUID, Int>()
    val freezeSaturation = 10.0
    private val maxFreezeTicks = (140 * freezeSaturation).toInt()

    private val breakEventHeat = 5
    private val heatSources = mapOf<Material, HeatData>(
        Material.CAMPFIRE to HeatData(8.0, 30.0),
        Material.SOUL_CAMPFIRE to HeatData(6.0, 40.0),
        Material.LANTERN to HeatData(3.0, 10.0),
        Material.FIRE to HeatData(4.0, 20.0),
        Material.SOUL_FIRE to HeatData(3.0, 30.0),
        Material.LAVA to HeatData(2.0, 25.0),
        Material.WATER to HeatData(1.0, -20.0),
    ).toList().sortedBy { (material, data) -> data.range }.toMap()

    fun setFreezeTicks(uuid: UUID, amount: Int) {
        freezeStates[uuid] = amount.coerceAtMost(maxFreezeTicks).coerceAtLeast(0)
    }

    fun modifyFreezeTicks(uuid: UUID, amount: Int) {
        setFreezeTicks(uuid, getFreezeTicks(uuid) + amount)
    }

    fun getFreezeTicks(uuid: UUID): Int {
        return freezeStates.getOrDefault(uuid, 0)
    }

    fun getFreezeDelta(uuid: UUID): Int {
        var player = Bukkit.getPlayer(uuid) ?: return 0
        if (player.world.environment == World.Environment.NETHER) return 100
        var location = player.location
        var warmth = 0

        if (player.isVisualFire) warmth += 2 * breakEventHeat
        if (location.world.getHighestBlockAt(location) != location.block) warmth++
        warmth += getHeatSource(player)
        if (location.world.gameTime < 12000) warmth++

        player.inventory.armorContents.forEach {
            if (it == null) return@forEach
            val name = it.type.name.lowercase()
            if (name.contains("leather")) warmth++
            if (name.contains("netherite")) warmth++
        }

        //Bukkit.getLogger().info("Env: ${getEnvironmentFactor(location)} Warmth: ${warmth}  Temp: ${getEnvironmentFactor(location) - warmth}")
        return getEnvironmentFactor(location) - warmth
    }

    private fun getEnvironmentFactor(location: Location): Int {
        if (location.y < 0) return -1
        if (location.y < 80) return breakEventHeat + 1
        if (location.y < 160) return breakEventHeat + 2
        if (location.y < 240) return breakEventHeat + 3
        if (location.y < 320) return breakEventHeat + 4
        return breakEventHeat + 5
    }

    private fun getHeatSource(player: Player): Int {
        if (player.location.block.type == Material.WATER) return heatSources.get(Material.WATER)?.relativeHeat(.5)
            ?: return 0
        if (player.location.block.lightLevel < 4) return 0
        val range = heatSources.toList().last().second.range.toInt()

        var blocks = mutableListOf<Block>()
        for (x in -range..range) {
            for (y in -range..range) {
                for (z in -range..range) {
                    val block = player.location.clone().add(x.toDouble(), y.toDouble(), z.toDouble()).block
                    val source = heatSources[block.type]?: continue
                    if(player.location.distance(block.location) > source.range) continue
                    blocks += block
                }
            }
        }
        if (blocks.isEmpty()) return 0
        val source = blocks.sortedBy { heatSources[it.type]?.range }.toList().last()
        Bukkit.getLogger().info(source.type.name)
        return heatSources[source.type]?.relativeHeat(player.location.distance(source.location)) ?: return 0
    }

}