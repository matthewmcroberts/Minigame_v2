package com.matthew.plugin.phases;

import com.matthew.plugin.phases.state.BasePhase;

import java.util.ArrayList;
import java.util.List;

/**
 * Special thanks to @BattleDash for inspiring the PhaseMachine approach to manage game states.
 *
 * While the concept originated from the developer mentioned, the following implementation
 * has been crafted by the author.
 */
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
