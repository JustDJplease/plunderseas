package me.newt.plunderseas.runnables;

import me.newt.plunderseas.PlunderSeas;
import me.newt.plunderseas.events.PlunderSeasSunriseEvent;
import org.bukkit.World;

public class WorldTimeRunnable implements Runnable {

    private final PlunderSeas plunderSeas;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                    CONSTRUCTOR                                    //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    public WorldTimeRunnable(PlunderSeas plunderSeas) {
        this.plunderSeas = plunderSeas;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                     RUNNABLE                                      //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    @Override
    public void run() {
        for (World world : plunderSeas.getServer().getWorlds()) {
            if (world.getEnvironment() != World.Environment.NORMAL) {
                continue;
            }
            long currentTime = world.getTime();
            if (currentTime == 1000) {
                plunderSeas.getServer().getPluginManager().callEvent(new PlunderSeasSunriseEvent(world));
            }
        }
    }
}
