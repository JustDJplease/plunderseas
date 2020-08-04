package me.newt.plunderseas.managers;

import me.newt.plunderseas.PlunderSeas;
import me.newt.plunderseas.runnables.ActionBarRunnable;
import me.newt.plunderseas.runnables.WorldTimeRunnable;
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
        runnables.add(scheduler.runTaskTimer(plunderSeas, new WorldTimeRunnable(plunderSeas), 1L, 1L));
        runnables.add(scheduler.runTaskTimer(plunderSeas, new ActionBarRunnable(plunderSeas), 1L, 1L));
    }

    /**
     * Stops all running tasks for this plugin.
     * @deprecated Might break the plugin.
     */
    public void stopRunnables() {
        runnables.forEach(BukkitTask::cancel);
    }
}
