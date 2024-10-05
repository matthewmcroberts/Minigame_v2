package com.matthew.plugin.modules.game;

import com.matthew.plugin.phases.PhaseMachine;
import lombok.Getter;

@Getter
public abstract class Game {

    private final GameArena arena;

    private final PhaseMachine machine;

    public Game() {
        arena = new GameArena();
        this.machine = createPhaseMachine();
    }

    public boolean isPvp() {
        return false;
    }

    public void setPvp(boolean pvp) {

    }

    public boolean end() {
        return false;
    }

    public boolean kill() {
        return false;
    }

    protected abstract PhaseMachine createPhaseMachine();

    /**
     * Starts initial phase
     */
    public abstract void start();

    /**
     * Skips initial phase and starts next phase
     */
    public abstract void forceStart();

    /**
     * Skip to final phase and trigger end method
     */
    public abstract void stop();
}
