package moe.quill.nadare.cooking.food

import moe.quill.nadare.cooking.core.EWCampfire
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin

class KeyManager(private val plugin: JavaPlugin) {
    private fun addIntKey(item: ItemStack, keyName: String, value: Int): ItemStack {
        val meta = item.itemMeta
        val data = meta.persistentDataContainer

        val key = NamespacedKey(plugin, keyName)
        data.set(key, PersistentDataType.INTEGER, value)
        item.itemMeta = meta

        return item
    }

    private fun addStringKey(item: ItemStack, keyName: String, value: String): ItemStack {
        val meta = item.itemMeta
        val data = meta.persistentDataContainer

        val key = NamespacedKey(plugin, keyName)
        data.set(key, PersistentDataType.STRING, value)
        item.itemMeta = meta

        return item
    }

    fun addIngredientKeys(item: ItemStack, campfire: EWCampfire): ItemStack {
        addIntKey(item, "custom_food", 1)
        if (campfire.ingredients.isEmpty()) return item
        for ((index, material) in campfire.ingredients.toList().withIndex()) {
            if (material != null) addStringKey(item, index.toString(), material.name)
        }
        return item
    }

    fun getIngredients(item: ItemStack) : List<Material> {
        val meta = item.itemMeta
        val data = meta.persistentDataContainer

        val materials = mutableListOf<Material>()
        for(i in 0..2){
            val key = NamespacedKey(plugin, i.toString())
            val value = data.get(key, PersistentDataType.STRING) ?: continue
            materials.add(Material.valueOf(value))
        }
        return materials
    }
}