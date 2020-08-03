package me.newt.plunderseas.runnables;

import me.newt.plunderseas.PlunderSeas;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class RunnableManager {

    private final PlunderSeas plunderSeas;
    private final List<BukkitTask> runnables;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                    CONSTRUCTOR                                    //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    public RunnableManager(PlunderSeas plunderSeas) {
        this.plunderSeas = plunderSeas;
        this.runnables = new ArrayList<>();
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    //                                  PUBLIC METHODS                                   //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    /**
     * Start all running tasks for this plugin.
     */
    public void startRunnables() {
        BukkitScheduler scheduler = plunderSeas.getServer().getScheduler();
        BukkitTask worldTimeRunnable = scheduler.runTaskTimer(plunderSeas, new WorldTimeRunnable(plunderSeas), 1L, 1L);
        runnables.add(worldTimeRunnable);
    }

    /**
     * Stops all running tasks for this plugin.
     *
     * @deprecated Might break the plugin.
     */
    public void stopRunnables() {
        for (BukkitTask task : runnables) {
            task.cancel();
        }
    }
}
