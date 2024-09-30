package com.matthew.plugin.phases;

import com.matthew.plugin.phases.state.BasePhase;

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

    //Sticking with arraylist for now due to frequent indexing
    private final List<BasePhase> phases;

    private final long updateInterval;

    private int currentPhase;

    //Boolean determining whether next phase is to be skipped
    private boolean skipping;

    public PhaseMachine(long updateInterval, BasePhase... phases) {
        this.phases = new ArrayList<>(List.of(phases));
        this.updateInterval = updateInterval;
        this.currentPhase = 0;
        this.skipping = false;
    }
    public PhaseMachine(BasePhase... phases) {
        this.phases = new ArrayList<>(List.of(phases));
        this.updateInterval = 1;
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

    public void start() {
        //index first phase and call its onStart method
        //schedule repeating task for update method
    }

    //Will need to keep in mind synchronization of threads and conflicting concurrent main thread modifications
    public void update() {
        //index current phase and call onUpdate
        //Also determine if current phase is ready to proceed to next phase or skip boolean is set to true
    }

    public void end() {
        //index current phase and call onEnd
        //Stop repeating task
    }
}
