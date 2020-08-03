package me.newt.plunderseas.listeners;

import me.newt.plunderseas.PlunderSeas;
import me.newt.plunderseas.storage.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class DeathHandler implements Listener {

    private final PlunderSeas plunderSeas;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                    CONSTRUCTOR                                    //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    public DeathHandler(PlunderSeas plunderSeas) {
        this.plunderSeas = plunderSeas;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                   EVENT HANDLER                                   //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        UUID uuid = player.getUniqueId();
        PlayerData playerData = plunderSeas.getPlayerDataManager().getPlayerData(uuid);

        boolean shouldDropInventory = playerData.removeSoulPointAndShouldDie();
        if (shouldDropInventory) {
            player.sendMessage(plunderSeas.getMessagesManager().getMessage("died_soulpoint_empty"));
            player.sendMessage(plunderSeas.getMessagesManager().getMessage("died_items_have_dropped"));
        } else {
            event.setKeepInventory(true);
            event.setKeepLevel(true);
            player.sendMessage(plunderSeas.getMessagesManager().getMessage("died_soulpoint_lost"));
        }
    }
}
