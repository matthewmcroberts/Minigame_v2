package com.matthew.plugin.modules.game;

import com.matthew.plugin.IGame;

public class Game implements IGame {

    @Override
    public boolean isPvp() {
        return false;
    }

    @Override
    public void setPvp(boolean pvp) {

    }
}
