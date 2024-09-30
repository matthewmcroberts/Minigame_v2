package com.matthew.plugin.phases.state;

public abstract class BasePhase {

    public abstract void onStart();

    public abstract void onUpdate();

    public abstract boolean canEnd();

    public abstract void onEnd();
}
