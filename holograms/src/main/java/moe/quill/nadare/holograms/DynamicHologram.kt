package moe.quill.nadare.holograms

import moe.quill.nadare.bukkitcommon.lib.BukkitLambda
import moe.quill.nadare.entries.Entry
import org.bukkit.Location
import org.bukkit.entity.AreaEffectCloud
import org.bukkit.plugin.Plugin

class DynamicHologram(
    plugin: Plugin,
    override val location: Location,
    interval: Long = 5,
    override val offset: Float = .27f
) : Hologram {
    override val entries = mutableListOf<Entry>()
    override val entities = mutableListOf<AreaEffectCloud>()

    private val daemon = BukkitLambda { update() }.runTaskTimer(plugin, 0, interval)

    override fun destroy() {
        super.destroy()
        daemon.cancel()
    }
}