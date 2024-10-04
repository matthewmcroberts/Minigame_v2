package com.matthew.plugin.phases.state;

import com.matthew.plugin.modules.game.Game;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CountdownPhase extends BasePhase {

    private int seconds;

    public CountdownPhase(Game game) {
        super(game);
        this.seconds = 10;
    }

    @Override
    public long getUpdateInterval() {
        return 20;
    }

    @Override
    public void start() {
        this.seconds = 10;
        getGame().getArena().sendMessage(
                Component.text("Countdown started!")
                        .color(NamedTextColor.YELLOW)
        );
    }

    @Override
    public void update() {
        if (seconds <= 0) {
            setCanEnd(true);
        } else {
            if (getGame().getArena().getMaxPlayers() <= getGame().getArena().getPlayers().size()) {
                getGame().getArena().sendMessage(Component.text("Game starting in " + seconds));
                seconds--;
            } else if (seconds != 10) {
                seconds = 10;
            }
        }
    }

    @Override
    public void end() {
        getGame().getArena().sendMessage(
                Component.text("Countdown ended, moving to next phase!")
                        .color(NamedTextColor.YELLOW)
        );
    }
}
