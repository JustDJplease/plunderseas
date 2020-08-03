package me.newt.plunderseas.events;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.List;

public class PlunderSeasSunriseEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final World world;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                    CONSTRUCTOR                                    //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    public PlunderSeasSunriseEvent(World world) {
        this.world = world;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                  PUBLIC METHODS                                   //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    /**
     * Get the world in which the sunrise happened.
     */
    public World getWorld(){
        return world;
    }

    /**
     * Get the players currently residing in this world.
     */
    public List<Player> getPlayersInWorld(){
        return world.getPlayers();
    }

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
