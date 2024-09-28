package com.matthew.plugin.modules.game.pool;

import com.matthew.plugin.Minigame;
import com.matthew.plugin.modules.game.Game;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Getter
public class GamePool {

    @Setter
    private int minCount;

    private final List<Game> instances;

    private final Function<GamePool, Game> gameFunction;

    public GamePool(int minCount, Function<GamePool, Game> gameFunction) {
        this.minCount = minCount;
        instances = new ArrayList<>();
        this.gameFunction = gameFunction;
    }

    public void populate() {
        if (!instances.isEmpty()) {
            return;
        }

        for (int i = 0; i < minCount; i++) {
            initNewGame();
        }
    }

    public boolean initNewGame() {
        return instances.add(gameFunction.apply(this));
    }

    public void killGame(Game game) {
        if (!instances.contains(game)) {
            Minigame.getInstance().getLogger().severe("Could not kill existing game because game instance was not " +
                    "found in pool. It is possible that the game instance was not properly initialized with pool. Contact Developer.");
            return;
        }
        game.kill();
    }

    public void kill() {
        instances.forEach(Game::kill);
        instances.clear();
    }
}
