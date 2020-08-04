package me.newt.plunderseas.listeners;

import me.newt.plunderseas.PlunderSeas;
import me.newt.plunderseas.storage.PlayerData;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

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
        PlayerData playerData = plunderSeas.getPlayerDataManager().getPlayerData(player);

        boolean shouldDropInventory = playerData.removeSoulPointAndShouldDie();
        if (shouldDropInventory) {
            player.sendMessage(plunderSeas.getMessagesManager().getMessage("died_soulpoint_empty"));
            player.sendMessage(plunderSeas.getMessagesManager().getMessage("died_items_have_dropped"));
        } else {
            player.sendMessage(plunderSeas.getMessagesManager().getMessage("died_soulpoint_lost"));
            // Apparently, players will still drop items with the keepInventory turned to true.
            event.setKeepInventory(true);
            event.setKeepLevel(true);
            // For that reason we manually remove the drops.
            event.getDrops().clear();
            event.setDroppedExp(0);
            // We play some fancy soul particle effects whenever a soul is consumed.
            playSoulEffect(player.getLocation());
        }
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                  PRIVATE METHODS                                  //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    /**
     * Creates a soul-particle escaping effect.
     */
    @SuppressWarnings("ConstantConditions")
    private void playSoulEffect(Location location) {
        location.getWorld().spawnParticle(Particle.SOUL, location, 25, 0.5, 0.5, 0.5, 0.1);
        location.getWorld().playSound(location, Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1f, 1f);
    }
}
