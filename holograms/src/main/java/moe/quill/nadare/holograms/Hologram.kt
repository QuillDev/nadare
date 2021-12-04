package moe.quill.nadare.holograms

import moe.quill.nadare.entries.DynamicEntry
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

    fun addEntry(vararg entry: Entry) {
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
            val entry = entries[entries.size - 1 - idx]
            when (entry) {
                is DynamicEntry -> entry.update()
            }

            entities[idx].customName(entry.value)
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
            line.isCustomNameVisible = true
            line.ticksLived = Int.MAX_VALUE
            line.radius = 0f
        } as AreaEffectCloud
    }


    fun clear() {
        entries.clear()
        update()
    }

    fun remove(index: Int) {
        if (index < 0 || index > entries.size - 1) return
        entries.removeAt(index)
        update()
    }

    fun destroy() {
        entities.forEach { it.remove() }
    }
}