package com.matthew.plugin;

import com.matthew.plugin.modules.game.GameModule;
import com.matthew.plugin.modules.manager.ModuleManager;
import com.matthew.plugin.modules.messages.MessageModule;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class Minigame extends JavaPlugin {

    @Getter
    private static Minigame instance;

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
                        .registerModule(new GameModule());

        //Setup modules
        getLogger().info("Setting up Modules...");
        moduleManager.setUp();

        getLogger().info("Minigame plugin enabled");
    }

    @Override
    public void onDisable() {
        moduleManager.teardown();
    }
}