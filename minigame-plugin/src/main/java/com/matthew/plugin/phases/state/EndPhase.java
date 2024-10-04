package com.matthew.plugin.phases.state;

import com.matthew.plugin.modules.game.Game;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class EndPhase extends BasePhase {

    private int seconds;

    public EndPhase(Game game) {
        super(game);
        this.seconds = 10;
    }

    @Override
    public void start() {
        this.seconds = 10;
        getGame().getArena().sendMessage(Component.text("End phase started!").color(NamedTextColor.YELLOW));
    }

    @Override
    public void update() {
        if (seconds <= 0) {
            setCanEnd(true);
        } else {
            if(getGame().getArena().getMaxPlayers() <= getGame().getArena().getPlayers().size()) {
                getGame().getArena().sendMessage(Component.text("End phase ending " + seconds));
                seconds--;
            } else if(seconds != 10) {
                seconds = 10;
            }
        }
    }

    @Override
    public void end() {
        getGame().getArena().sendMessage(Component.text("End phase ending!").color(NamedTextColor.YELLOW));
    }

    @Override
    public long getUpdateInterval() {
        return 20;
    }
}
