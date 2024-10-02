package com.matthew.plugin;

import com.matthew.plugin.modules.game.Game;
import com.matthew.plugin.modules.game.pool.GamePool;
import com.matthew.plugin.phases.PhaseMachine;
import com.matthew.plugin.phases.state.CountdownPhase;
import com.matthew.plugin.phases.state.EndPhase;
import com.matthew.plugin.phases.state.GamePhase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class TeamDeathMatchGame extends Game {

    private final GamePool pool;

    private final PhaseMachine machine;

    public TeamDeathMatchGame(GamePool pool) {
        this.pool = pool;
        this.machine = new PhaseMachine(
                new CountdownPhase(),
                new GamePhase(),
                new EndPhase()
        );
        init();
    }

    //start will get called once canStart method returns True. Can start method is called everytime GameJoinEvent is fired
    @Override
    public void init() {
        machine.onStart();
    }
}
