package com.matthew.plugin.modules.game;

import lombok.Getter;

@Getter
public abstract class Game {

    private final GameArena arena;

    public Game() {
        arena = new GameArena();
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

    public abstract void start();
}
