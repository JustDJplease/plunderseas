package me.newt.plunderseas.listeners;

import me.newt.plunderseas.PlunderSeas;
import me.newt.plunderseas.events.PlunderSeasSunriseEvent;
import me.newt.plunderseas.storage.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.UUID;

public class SunriseHandler implements Listener {

    private final PlunderSeas plunderSeas;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                    CONSTRUCTOR                                    //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    public SunriseHandler(PlunderSeas plunderSeas) {
        this.plunderSeas = plunderSeas;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                  EVENT HANDLER                                    //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    @EventHandler
    public void onSunrise(PlunderSeasSunriseEvent event) {
        List<Player> playerList = event.getPlayersInWorld();
        for (Player player : playerList) {
            UUID uuid = player.getUniqueId();
            PlayerData playerData = plunderSeas.getPlayerDataManager().getPlayerData(uuid);
            playerData.addSoulPoint();
            player.sendMessage(plunderSeas.getMessagesManager().getMessage("sunrise_soulpoint_gained"));
        }
    }
}
