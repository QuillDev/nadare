package moe.quill.nadare.attributes.attributes

import org.bukkit.Material

class AttributeFlag {

    companion object {
        //Tools
        const val PICKAXE = "PICKAXE"
        const val AXE = "AXE"
        const val FISHING_ROD = "FISHING_ROD"
        const val HOE = "HOE"
        const val SHOVEL = "SHOVEL"

        //Weapons
        const val SWORD = "SWORD"

        //Random
        const val CONSUMABLE = "CONSUMABLE"

        private val values: Map<String, Collection<Material>>

        init {
            val temp = mutableMapOf<String, MutableCollection<Material>>()
            Material.values().forEach { mat ->
                val name = mat.name
                val key = when {
                    name.contains("_${SWORD}") -> SWORD
                    name.contains("_${AXE}") -> AXE
                    name.contains("_${HOE}") -> HOE
                    name.contains("_${PICKAXE}") -> PICKAXE
                    name.contains("_${SHOVEL}") -> SHOVEL
                    mat.isEdible -> CONSUMABLE
                    else -> return@forEach
                }
                temp.compute(key) { _, list -> list?.add(mat); list ?: mutableListOf(mat) }
            }
            temp[FISHING_ROD] = mutableListOf(Material.FISHING_ROD)

            values = temp.mapValues { (_, mutList) -> mutList.toList() }
        }
    }
}