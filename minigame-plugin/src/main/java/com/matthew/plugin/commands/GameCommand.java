package com.matthew.plugin.commands;

import com.matthew.plugin.Minigame;
import com.matthew.plugin.commands.structure.BaseCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.logging.Logger;

public class GameCommand extends BaseCommand {

    private final Logger logger = Minigame.getInstance().getLogger();

    public GameCommand() {
        super("game.use");
    }

    @Override
    protected boolean execute(Player player, String[] args) {
        return false;
    }

    @Override
    protected void registerActions() {
        commandActions.put("join", (uuid, args) -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null) {
                logger.warning("Player (sender) with UUID " + uuid + " was not online when running 'set' for rank command.");
                return;
            }
           //add player to an open game
        });
        commandActions.put("leave", (uuid, args) -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null) {
                logger.warning("Player (sender) with UUID " + uuid + " was not online when running 'set' for rank command.");
                return;
            }
            //remove player from current game
        });
        commandActions.put("start", (uuid, args) -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null) {
                logger.warning("Player (sender) with UUID " + uuid + " was not online when running 'set' for rank command.");
                return;
            }

            //start the game the player is currently in
        });
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
