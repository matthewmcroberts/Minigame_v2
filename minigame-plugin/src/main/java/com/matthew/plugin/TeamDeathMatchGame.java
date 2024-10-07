package com.matthew.plugin;

import com.matthew.plugin.modules.game.Game;
import com.matthew.plugin.modules.game.pool.GamePool;
import com.matthew.plugin.phases.PhaseMachine;
import com.matthew.plugin.phases.state.CountdownPhase;
import com.matthew.plugin.phases.state.EndPhase;
import com.matthew.plugin.phases.state.GamePhase;
import lombok.Getter;

@Getter
public class TeamDeathMatchGame extends Game {

    private final GamePool pool;

    private final String id;

    public TeamDeathMatchGame(GamePool pool) {
        this.pool = pool;
        id = "TDM-" + pool.getInstances().size() + 1;
    }

    @Override
    protected PhaseMachine createPhaseMachine() {
        return new PhaseMachine(
                new CountdownPhase(this),
                new GamePhase(this),
                new EndPhase(this)
        );
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void start() {
        init();
        machine.onStart();
    }

    @Override
    public void forceStart() {
        init();
        machine.skip(); //skip first phase (countdown phase)
        machine.onStart();
    }

    @Override
    public void forcestop() {
        machine.stop();
    }

    private void init() {
        arena.teleportAllToSpawn();
    }
}
