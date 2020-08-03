package me.newt.plunderseas.storage;

import me.newt.plunderseas.PlunderSeas;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class FileManager {

    private final PlunderSeas plunderSeas;
    private final int configVersion = 1;
    private final int messagesVersion = 1;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                    CONSTRUCTOR                                    //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    public FileManager(PlunderSeas plunderSeas) {
        this.plunderSeas = plunderSeas;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                  PUBLIC METHODS                                   //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    /**
     * Creates the /plugins/PlunderSeas/data folders if they do not exist already.
     */
    public void createFolders() {
        File folder = new File("/plugins/PlunderSeas/data");
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    /**
     * Creates the config.yml file if it does not exist already.
     */
    public void createConfigFile() {
        plunderSeas.saveDefaultConfig();
        int currentConfigVersion = plunderSeas.getConfig().getInt("version");

        if (configVersion != currentConfigVersion) {
            plunderSeas.getLogger().severe("Encountered an outdated config.yml file!");
            plunderSeas.getLogger().severe("Please delete the file /plugins/PlunderSeas/config.yml and restart the server.");
        }
    }

    /**
     * Creates the messages.yml file if it does not exist already.
     */
    public void createMessagesFile() {
        File file = new File("/plugins/PlunderSeas", "messages.yml");
        if (!file.exists()) {
            plunderSeas.saveResource("messages.yml", false);
        }

        FileConfiguration messages = getMessagesFile();
        int currentMessagesVersion = messages.getInt("version");

        if (messagesVersion != currentMessagesVersion) {
            plunderSeas.getLogger().severe("Encountered an outdated messages.yml file!");
            plunderSeas.getLogger().severe("Please delete the file /plugins/PlunderSeas/messages.yml and restart the server.");
        }
    }

    /**
     * Get the configuration from inside the messages.yml file.
     */
    public FileConfiguration getMessagesFile() {
        File file = new File("/plugins/PlunderSeas", "messages.yml");
        FileConfiguration messages = new YamlConfiguration();
        try {
            messages.load(file);
        } catch (IOException | InvalidConfigurationException ex) {
            ex.printStackTrace();
        }
        return messages;
    }

    /**
     * Load PlayerData from a file.
     */
    public PlayerData loadPlayerData(UUID uuid) {
        File file = new File("/plugins/PlunderSeas/data", uuid + ".yml");
        FileConfiguration config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException ex) {
            ex.printStackTrace();
        }
        return new PlayerData(plunderSeas, uuid, config);
    }

    /**
     * Save PlayerData to a file.
     */
    public void savePlayerData(UUID uuid, PlayerData playerData) {
        File file = new File("/plugins/PlunderSeas/data", uuid + ".yml");

        if (!file.exists()) {
            // No file existing currently.
            plunderSeas.saveResource("template_playerdata.yml", false);
            File template = new File("/plugins/PlunderSeas/data", "template_playerdata.yml");
            template.renameTo(file);
            return;
        }

        // Updating existing file if it does exist.
        FileConfiguration config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException ex) {
            ex.printStackTrace();
        }
        config.set("soulpoints", playerData.getSoulPoints());
        try {
            config.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Check if a PlayerData file exists.
     */
    public boolean hasPlayerData(UUID uuid) {
        File file = new File("/plugins/PlunderSeas/data", uuid + ".yml");
        return file.exists();
    }
}
