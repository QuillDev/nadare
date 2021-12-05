package moe.quill.nadare.cooking.temperature

class HeatData(val range: Double, val heat: Double){
    fun relativeHeat(distance: Double): Int{
        return (heat * (1 - (distance / range)) ).toInt().coerceAtLeast(0).coerceAtMost(heat.toInt())
    }
}
