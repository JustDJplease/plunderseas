package me.newt.plunderseas.runnables;

import me.newt.plunderseas.PlunderSeas;
import me.newt.plunderseas.storage.PlayerData;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class ActionBarRunnable implements Runnable {

    private final PlunderSeas plunderSeas;
    private final String actionBar;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                    CONSTRUCTOR                                    //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    public ActionBarRunnable(PlunderSeas plunderSeas) {
        this.plunderSeas = plunderSeas;
        this.actionBar = plunderSeas.getMessagesManager().getMessage("actionbar");

    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                     RUNNABLE                                      //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    @Override
    public void run() {
        plunderSeas.getServer().getOnlinePlayers().forEach(player -> {
            PlayerData playerData = plunderSeas.getPlayerDataManager().getPlayerData(player);
            String formattedActionBar = actionBar;
            formattedActionBar = formattedActionBar.replace("{0}", "" + playerData.getSoulPoints());
            formattedActionBar = formattedActionBar.replace("{1}", "" + getFormattedBalance(player));
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(formattedActionBar));
        });
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                  PRIVATE METHODS                                  //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    /**
     * Returns the player's balance, formatted to two decimal places.
     */
    private String getFormattedBalance(Player player) {
        double balance = plunderSeas.getEconomyManager().getBalance(player);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(balance);
    }
}
