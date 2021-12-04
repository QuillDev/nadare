package moe.quill.nadare.cooking.temperature

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import com.comphenix.protocol.wrappers.WrappedDataWatcher
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.util.*

class TempHandler {
    val freezeStates = mutableMapOf<UUID, Long>()

}