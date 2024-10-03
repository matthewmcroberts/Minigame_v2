package com.matthew.plugin.modules.game.pool;

import com.matthew.plugin.Minigame;
import com.matthew.plugin.modules.game.Game;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

@Getter
public class GamePool {

    private final Logger logger = Minigame.getInstance().getLogger();

    private final List<Game> instances;

    private final Function<GamePool, Game> gameFunction;

    @Setter
    private int minCount;

    public GamePool(int minCount, Function<GamePool, Game> gameFunction) {
        this.minCount = minCount;
        instances = new ArrayList<>();
        this.gameFunction = gameFunction;
    }

    public void populate() {
        if (instances.size() >= minCount) {
            logger.info("Could not populate game pool due to min count already reached");
            return;
        }

        for (int i = 0; i < minCount; i++) {
            boolean success = initNewGame();
            if(!success) {
                logger.warning("Failed to initialize game instance");
            }
        }
        logger.info(instances.toString());
    }

    //TODO: Checks for if player is in a game
    public void addPlayer(final Player player) {
        for(Game game : instances) {
            if(game.getArena().isFull()) {
                continue;
            }
            boolean success = game.getArena().addPlayer(player);
            logger.info(game.getArena().getPlayers().toString());

            if(success) {
                break;
            }
        }
    }

    //TODO: Checks for if player is in a game
    public void removePlayer(final Player player) {
        for(Game game : instances) {
            if(game.getArena().getPlayers().contains(player.getUniqueId())) {
                game.getArena().removePlayer(player);
            }
        }
    }

    public boolean initNewGame() {
        logger.info("Initializing new game instances");
        return instances.add(gameFunction.apply(this));
    }

    public void killGame(Game game) {
        if (!instances.contains(game)) {
            logger.severe("Could not kill existing game because game instance was not " +
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
