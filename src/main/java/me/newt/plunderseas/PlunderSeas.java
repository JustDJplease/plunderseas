package me.newt.plunderseas;

import me.newt.plunderseas.managers.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class PlunderSeas extends JavaPlugin {

    private FileManager fileManager;
    private ListenerManager listenerManager;
    private MessagesManager messagesManager;
    private PlayerDataManager playerDataManager;
    private RunnableManager runnableManager;
    private Economy economyManager;


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                    TASKLIST                                       //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
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
        getLogger().info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        getLogger().info("Startup: Creating necessary files...");
        fileManager = new FileManager(this);
        fileManager.createNecessaryFiles();

        getLogger().info("Startup: Loading messages...");
        messagesManager = new MessagesManager(this);
        messagesManager.loadMessages();

        getLogger().info("Startup: Loading player data...");
        playerDataManager = new PlayerDataManager(this);

        getLogger().info("Startup: Hooking into vault...");
        RegisteredServiceProvider<Economy> serviceProvider = getServer().getServicesManager().getRegistration(Economy.class);
        assert serviceProvider != null : "Missing a plugin that manages economy (like Essentials)";
        economyManager = serviceProvider.getProvider();

        getLogger().info("Startup: Starting scheduled tasks...");
        runnableManager = new RunnableManager(this);
        runnableManager.startRunnables();

        getLogger().info("Startup: Listening for events...");
        listenerManager = new ListenerManager(this);
        listenerManager.startListeners();

        getLogger().info("Startup: Completed!");
        getLogger().info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
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

    public ListenerManager getListenerManager() {
        return listenerManager;
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

    public Economy getEconomyManager() {
        return economyManager;
    }
}
