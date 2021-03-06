package me.newt.plunderseas.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlunderSeasRaidEndEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                    CONSTRUCTOR                                    //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    public PlunderSeasRaidEndEvent() {
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                  PUBLIC METHODS                                   //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    
    /**
     * Spigot required code for a custom event.
     */
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    /**
     * Spigot required code for a custom event.
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
