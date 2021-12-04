package moe.quill.nadare.cooking.temperature

import java.util.*

class TempHandler {
    private val freezeStates = mutableMapOf<UUID, Int>()
    private val maxTicks = 140

    fun setTicks(uuid: UUID, amount: Int) {
        freezeStates.getOrPut(uuid) { amount.coerceAtMost(maxTicks).coerceAtLeast(0) }
    }

    fun modifyTicks(uuid: UUID, amount: Int) {
        setTicks(uuid, getTicks(uuid) + amount)
    }

    fun getTicks(uuid: UUID): Int {
        return freezeStates.getOrDefault(uuid, 0)
    }
}