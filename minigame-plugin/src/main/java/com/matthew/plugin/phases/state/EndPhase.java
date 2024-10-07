package com.matthew.plugin.phases.state;

import com.matthew.plugin.Minigame;
import com.matthew.plugin.modules.game.Game;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class EndPhase extends BasePhase {

    private int seconds;

    public EndPhase(Game game) {
        super(game);
        this.seconds = 5;
    }

    @Override
    public long getUpdateInterval() {
        return 20;
    }

    @Override
    public String getName() {
        return "End";
    }

    @Override
    public void start() {
        if(game.getMachine().isSkipping()) {
            return;
        }

        this.seconds = 5;
        setCanEnd(false);
        getGame().getArena().sendMessage(Component.text("End phase started!").color(NamedTextColor.YELLOW));
    }

    @Override
    public void update() {
        if (seconds <= 0) {
            setCanEnd(true);
        } else {
            if(arena.getMaxPlayers() <= arena.getPlayers().size()) {
                game.getArena().sendMessage(Component.text("End phase ending " + seconds));
                seconds--;
            } else if(seconds != 5) {
                seconds = 5;
            }
        }
    }

    @Override
    public void end() {
        //necessary game cleanup goes here
        arena.teleportAllToSpawn();
        Component stopMessage = messageModule.buildMessage("gamestop");
        arena.sendMessage(stopMessage);

        //TODO: reset arena, reset game, etc.
    }
}
