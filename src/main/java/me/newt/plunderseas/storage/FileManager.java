package me.newt.plunderseas.storage;

import me.newt.plunderseas.PlunderSeas;

import java.io.File;

public class FileManager {

    private PlunderSeas plunderSeas;
    private int configVersion = 1;

    /**
     * Constructor.
     *
     * @param plunderSeas Instance of the main class.
     */
    public FileManager(PlunderSeas plunderSeas) {
        this.plunderSeas = plunderSeas;
    }

    /**
     * Creates the /plugin/ folders if they do not exist already.
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
}
