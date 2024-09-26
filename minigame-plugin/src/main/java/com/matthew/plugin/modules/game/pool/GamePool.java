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

    private static GamePool instance;

    @Setter
    private int minCount;

    private final List<Game> instances;

    private final Function<GamePool, Game> gameFunction;

    private GamePool(int minCount, Function<GamePool, Game> gameFunction) {
        this.minCount = minCount;
        instances = new ArrayList<>();
        this.gameFunction = gameFunction;
    }

    public static GamePool getInstance() {
        if (instance == null) {
            instance = new GamePool(3);
        }
        return instance;
    }

    public void populate() {
//        if(!instances.isEmpty()) {
//            return;
//        }
//
//        for (int i = 0; i < minCount; i++) {
//            Game game = new Game();
//            instances.add(game);
//        }
    }

    public Game initNewGame(Game game) {
        instances.add(game);
        return game;
    }

    public void killGame(Game game) {
        if(!instances.contains(game)) {
            Minigame.getInstance().getLogger().severe("Could not kill existing game because game instance was not " +
                    "found in pool. It is possible that the game instance was not properly initialized with pool.");
            return;
        }
        game.kill();
    }

    public void kill() {
        instances.forEach(Game::kill);
        instances.clear();
    }
}
