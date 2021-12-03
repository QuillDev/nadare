package moe.quill.nadare.cooking.temp

import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable


class AddCold : BukkitRunnable() {

    override fun run() {
        //nms Code to keep freeze packets???
//        val player = sender as Player
//        val ep: EntityPlayer = (player as CraftPlayer).getHandle()
//        ep.setTicksFrozen(ep.getTicksRequiredToFreeze())
//        ep.b.sendPacket(PacketPlayOutEntityMetadata(ep.getId(), ep.getDataWatcher(), true))
    }
}