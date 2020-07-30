package me.newt.plunderseas;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class PS extends JavaPlugin {

    Logger logger;

    @Override
    public void onEnable() {
        logger = Bukkit.getLogger();
        logger.info("Enabling " + getDescription().getFullName());
    }

    @Override
    public void onDisable() {
        logger.info("Disabling " + getDescription().getFullName());
    }
}
