package moe.quill.nadare.cooking.temperature

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import java.util.*

class WeatherChannel {

    private val breakEventHeat = 5
    private val heatSources = mapOf<Material, HeatData>(
        Material.CAMPFIRE to HeatData(8.0, 40.0),
        Material.SOUL_CAMPFIRE to HeatData(7.0, 50.0),

        Material.LANTERN to HeatData(4.0, 20.0),
        Material.SOUL_LANTERN to HeatData(3.0, 25.0),

        Material.TORCH to HeatData(2.0, 10.0),
        Material.WALL_TORCH to HeatData(2.0, 10.0),
        Material.SOUL_TORCH to HeatData(2.0, 15.0),
        Material.SOUL_WALL_TORCH to HeatData(2.0, 15.0),

        Material.FIRE to HeatData(4.0, 20.0),
        Material.SOUL_FIRE to HeatData(3.0, 30.0),

        Material.LAVA to HeatData(3.0, 40.0),
        Material.MAGMA_BLOCK to HeatData(3.0, 30.0),

        Material.WATER to HeatData(0.0, -50.0),
        Material.POWDER_SNOW to HeatData(0.0, -15.0),
    ).toList().sortedBy { (material, data) -> data.range }.toMap()

    fun getFreezeDelta(uuid: UUID): Int {
        var player = Bukkit.getPlayer(uuid) ?: return 0
        if (player.world.environment == World.Environment.NETHER) return 100
        var location = player.location

        var warmth = 0

        if (player.isVisualFire) warmth += 2 * breakEventHeat

        warmth += getHeatSource(player)

        player.inventory.armorContents.forEach {
            if (it == null) return@forEach
            val name = it.type.name.lowercase()
            if (name.contains("leather")) warmth++
            if (name.contains("netherite")) warmth++
        }

        Bukkit.getLogger()
            .info("Env: ${getEnvironmentFactor(location)} Warmth: ${warmth}  Temp: ${getEnvironmentFactor(location) - warmth}")
        return getEnvironmentFactor(location) - warmth
    }

    private fun getEnvironmentFactor(location: Location): Int {
        val heightTemp = when (location.y.toInt()) {
            in 0..60 -> 0
            in 60..100 -> 1
            in 100..140 -> 2
            in 140..180 -> 3
            in 180..500 -> 5
            else -> -5
        }
        val timeTemp = when (location.world.time) {
            in 1000..5999 -> 1
            in 6000..11999 -> 3
            in 12000..18999 -> 6
            in 19000..22999 -> 5
            else -> 2
        }
        val blockLoc = location.block.location
        val roomTemp = if((location.world.getHighestBlockAt(blockLoc).location.y <= (blockLoc.y - 1).toInt())) 2 else 0
        return heightTemp + timeTemp + roomTemp
    }

    private fun getHeatSource(player: Player): Int {
        val type = player.location.block.type
        if (type == Material.WATER || type == Material.POWDER_SNOW) {
            return heatSources[type]?.relativeHeat(0.0) ?: return 0
        }
        if (player.location.block.lightLevel < 4) return 0
        val range = heatSources.toList().last().second.range.toInt()

        var blocks = mutableListOf<Block>()
        for (x in -range..range) {
            for (y in -range..range) {
                for (z in -range..range) {
                    val block = player.location.clone().add(x.toDouble(), y.toDouble(), z.toDouble()).block
                    val source = heatSources[block.type] ?: continue
                    if (player.location.distance(block.location) > source.range) continue
                    blocks += block
                }
            }
        }
        if (blocks.isEmpty()) return 0
        val source =
            blocks.sortedBy { heatSources[it.type]?.relativeHeat(player.location.distance(it.location)) }.toList()
                .last()
        val distance = player.location.distance(source.location)
        //Bukkit.getLogger().info("distance to %s is %s".format(source.type.name, distance))
        return heatSources[source.type]?.relativeHeat(distance) ?: return 0
    }
}