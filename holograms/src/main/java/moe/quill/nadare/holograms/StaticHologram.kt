package moe.quill.nadare.holograms

import moe.quill.nadare.entries.Entry
import org.bukkit.Location
import org.bukkit.entity.AreaEffectCloud

class StaticHologram(override val location: Location, override val offset: Float = .27f) : Hologram {
    override val entries = mutableListOf<Entry>()
    override val entities = mutableListOf<AreaEffectCloud>()
}