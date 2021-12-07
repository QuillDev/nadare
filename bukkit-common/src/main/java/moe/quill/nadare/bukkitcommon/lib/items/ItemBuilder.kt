package moe.quill.nadare.bukkitcommon.lib.items

import com.destroystokyo.paper.profile.ProfileProperty
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.persistence.PersistentDataType
import java.util.*

class ItemBuilder(private val item: ItemStack) {
    constructor(type: Material, amount: Int = 1) : this(ItemStack(type, amount))

    //Display name
    fun displayName(component: Component): ItemBuilder {
        meta()?.let { it.displayName(component); meta(it) }
        return this
    }

    //Modify type
    fun type(material: Material): ItemBuilder {
        item.type = material
        return this
    }

    fun type(): Material {
        return item.type
    }

    //Modify Amount
    fun amount(amount: Int): ItemBuilder {
        item.amount = amount
        return this
    }

    fun headSkin(dataString: String): ItemBuilder {
        if (item.type != Material.PLAYER_HEAD) return this
        meta()?.let {
            val meta = it as SkullMeta
            val profile = Bukkit.createProfile(UUID.randomUUID())
            profile.properties.add(ProfileProperty("textures", dataString))
            meta.playerProfile = profile
            meta(meta)
        }
        return this
    }

    //Modify lore
    fun lore(vararg components: Component): ItemBuilder {
        meta()?.let { it.lore(components.toList()); meta(it) }
        return this
    }

    fun lore(list: List<Component>): ItemBuilder {
        lore(*list.toTypedArray())
        return this
    }

    fun addLore(component: Component): ItemBuilder {
        meta()?.let { meta ->
            meta.lore()?.let { stale ->
                val newLore = stale.toMutableList()
                newLore += component
                meta.lore(newLore)
                meta(meta)
            }
        }
        return this
    }

    //Modify Keys
    fun applyMarkerKey(key: NamespacedKey): ItemBuilder {
        applyKey(key, PersistentDataType.STRING, "")
        return this
    }

    fun <T, Z> applyKey(key: NamespacedKey, type: PersistentDataType<T, Z>, value: Z): ItemBuilder {
        meta()?.let { it.persistentDataContainer.set(key, type, value!!); meta(it) }
        return this
    }

    //Random Flags
    fun unbreakable(unbreakable: Boolean = true): ItemBuilder {
        meta()?.let { it.isUnbreakable = unbreakable; meta(it) }
        return this
    }

    fun removeFlags(vararg flags: ItemFlag): ItemBuilder {
        meta()?.let { it.removeItemFlags(*flags); meta(it) }
        return this
    }

    //Modify Meta
    fun meta(meta: ItemMeta): ItemBuilder {
        item.itemMeta = meta
        return this
    }

    fun meta(): ItemMeta? {
        return item.itemMeta
    }


    fun build(): ItemStack {
        return item
    }
}