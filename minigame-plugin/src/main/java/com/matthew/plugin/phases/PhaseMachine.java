package com.matthew.plugin.phases;

import com.matthew.plugin.Minigame;
import com.matthew.plugin.phases.state.BasePhase;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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

    @Getter
    private final List<BasePhase> phases;

    private final List<BukkitTask> tasks = new ArrayList<>();

    private int currentPhase = 0;

    @Getter
    @Setter
    private boolean stopped = false;

    @Getter
    private boolean running = false;

    @Getter
    private boolean skipping = false;

    public PhaseMachine(long updateInterval, BasePhase... phases) {
        this.phases = new ArrayList<>(List.of(phases));
    }

    public PhaseMachine(BasePhase... phases) {
        this.phases = new ArrayList<>(List.of(phases));
    }

    public void add(BasePhase phase) {
        phases.add(phase);
    }

    public void addNext(BasePhase phase) {
        phases.add(currentPhase + 1, phase);
    }

    public void skip() {
        skipping = true;
    }

    public BasePhase getCurrentPhase() {
        return phases.get(currentPhase);
    }

    public int getCurrentPhaseIndex() {
        return currentPhase;
    }

    public void onStart() {
        if (phases.isEmpty()) {
            plugin.getLogger().warning("No phases found! Machine could not be started!");
            return;
        }
        currentPhase = 0;
        phases.get(currentPhase).start();
        schedulePhaseUpdate();
        running = true;
    }

    public void onUpdate() {
        if (phases.isEmpty()) {
            cancelAllTasks();
            return;
        }

        if (skipping) {
            plugin.getLogger().warning("Skipping phase: " + phases.get(currentPhase).getName());
            skipping = false;
            moveToNextPhase();
            return;
        }

        if (phases.get(currentPhase).canEnd()) {
            moveToNextPhase();
        } else {
            phases.get(currentPhase).update();
        }
    }

    public void onEnd() {
        running = false;
    }

    public void stop() {
        cancelAllTasks();

        phases.getLast().end(); //perform any game cleanup
        running = false;
        currentPhase = 0;
        skipping = false;
    }


    private void schedulePhaseUpdate() {
        cancelAllTasks();
        long updateInterval = phases.get(currentPhase).getUpdateInterval();
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, this::onUpdate, 0L, updateInterval);
        tasks.add(task);
    }

    private void moveToNextPhase() {
        phases.get(currentPhase).end();
        currentPhase++;

        if (currentPhase < phases.size()) {
            phases.get(currentPhase).start();
            schedulePhaseUpdate();
        } else {
            onEnd();
            phases.get(currentPhase - 1).getGame().getArena().sendMessage(
                    Component.text("Ending all phases")
                            .color(NamedTextColor.YELLOW)
            );
            cancelAllTasks();
        }
    }

    private void cancelAllTasks() {
        tasks.forEach(BukkitTask::cancel);
        tasks.clear();
    }

    private boolean isFinalPhase() {
        return currentPhase == phases.size() - 1;
    }
}
