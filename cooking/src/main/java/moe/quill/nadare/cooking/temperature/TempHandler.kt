package moe.quill.nadare.cooking.temperature

import java.util.*

class TempHandler {
    private val freezeStates = mutableMapOf<UUID, Int>()
    private val maxFreezeTicks = 140

    fun setFreezeTicks(uuid: UUID, amount: Int) {
        freezeStates[uuid] = amount.coerceAtMost(maxFreezeTicks).coerceAtLeast(0)
    }

    fun modifyFreezeTicks(uuid: UUID, amount: Int) {
        setFreezeTicks(uuid, getFreezeTicks(uuid) + amount)
    }

    fun getFreezeTicks(uuid: UUID): Int {
        return freezeStates.getOrDefault(uuid, 0)
    }

    fun getWarmth(uuid: UUID) : Int{
        return 1
    }

}