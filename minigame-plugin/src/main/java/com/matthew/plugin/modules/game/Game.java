package com.matthew.plugin.modules.game;

import com.matthew.plugin.arena.Arena;
import com.matthew.plugin.modules.manager.ModuleManager;
import com.matthew.plugin.modules.messages.MessageModule;
import com.matthew.plugin.phases.PhaseMachine;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

@Getter
public abstract class Game {

    protected final Arena arena;

    protected MessageModule messageModule = ModuleManager.getInstance().getRegisteredModule(MessageModule.class);

    protected final PhaseMachine machine;

    //TODO: Create a more dynamic way of getting the world for a game arena
    public Game() {
        arena = new GameArena(new Location(Bukkit.getWorld("world"), 252, 84, 100));
        this.machine = createPhaseMachine();
    }

    public boolean isPvp() {
        return false;
    }

    public void setPvp(boolean pvp) {

    }

    public boolean end() {
        return false;
    }

    public boolean kill() {
        return false;
    }

    protected abstract PhaseMachine createPhaseMachine();

    public abstract String getId();

    /**
     * Starts initial phase
     */
    public abstract void start();

    /**
     * Skips initial phase and starts next phase
     */
    public abstract void forceStart();

    /**
     * Execute end of game cleanup. If called before final phase finishes, the game will stop abruptly.
     */
    public abstract void forcestop();
}
