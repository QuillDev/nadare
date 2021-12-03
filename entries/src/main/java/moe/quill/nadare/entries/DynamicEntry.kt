package moe.quill.nadare.entries

import net.kyori.adventure.text.Component

class DynamicEntry(val supplier: () -> Component) : Entry {

    override var value: Component = update()

    fun update(): Component {
        val new = supplier()
        value = new
        return new
    }
}