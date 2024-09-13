package com.matthew.plugin.commands;

import com.matthew.plugin.commands.structure.BaseCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GameCommand extends BaseCommand {

    public GameCommand() {
        super("game.use");
    }

    @Override
    protected boolean execute(Player player, String[] args) {
        return false;
    }

    @Override
    protected void registerActions() {

    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
