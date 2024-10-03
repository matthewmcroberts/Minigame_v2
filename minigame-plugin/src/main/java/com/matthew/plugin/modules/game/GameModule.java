package com.matthew.plugin.modules.game;

import com.matthew.plugin.Module;
import com.matthew.plugin.modules.game.pool.GamePool;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

@RequiredArgsConstructor
public class GameModule implements Module {

    @Setter
    @Getter
    private GamePool pool;

    private final JavaPlugin plugin;

//    public Game initNewGame() {
//        return pool.initNewGame();
//    }

    public void addToGame(final Player player) {
        pool.addPlayer(player);
    }

    public void removeFromGame(final Player player) {
        pool.removePlayer(player);
    }

    public Game getGame(final Player player) {
        for(Game game: pool.getInstances()) {
            if(game.getArena().getPlayers().contains(player.getUniqueId())) {
                return game;
            }
        }
        return null;
    }

    @Override
    public void setUp() {
        plugin.getLogger().info("Setting up game pool...");
    }

    @Override
    public void teardown() {
        pool.kill();
    }
}
