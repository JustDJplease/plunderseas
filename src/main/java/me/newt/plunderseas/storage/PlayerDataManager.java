package me.newt.plunderseas.storage;

import me.newt.plunderseas.PlunderSeas;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataManager {

    private PlunderSeas plunderSeas;
    private HashMap<UUID, PlayerData> onlinePlayersData;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                    CONSTRUCTOR                                    //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    public PlayerDataManager(PlunderSeas plunderSeas) {
        this.plunderSeas = plunderSeas;
        this.onlinePlayersData = new HashMap<>();
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                  PUBLIC METHODS                                   //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    /**
     * Get PlayerData for a given player's uuid.
     */
    public PlayerData getPlayerData(UUID uuid) {

        if (onlinePlayersData.containsKey(uuid)) {
            // The PlayerData is already loaded in memory.
            return onlinePlayersData.get(uuid);

        } else if (hasExistingPlayerData(uuid)) {
            // The PlayerData needs to be loaded from a file that exists already.
            PlayerData playerData = plunderSeas.getFileManager().loadPlayerData(uuid);
            onlinePlayersData.put(uuid, playerData);
            return playerData;

        } else {
            // The PlayerData needs to be created new as there is no file yet.
            PlayerData playerData = new PlayerData(plunderSeas, uuid);
            onlinePlayersData.put(uuid, playerData);
            plunderSeas.getFileManager().savePlayerData(uuid, playerData);
            return playerData;
        }
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                  PRIVATE METHODS                                  //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    /**
     * Check if this player has existing PlayerData.
     */
    private boolean hasExistingPlayerData(UUID uuid) {
        return plunderSeas.getFileManager().hasPlayerData(uuid);
    }
}
