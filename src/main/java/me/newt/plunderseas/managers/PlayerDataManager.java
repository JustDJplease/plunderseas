package me.newt.plunderseas.managers;

import me.newt.plunderseas.PlunderSeas;
import me.newt.plunderseas.storage.PlayerData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataManager {

    private final PlunderSeas plunderSeas;
    private final HashMap<UUID, PlayerData> onlinePlayersData;

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
     * Get PlayerData for a given player.
     */
    public PlayerData getPlayerData(Player player) {
        return getPlayerData(player.getUniqueId());
    }

    /**
     * Get PlayerData for a given player's uuid.
     */
    public PlayerData getPlayerData(UUID uuid) {

        // Check if the playerData is already loaded.
        if (onlinePlayersData.containsKey(uuid)) {
            return onlinePlayersData.get(uuid);
        }

        // Check if we can load playerData from file.
        else if (hasExistingPlayerData(uuid)) {
            PlayerData playerData = plunderSeas.getFileManager().getPlayerDataFromFile(uuid);
            onlinePlayersData.put(uuid, playerData);
            return playerData;
        }

        // Create new playerData and file.
        else {
            PlayerData playerData = new PlayerData(plunderSeas, uuid);
            onlinePlayersData.put(uuid, playerData);
            plunderSeas.getFileManager().savePlayerDataToFile(uuid, playerData);
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
        return plunderSeas.getFileManager().hasPlayerDataFile(uuid);
    }
}
