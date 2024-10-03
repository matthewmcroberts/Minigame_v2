package com.matthew.plugin;

import com.matthew.plugin.commands.GameCommand;
import com.matthew.plugin.modules.game.GameModule;
import com.matthew.plugin.modules.game.pool.GamePool;
import com.matthew.plugin.modules.manager.ModuleManager;
import com.matthew.plugin.modules.messages.MessageModule;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Minigame extends JavaPlugin {

    @Getter
    private static Minigame instance;

    private GamePool pool;

    private ModuleManager moduleManager;

    @Override
    public void onLoad() {
        if(!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
    }

    @Override
    public void onEnable() {
        instance = this;

        //Init singleton instance of ModuleManager
        moduleManager = ModuleManager.getInstance();

        //Register modules
        getLogger().info("Registering Modules...");
        moduleManager.registerModule(new MessageModule(this))
                        .registerModule(new GameModule(this));

        //Setup modules
        getLogger().info("Setting up Modules...");
        moduleManager.setUp();

        //Init game pool(s)
        getLogger().info("Initializing game pool(s)...");
        pool = new GamePool(5, TeamDeathMatchGame::new);

        //Populate game pool(s)
        getLogger().info("Populating game pool(s)...");
        pool.populate();

        moduleManager.getRegisteredModule(GameModule.class).setPool(pool);

        Objects.requireNonNull(getCommand("game")).setExecutor(new GameCommand());

        getLogger().info("Minigame plugin enabled");
    }

    @Override
    public void onDisable() {
        moduleManager.teardown();
    }
}