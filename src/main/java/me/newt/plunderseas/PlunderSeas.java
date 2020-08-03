package me.newt.plunderseas;

import me.newt.plunderseas.listeners.ListenerManager;
import me.newt.plunderseas.runnables.RunnableManager;
import me.newt.plunderseas.storage.FileManager;
import me.newt.plunderseas.storage.PlayerDataManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PlunderSeas extends JavaPlugin {

    private FileManager fileManager;
    private MessagesManager messagesManager;
    private PlayerDataManager playerDataManager;
    private RunnableManager runnableManager;
    private ListenerManager listenerManager;


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                    TASKLIST                                       //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    // - Display soulpoints, money and skill-rating in action bar.

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                    ENABLING                                       //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    @Override
    public void onEnable() {
        getLogger().info("Creating files...");
        fileManager = new FileManager(this);
        fileManager.createFolders();
        fileManager.createConfigFile();
        fileManager.createMessagesFile();

        messagesManager = new MessagesManager(this);
        messagesManager.loadMessages();

        getLogger().info("Loading player data...");
        playerDataManager = new PlayerDataManager(this);

        getLogger().info("Starting tasks...");
        runnableManager = new RunnableManager(this);
        runnableManager.startRunnables();

        getLogger().info("Registering events...");
        listenerManager = new ListenerManager(this);
        listenerManager.startListeners();
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

    public MessagesManager getMessagesManager() {
        return messagesManager;
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }

    public RunnableManager getRunnableManager() {
        return runnableManager;
    }

    public ListenerManager getListenerManager() {
        return listenerManager;
    }
}
