package com.matthew.plugin.modules.phases;

//Could possibly make this an inner class for PhaseModule

import com.matthew.plugin.modules.phases.state.BasePhase;

import java.util.ArrayList;
import java.util.List;

public class PhaseMachine {

    private static PhaseMachine instance;

    private final List<BasePhase> phases = new ArrayList<>();

    public static PhaseMachine getInstance() {
        if(instance == null) {
            instance = new PhaseMachine();
        }
        return instance;
    }
}
