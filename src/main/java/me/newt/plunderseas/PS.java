package me.newt.plunderseas;

import me.newt.plunderseas.plundering.UtilCreateOceanWorld;
import org.bukkit.plugin.java.JavaPlugin;

public class PS extends JavaPlugin {
    
    @Override
    public void onEnable() {
        System.out.println("Creating world...");
        System.out.println("Created: " + UtilCreateOceanWorld.createWorld().getName());
    }

    @Override
    public void onDisable() {

    }
}
