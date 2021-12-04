package moe.quill.nadare.cooking.temperature

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import com.comphenix.protocol.wrappers.WrappedDataWatcher
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class TempPacketListener(plugin: Plugin, private val tempHandler: TempHandler) :
    PacketAdapter(plugin, ListenerPriority.HIGHEST, PacketType.Play.Server.ENTITY_METADATA) {

    override fun onPacketSending(event: PacketEvent?) {
        event ?: return
        if (event.packetType != PacketType.Play.Server.ENTITY_METADATA) return
        //Get the entity and make sure it's a player
        val entity = event.packet.getEntityModifier(event).read(0) ?: return
        if (entity !is Player) return

        val oldBits = WrappedDataWatcher.getEntityWatcher(entity)

        val ticks = tempHandler.freezeStates.getOrPut(entity.uniqueId) { 0L }
        oldBits.setObject(7, ticks)
        event.packet.watchableCollectionModifier.write(0, oldBits.watchableObjects)
    }
}