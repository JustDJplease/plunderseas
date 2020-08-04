package me.newt.plunderseas.managers;

import me.newt.plunderseas.PlunderSeas;
import me.newt.plunderseas.listeners.DeathHandler;
import me.newt.plunderseas.listeners.JoinHandler;
import me.newt.plunderseas.listeners.SunriseHandler;
import org.bukkit.plugin.PluginManager;

public class ListenerManager {

    private final PlunderSeas plunderSeas;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                    CONSTRUCTOR                                    //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    public ListenerManager(PlunderSeas plunderSeas) {
        this.plunderSeas = plunderSeas;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                  PUBLIC METHODS                                   //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    /**
     * Start all listeners for this plugin.
     */
    public void startListeners() {
        PluginManager pluginManager = plunderSeas.getServer().getPluginManager();
        pluginManager.registerEvents(new JoinHandler(plunderSeas), plunderSeas);
        pluginManager.registerEvents(new DeathHandler(plunderSeas), plunderSeas);
        pluginManager.registerEvents(new SunriseHandler(plunderSeas), plunderSeas);
    }
}
