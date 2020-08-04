package me.newt.plunderseas.managers;

import me.newt.plunderseas.PlunderSeas;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessagesManager {

    private final PlunderSeas plunderSeas;
    private FileConfiguration messages;
    private final Pattern hexColourPattern = Pattern.compile("(?<!\\\\)(#[a-fA-F0-9]{6})");


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
            return translateColour(ChatColor.translateAlternateColorCodes('&', message));
        }

        return "missing_messages_" + id;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                  PRIVATE METHODS                                  //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    /**
     * Translate HEX colour codes.
     */
    private String translateColour(String message) {
        Matcher matcher = hexColourPattern.matcher(message);

        while (matcher.find()) {
            String color = message.substring(matcher.start(), matcher.end());
            message = message.replace(color, "" + ChatColor.of(color));
        }
        return message;
    }
}
