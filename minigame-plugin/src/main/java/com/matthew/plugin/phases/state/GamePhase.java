package com.matthew.plugin.phases.state;

import com.matthew.plugin.modules.game.Game;
import net.kyori.adventure.text.Component;

public class GamePhase extends BasePhase {

    private int seconds;

    public GamePhase(Game game) {
        super(game);
    }

    @Override
    public void start() {
        getGame().getArena().sendMessage(Component.text("Game phase started"));
    }

    @Override
    public void update() {
        if (seconds <= 0) {
            setCanEnd(true);
        } else {
            if(getGame().getArena().getMaxPlayers() <= getGame().getArena().getPlayers().size()) {
                seconds--;
            } else if(seconds != 10) {
                seconds = 10;
            }
        }
    }

    @Override
    public void end() {
        getGame().getArena().sendMessage(Component.text("Game phase ending"));
    }

    @Override
    public long getUpdateInterval() {
        return 20;
    }
}
