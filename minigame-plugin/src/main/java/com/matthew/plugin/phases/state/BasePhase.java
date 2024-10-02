package com.matthew.plugin.phases.state;

public abstract class BasePhase {

    public abstract void start();

    public abstract void update();

    public abstract boolean canEnd();

    public abstract void end();
}
