package com.matthew.plugin;

import com.matthew.plugin.modules.game.Game;
import com.matthew.plugin.modules.game.pool.GamePool;
import com.matthew.plugin.phases.PhaseMachine;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class TeamDeathMatchGame extends Game {

    private final GamePool pool;

    private final PhaseMachine machine;

    public TeamDeathMatchGame(GamePool pool) {
        this.pool = pool;
        this.machine = new PhaseMachine();
    }

    //start will get called once canStart method returns True. Can start method is called everytime GameJoinEvent is fired
    @Override
    public void start() {
        machine.start();
    }
}
