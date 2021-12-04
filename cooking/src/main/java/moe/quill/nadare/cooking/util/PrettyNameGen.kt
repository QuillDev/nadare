package moe.quill.nadare.cooking.util

import org.bukkit.Material

class PrettyNameGen {
    companion object{
        @JvmStatic
        fun asPretty(material: Material) : String{
            return material.name.split("_")
                .joinToString(" ") { it.lowercase().replaceFirstChar { first -> first.uppercase() } }
        }
    }
}