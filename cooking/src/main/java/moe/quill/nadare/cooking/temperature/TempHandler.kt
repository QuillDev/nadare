package moe.quill.nadare.cooking.temperature

import java.util.*

class TempHandler {
    private val freezeStates = mutableMapOf<UUID, Long>()
    private val maxTicks = 140L

    fun setTicks(uuid: UUID, amount: Long) {
        freezeStates.getOrPut(uuid) { amount.coerceAtMost(maxTicks).coerceAtLeast(0) }
    }

    fun modifyTicks(uuid: UUID, amount: Long) {
        setTicks(uuid, getTicks(uuid) + amount)
    }

    fun getTicks(uuid: UUID): Long {
        return freezeStates.getOrDefault(uuid, 0)
    }
}