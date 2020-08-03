package me.newt.plunderseas;

import me.newt.plunderseas.listeners.DeathHandler;
import me.newt.plunderseas.listeners.JoinHandler;
import me.newt.plunderseas.listeners.SunriseHandler;
import me.newt.plunderseas.runnables.RunnableManager;
import me.newt.plunderseas.storage.FileManager;
import me.newt.plunderseas.storage.PlayerDataManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PlunderSeas extends JavaPlugin {

    private FileManager fileManager;
    private PlayerDataManager playerDataManager;
    private RunnableManager runnableManager;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                    TASKLIST                                       //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    // Soulpoints:
    // - [COMPLETED] Soulpoint deduction upon death
    // - Soulpoint regeneration every morning
    // - Soulpoint display actionbar (money / rating as well)
    // - [COMPLETED] Soulpoint saving (file & in memory)
    // - [COMPLETED] Load player data on join
    // - [COMPLETED] New day event for every overworld

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                    ENABLING                                       //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    @Override
    public void onEnable() {
        getLogger().info("Creating files...");
        fileManager = new FileManager(this);
        fileManager.createFolders();
        fileManager.createConfigFile();

        getLogger().info("Loading player data...");
        playerDataManager = new PlayerDataManager(this);

        getLogger().info("Starting tasks...");
        runnableManager = new RunnableManager(this);
        runnableManager.startRunnables();

        getLogger().info("Registering events...");
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new JoinHandler(this), this);
        pluginManager.registerEvents(new DeathHandler(this), this);
        pluginManager.registerEvents(new SunriseHandler(this), this);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                   DISABLING                                       //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    @Override
    public void onDisable() {

    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                  PUBLIC METHODS                                   //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    public FileManager getFileManager() {
        return fileManager;
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }

    public RunnableManager getRunnableManager() {
        return runnableManager;
    }
}
