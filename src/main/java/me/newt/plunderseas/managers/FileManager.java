package me.newt.plunderseas.managers;

import me.newt.plunderseas.PlunderSeas;
import me.newt.plunderseas.storage.PlayerData;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@SuppressWarnings({"ResultOfMethodCallIgnored", "FieldCanBeLocal"})
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
     * Makes sure all necessary files and folders are created.
     */
    public void createNecessaryFiles() {
        createFolders();
        createConfigFile();
        createMessagesFile();
    }

    /**
     * Gets the yamlConfiguration from the messages file.
     */
    public FileConfiguration getMessagesFile() {
        File file = new File("plugins/PlunderSeas", "messages.yml");
        return loadYAML(file);
    }

    /**
     * Gets the playerData from a file.
     */
    public PlayerData getPlayerDataFromFile(UUID uuid) {
        File file = new File("plugins/PlunderSeas/data", uuid + ".yml");
        return new PlayerData(plunderSeas, uuid, loadYAML(file));
    }

    /**
     * Saves the playerData to a file.
     */
    public void savePlayerDataToFile(UUID uuid, PlayerData playerData) {
        File file = new File("plugins/PlunderSeas/data", uuid + ".yml");

        if (!file.exists()) {
            // No file existing currently.
            plunderSeas.saveResource("template_playerdata.yml", false);
            File template = new File("plugins/PlunderSeas", "template_playerdata.yml");
            template.renameTo(file);
            return;
        }

        // Updating existing file if it does exist.
        FileConfiguration config = loadYAML(file);
        config.set("soulpoints", playerData.getSoulPoints());
        saveYAML(config, file);
    }

    /**
     * Check if there is a file with playerData for this player.
     */
    public boolean hasPlayerDataFile(UUID uuid) {
        File file = new File("plugins/PlunderSeas/data", uuid + ".yml");
        return file.exists();
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                  PRIVATE METHODS                                  //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    /**
     * Creates necessary folders (if non-existent).
     */
    private void createFolders() {
        File folder = new File("plugins/PlunderSeas/data");
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    /**
     * Creates necessary config.yml (if non-existent).
     */
    private void createConfigFile() {
        plunderSeas.saveDefaultConfig();

        // Check if the config file  currently saved is up to date.
        int currentConfigVersion = plunderSeas.getConfig().getInt("version");
        if (configVersion != currentConfigVersion) {
            plunderSeas.getLogger().severe("Encountered an outdated config.yml file!");
            plunderSeas.getLogger().severe("Please delete the file /plugins/PlunderSeas/config.yml and restart the server.");
        }
    }

    /**
     * Creates necessary messages.yml (if non-existent).
     */
    private void createMessagesFile() {
        File file = new File("plugins/PlunderSeas", "messages.yml");
        if (!file.exists()) {
            plunderSeas.saveResource("messages.yml", false);
        }

        // Check if the messages file currently saved is up to date.
        FileConfiguration messages = getMessagesFile();
        int currentMessagesVersion = messages.getInt("version");

        if (messagesVersion != currentMessagesVersion) {
            plunderSeas.getLogger().severe("Encountered an outdated messages.yml file!");
            plunderSeas.getLogger().severe("Please delete the file /plugins/PlunderSeas/messages.yml and restart the server.");
        }
    }

    /**
     * Loads YAML from a file.
     */
    private FileConfiguration loadYAML(File file) {
        FileConfiguration yamlConfiguration = new YamlConfiguration();
        try {
            yamlConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException ex) {
            ex.printStackTrace();
        }
        return yamlConfiguration;
    }

    /**
     * Saves YAML to a file.
     */
    private void saveYAML(FileConfiguration yamlConfiguration, File file) {
        try {
            yamlConfiguration.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
