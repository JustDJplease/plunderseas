package me.newt.plunderseas.listeners;

import me.newt.plunderseas.PlunderSeas;
import me.newt.plunderseas.events.PlunderSeasSunriseEvent;
import me.newt.plunderseas.storage.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SunriseHandler implements Listener {

    private final PlunderSeas plunderSeas;
    private final String sunRiseMessage;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                    CONSTRUCTOR                                    //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    public SunriseHandler(PlunderSeas plunderSeas) {
        this.plunderSeas = plunderSeas;
        this.sunRiseMessage = plunderSeas.getMessagesManager().getMessage("sunrise_soulpoint_gained");
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                  EVENT HANDLER                                    //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    @EventHandler
    public void onSunrise(PlunderSeasSunriseEvent event) {
        event.getPlayersInWorld().forEach(player -> {
            player.sendMessage(sunRiseMessage);
            PlayerData playerData = plunderSeas.getPlayerDataManager().getPlayerData(player);
            playerData.addSoulPoint();
        });
    }
}
