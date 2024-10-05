package com.matthew.plugin.phases.state;

import com.matthew.plugin.modules.game.Game;
import net.kyori.adventure.text.Component;

public class CountdownPhase extends BasePhase {

    private int seconds;

    public CountdownPhase(Game game) {
        super(game);
        this.seconds = 10;
    }

    @Override
    public String getName() {
        return "Countdown";
    }

    @Override
    public long getUpdateInterval() {
        return 20;
    }

    @Override
    public void start() {
        //pre game phase logic goes here
        if(game.getMachine().isSkipping()) {
            return;
        }

        this.seconds = 10;
        setCanEnd(false);
    }

    @Override
    public void update() {
        if (seconds <= 0) {
            setCanEnd(true);
        } else {
            if (game.getArena().getMaxPlayers() <= game.getArena().getPlayers().size()) {
                Component countdownMessage = messageModule.buildMessage("countdown", seconds);
                game.getArena().sendMessage(countdownMessage);
                seconds--;
            } else if (seconds != 10) {
                seconds = 10;
            }
        }
    }

    @Override
    public void end() {

    }
}
