package com.matthew.plugin.phases;

import com.matthew.plugin.Minigame;
import com.matthew.plugin.phases.state.BasePhase;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Special thanks to "GameManager", a repository owned by @BattleDash, for inspiring the PhaseMachine approach to manage
 * game states.
 * <a href="https://github.com/BattleDash/GameManager">Repo found here</a></p>
 *
 * <p>The following implementation contains mixed code written by the author "Matthew (GoofIt/Mahht)" and inspiration above.</p>
 */
public class PhaseMachine {

    private final JavaPlugin plugin = Minigame.getInstance();
    //Sticking with arraylist for now due to frequent indexing
    private final List<BasePhase> phases;

    private final List<BukkitTask> tasks;

    private int currentPhase;

    //Boolean determining whether next phase is to be skipped
    private boolean skipping;

    public PhaseMachine(long updateInterval, BasePhase... phases) {
        this.phases = new ArrayList<>(List.of(phases));
        this.tasks = new ArrayList<>();
        this.currentPhase = 0;
        this.skipping = false;
    }
    public PhaseMachine(BasePhase... phases) {
        this.phases = new ArrayList<>(List.of(phases));
        this.tasks = new ArrayList<>();
        this.currentPhase = 0;
        this.skipping = false;
    }

    public void add(BasePhase phase) {
        phases.add(phase);
    }

    /**
     * The PhaseMachine class maintains a "has-a" relationship with its associated Game.
     * This method adds a phase immediately after the currently active phase,
     * rather than appending it to the end of the list.
     *
     * @param phase The phase to be added directly after the current active phase.
     */
    public void addNext(BasePhase phase) {
        phases.add(currentPhase + 1, phase);
    }

    public void skip() {
        skipping = true;
    }

    public void onStart() {
        if(phases.isEmpty()) {
            return;
        }
        phases.get(currentPhase).start();
        schedulePhaseUpdate();
    }

    //Will need to keep in mind synchronization of threads and conflicting concurrent main thread modifications
    public void onUpdate() {
        if(phases.isEmpty()) {
            tasks.forEach(BukkitTask::cancel);
            return;
        }

        if(skipping) {
            currentPhase++;
            skipping = false;
            return;
        }

        if(phases.get(currentPhase).canEnd()) {
            if(currentPhase + 1 >= phases.size()) {
                tasks.forEach(BukkitTask::cancel);
                onEnd();
                for(BasePhase phase : phases) {
                    phase.getGame().getArena().sendMessage(Component.text("Ending all phases"));
                }
                return;
            }

            //TODO: Some of this logic should be in the onEnd method

            phases.get(currentPhase).end(); //end current phase
            currentPhase++; //move to next phase
            phases.get(currentPhase).start(); //start next phase
            schedulePhaseUpdate(); //reschedule the new phase's update interval
            return; //return and it will come back to onupdate
        }

        phases.get(currentPhase).update();
    }

    public void onEnd() {
        if(phases.isEmpty()) {
            return;
        }
        phases.get(currentPhase).end();
    }

    private void schedulePhaseUpdate() {
        tasks.forEach(BukkitTask::cancel);
        tasks.clear();

        long updateInterval = phases.get(currentPhase).getUpdateInterval();

        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, this::onUpdate, 0L, updateInterval);
        tasks.add(task);
    }
}
