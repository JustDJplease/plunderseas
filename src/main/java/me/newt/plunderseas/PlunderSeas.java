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
    // Currency:
    // - Hook into vault.
    //
    // Adventuring & Shipwrecks
    // - Shipwreck chests regenerate.
    // - Looting yields cash.
    //
    // Raiding:
    // - Create blank world upon enabling.
    // - Trash blank world upon disabling.
    // - Hook into precious-stones.
    // - Copy raided islands to that world (0,0).
    // - Setup world-border.
    // - One raid active at a time.
    // - Warmup in which defending players can decide to participate.
    // - Teleport participating players.
    // - Keep track of players near trap armor-stands.
    // - Destruction of non-default-generated blocks leads to income.
    // - Destruction of special blocks leads to extra income.
    // - Money is lost from defending team.
    // - Limited time to raid.
    // - Cool-down after raid.
    // - Skill rating for victory & loss.
    // - Death = out of the raid
    // - No remaining attackers or time over = end of the raid. (Check every second, account for leavers).
    //
    // Skill-rating:
    // - Crew with the highest skill-rating gets a weekly & monthly reward.
    //
    // Traps:
    // - Popular trap plugin to hook into?
    // -    Potion effect traps.
    // -    Launching traps.
    // -    MythicMob traps.
    // -    Disappearing floor (3x3) traps.
    // - Players can place and remove traps (armor-stands).
    // - Trap (items) can be bought.
    // - Animated armor-stands.
    // - Upon raid, these are hidden.
    //
    // Display:
    // - Souls, money and skill-rating in action bar.
    //
    // Souls:
    // - /soulpoint(s) set <player> <number or max>.
    // - /soulpoint(s) get <player>.

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
