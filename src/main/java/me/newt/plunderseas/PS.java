package me.newt.plunderseas;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class PS extends JavaPlugin {

    Logger logger;

    // Soulpoints:
    // - Soulpoint deduction upon death
    // - Soulpoint regeneration every morning
    // - Soulpoint display actionbar (money / rating as well)
    // - Soulpoint saving (file)

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
