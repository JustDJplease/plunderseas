package me.newt.plunderseas;

import me.newt.plunderseas.storage.FileManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PlunderSeas extends JavaPlugin {

    private FileManager fileManager;

    // Soulpoints:
    // - Soulpoint deduction upon death
    // - Soulpoint regeneration every morning
    // - Soulpoint display actionbar (money / rating as well)
    // - Soulpoint saving (file)

    @Override
    public void onEnable() {
        getLogger().info("Creating files...");
        fileManager = new FileManager(this);
        fileManager.createFolders();
        fileManager.createConfigFile();
    }

    @Override
    public void onDisable() {

    }
}
