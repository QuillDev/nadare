package moe.quill.nadare.cooking.temperature

import com.comphenix.protocol.ProtocolLibrary
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerRespawnEvent

class PlayerRespawnListener(private val tempHandler: TempHandler, private val tempPacketListener: TempPacketListener) : Listener {

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent){
        val player = event.player
        tempHandler.setFreezeTicks(player.uniqueId, 0)
        ProtocolLibrary.getProtocolManager().sendServerPacket(player, tempPacketListener.createFreezePacket(player))
    }
}