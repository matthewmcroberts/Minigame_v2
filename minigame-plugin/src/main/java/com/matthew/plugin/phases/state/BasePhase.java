package com.matthew.plugin.phases.state;

import com.matthew.plugin.modules.game.Game;
import com.matthew.plugin.modules.manager.ModuleManager;
import com.matthew.plugin.modules.messages.MessageModule;
import lombok.Getter;

public abstract class BasePhase {

    protected MessageModule messageModule = ModuleManager.getInstance().getRegisteredModule(MessageModule.class);

    private boolean canEnd;

    @Getter
    private final Game game;

    public BasePhase(Game game) {
        this.game = game;
        this.canEnd = false;
    }

    protected void setCanEnd(boolean canEnd) {
        this.canEnd = canEnd;
    }

    public boolean canEnd() {
        return canEnd;
    }

    public abstract void start();

    public abstract void update();

    public abstract void end();

    public abstract long getUpdateInterval();
}
