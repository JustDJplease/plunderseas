package me.newt.plunderseas.managers;

import me.newt.plunderseas.PlunderSeas;
import org.apache.commons.io.FileUtils;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import java.io.File;
import java.io.IOException;

public class WorldManager {

    private PlunderSeas plunderSeas;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                    CONSTRUCTOR                                    //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    public WorldManager(PlunderSeas plunderSeas) {
        this.plunderSeas = plunderSeas;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                  PUBLIC METHODS                                   //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    /**
     * Create the off-map world.
     */
    public void createOffMapWorld() {
        String name = plunderSeas.getConfig().getString("offmap_world_name");
        if (name == null) {
            plunderSeas.getLogger().severe("Invalid configuration for off-map world!");
            return;
        }
        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.environment(World.Environment.NORMAL);
        worldCreator.type(WorldType.FLAT);
        worldCreator.generatorSettings("minecraft:bedrock,31*minecraft:sand,30*minecraft:water;minecraft:deep_ocean;");
        worldCreator.createWorld();
    }

    /**
     * Removes any the world folder of the existing world.
     */
    public void removeOffMapWorld() {
        String name = plunderSeas.getConfig().getString("offmap_world_name");
        if (name == null) {
            return;
        }

        File file = new File(name);
        if (file.exists()) {
            try {
                FileUtils.deleteDirectory(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
