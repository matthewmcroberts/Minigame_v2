package com.matthew.plugin.modules.game;

public abstract class Game {

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
}
