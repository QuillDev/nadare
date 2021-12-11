package moe.quill.nadare.cooking.core

import moe.quill.nadare.bukkitcommon.lib.keys.KeyManager
import moe.quill.nadare.cooking.food.FoodItemGenerator
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class CampfireManager(val foodItemGenerator: FoodItemGenerator) {
     val campfires = mutableListOf<EWCampfire>()

     fun registerCampfire(campfire: EWCampfire){
          campfires.add(campfire)
          campfire.itemGenerator = foodItemGenerator
     }


}