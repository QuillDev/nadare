package moe.quill.nadare.cooking.core

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class CampfireManager(private val plugin: JavaPlugin) {
     private val campfires = mutableListOf<EWCampfire>()

     fun registerCampfire(campfire: EWCampfire){
          campfires.add(campfire)
     }


}