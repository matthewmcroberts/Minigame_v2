package com.matthew.plugin.modules.game;

import com.matthew.plugin.Module;
import com.matthew.plugin.TeamDeathMatchGame;
import com.matthew.plugin.modules.game.pool.GamePool;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@RequiredArgsConstructor
public class GameModule implements Module {

    private GamePool pool;

    private final JavaPlugin plugin;

//    public Game initNewGame() {
//        return pool.initNewGame();
//    }

    @Override
    public void setUp() {
        plugin.getLogger().info("Setting up game pool...");
        pool = new GamePool(1, TeamDeathMatchGame::new);
        pool.populate();
    }

    @Override
    public void teardown() {
        pool.kill();
    }
}
