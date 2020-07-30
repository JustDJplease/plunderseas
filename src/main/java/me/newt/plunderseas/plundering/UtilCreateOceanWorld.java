package me.newt.plunderseas.plundering;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import java.util.UUID;

public class UtilCreateOceanWorld {

    /**
     * Simple test for world generation.
     * @return The created world.
     */
    public static World createWorld() {
        UUID uuid = UUID.randomUUID();
        WorldCreator worldCreator = new WorldCreator("ocean_" + uuid);
        worldCreator.environment(World.Environment.NORMAL);
        worldCreator.generateStructures(false);
        worldCreator.type(WorldType.FLAT);
        worldCreator.generatorSettings("{\"structures\": {\"structures\": {}}, \"layers\": [{\"block\": \"bedrock\", \"height\": 1}, {\"block\": \"sand\", \"height\": 25}, {\"block\": \"water\", \"height\": 36}], \"biome\":\"deep_ocean\"}");
        return Bukkit.createWorld(worldCreator);
    }
}
