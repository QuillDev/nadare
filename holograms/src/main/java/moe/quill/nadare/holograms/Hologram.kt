package moe.quill.nadare.holograms

import moe.quill.nadare.entries.Entry
import org.bukkit.*
import org.bukkit.entity.AreaEffectCloud
import org.bukkit.entity.EntityType
import org.bukkit.event.entity.CreatureSpawnEvent

interface Hologram {
    //Setup
    val location: Location
    val entries: MutableList<Entry>
    val offset: Float

    //Entity Management
    val entities: MutableList<AreaEffectCloud>

    fun addEntry(entry: Entry) {
        entries += entry
        update()
    }

    fun removeEntry(entry: Entry) {
        entries -= entry
        update()
    }

    fun update() {
        //IF we have no entries don't really do anything
        if (entries.isEmpty()) {
            if (entities.isEmpty()) return
            entities.forEach { it.remove() }
            return
        }

        //Ensure both lists are the same size
        while (entities.size < entries.size) {
            entities += spawnLine(location.clone().add(0.0, (entities.size * offset).toDouble(), 0.0))
        }
        while (entities.size > entries.size) {
            val stale = entities[entities.size - 1]
            entities -= stale
            stale.remove()
        }

        for (idx in 0 until entries.size) {
            entities[idx].customName(entries[idx].value)
        }
    }

    private fun spawnLine(location: Location): AreaEffectCloud {
        return location.world.spawnEntity(
            location,
            EntityType.AREA_EFFECT_CLOUD,
            CreatureSpawnEvent.SpawnReason.CUSTOM
        ) {
            val line = it as AreaEffectCloud
            line.setParticle(Particle.BLOCK_CRACK, Bukkit.createBlockData(Material.AIR))
            line.ticksLived = Int.MAX_VALUE
            line.radius = 0f
        } as AreaEffectCloud
    }

    fun destroy() {
        entities.forEach { it.remove() }
    }
}