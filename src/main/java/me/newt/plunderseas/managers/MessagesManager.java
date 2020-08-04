package me.newt.plunderseas.managers;

import me.newt.plunderseas.PlunderSeas;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class MessagesManager {

    private final PlunderSeas plunderSeas;
    private FileConfiguration messages;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                    CONSTRUCTOR                                    //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    public MessagesManager(PlunderSeas plunderSeas) {
        this.plunderSeas = plunderSeas;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                  PUBLIC METHODS                                   //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    /**
     * Load the messages file into memory.
     */
    public void loadMessages() {
        messages = plunderSeas.getFileManager().getMessagesFile();
    }

    /**
     * Get a formatted message from the messages.yml file.
     */
    public String getMessage(String id) {
        String message = messages.getString("messages." + id);

        // Check if there is a message with this id.
        if (message != null) {
            return ChatColor.translateAlternateColorCodes('&', message);
        }

        return "missing_messages_" + id;
    }
}
