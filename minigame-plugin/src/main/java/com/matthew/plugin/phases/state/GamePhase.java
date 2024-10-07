package com.matthew.plugin.phases.state;

import com.matthew.plugin.modules.game.Game;
import com.matthew.plugin.modules.manager.ModuleManager;
import com.matthew.plugin.modules.messages.MessageModule;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class GamePhase extends BasePhase {

    private final MessageModule messageModule = ModuleManager.getInstance().getRegisteredModule(MessageModule.class);

    private int seconds;

    public GamePhase(Game game) {
        super(game);
        this.seconds = 5;
    }

    @Override
    public long getUpdateInterval() {
        return 20;
    }

    @Override
    public String getName() {
        return "Game";
    }

    @Override
    public void start() {
        if(game.getMachine().isSkipping()) {
            return;
        }

        this.seconds = 5;
        setCanEnd(false);
        Component startMessage = messageModule.buildMessage("gamestart");
        getGame().getArena().sendMessage(startMessage);
    }

    @Override
    public void update() {
        if (seconds <= 0) {
            setCanEnd(true);
        } else {
            if(getGame().getArena().getMaxPlayers() <= getGame().getArena().getPlayers().size()) {
                getGame().getArena().sendMessage(Component.text("Game phase ending " + seconds));
                seconds--;
            } else if(seconds != 5) {
                seconds = 5;
            }
        }
    }

    @Override
    public void end() {
        getGame().getArena().sendMessage(Component.text("Game phase ended, moving to next phase!").color(NamedTextColor.YELLOW));
    }
}
