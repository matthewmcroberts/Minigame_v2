package com.matthew.plugin.modules.game;

import com.matthew.plugin.Module;
import com.matthew.plugin.modules.game.pool.GamePool;

public class GameModule implements Module {

    private final GamePool pool = GamePool.getInstance();

    public GameModule() {
    }

    public Game initNewGame(Game game) {
        return pool.initNewGame(game);
    }

    @Override
    public void setUp() {
        pool.populate();
    }

    @Override
    public void teardown() {
        pool.kill();
    }
}
