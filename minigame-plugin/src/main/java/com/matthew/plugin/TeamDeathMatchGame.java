package com.matthew.plugin;

import com.matthew.plugin.modules.game.Game;
import com.matthew.plugin.modules.game.pool.GamePool;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TeamDeathMatchGame extends Game {

    private final GamePool pool;
}
