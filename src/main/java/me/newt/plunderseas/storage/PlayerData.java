package me.newt.plunderseas.storage;

import me.newt.plunderseas.PlunderSeas;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.UUID;

public class PlayerData {

    private final PlunderSeas plunderSeas;
    private final UUID uuid;
    private int soulpoints;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                    CONSTRUCTOR                                    //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    /**
     * Create new PlayerData from configuration.
     */
    public PlayerData(PlunderSeas plunderSeas, UUID uuid) {
        this.plunderSeas = plunderSeas;
        this.uuid = uuid;
        this.soulpoints = plunderSeas.getConfig().getInt("max_soulpoints");
    }

    /**
     * Import PlayerData from configuration.
     */
    public PlayerData(PlunderSeas plunderSeas, UUID uuid, FileConfiguration existingData) {
        this.plunderSeas = plunderSeas;
        this.uuid = uuid;
        this.soulpoints = existingData.getInt("soulpoints");

        // Check if it does not exceed the max allowed SoulPoints and check if it is not negative or 0.
        int max_soulpoints = plunderSeas.getConfig().getInt("max_soulpoints");
        if (soulpoints > max_soulpoints) {
            soulpoints = max_soulpoints;
        }
        if (soulpoints < 1) {
            soulpoints = 1;
        }
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                  PUBLIC METHODS                                   //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    /**
     * Get the number of SoulPoints the player currently has.
     */
    public int getSoulPoints() {
        return soulpoints;
    }

    /**
     * Add a SoulPoint to the player.
     */
    public void addSoulPoint() {
        soulpoints = soulpoints + 1;
        int max_soulpoints = plunderSeas.getConfig().getInt("max_soulpoints");
        if (soulpoints > max_soulpoints) {
            soulpoints = max_soulpoints;
        }
        if (soulpoints < 1) {
            soulpoints = 1;
        }
        plunderSeas.getFileManager().savePlayerDataToFile(uuid, this);
    }

    /**
     * Remove a SoulPoint from the player.
     */
    public boolean removeSoulPointAndShouldDie() {
        soulpoints = soulpoints - 1;
        int max_soulpoints = plunderSeas.getConfig().getInt("max_soulpoints");

        if (soulpoints < 1) {
            // Player dies.
            soulpoints = max_soulpoints;
            plunderSeas.getFileManager().savePlayerDataToFile(uuid, this);
            return true;
        } else if (soulpoints > max_soulpoints) {
            // If it somehow bugged.
            soulpoints = max_soulpoints;
        }

        // Player does not die.
        plunderSeas.getFileManager().savePlayerDataToFile(uuid, this);
        return false;

    }
}
