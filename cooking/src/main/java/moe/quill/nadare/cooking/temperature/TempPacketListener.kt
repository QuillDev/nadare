package moe.quill.nadare.cooking.temperature

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketContainer
import com.comphenix.protocol.events.PacketEvent
import com.comphenix.protocol.wrappers.WrappedDataWatcher
import moe.quill.nadare.bukkitcommon.BukkitLambda
import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.util.*

class TempPacketListener(plugin: Plugin, private val tempHandler: TempHandler) :
    PacketAdapter(plugin, ListenerPriority.HIGHEST, PacketType.Play.Server.ENTITY_METADATA) {

    init {
        BukkitLambda {
            Bukkit.getOnlinePlayers().forEach {
                ProtocolLibrary.getProtocolManager().sendServerPacket(
                    it,
                    createFreezePacket(it, PacketContainer(PacketType.Play.Server.ENTITY_METADATA))
                )
            }
        }.runTaskTimerAsynchronously(plugin, 0, 5)
    }

    override fun onPacketSending(event: PacketEvent?) {
        event ?: return
        if (event.packetType != PacketType.Play.Server.ENTITY_METADATA) return
        //Get the entity and make sure it's a player
        val entity = event.packet.getEntityModifier(event).read(0) ?: return
        if (entity !is Player) return

        event.packet = createFreezePacket(entity, event.packet)
    }

    private fun createFreezePacket(entity: Player, raw: PacketContainer): PacketContainer {
        val oldBits = WrappedDataWatcher.getEntityWatcher(entity)

        oldBits.setObject(7, tempHandler.getFreezeTicks(entity.uniqueId))
        raw.watchableCollectionModifier.write(0, oldBits.watchableObjects)

        return raw
    }
}