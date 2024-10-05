package com.matthew.plugin.commands;

import com.matthew.plugin.Minigame;
import com.matthew.plugin.commands.structure.BaseCommand;
import com.matthew.plugin.modules.game.Game;
import com.matthew.plugin.modules.game.GameModule;
import com.matthew.plugin.modules.manager.ModuleManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.logging.Logger;

public class GameCommand extends BaseCommand {

    private final Logger logger = Minigame.getInstance().getLogger();

    private final GameModule gameModule;

    public GameCommand() {
        super("game.use");
        this.gameModule = ModuleManager.getInstance().getRegisteredModule(GameModule.class);
    }

    @Override
    protected boolean execute(Player player, String[] args) {
        String actionArg = args[0].toLowerCase();

        if (args.length == 1) {
            BiConsumer<UUID, String[]> action = commandActions.get(actionArg);
            if (action != null) {
                action.accept(player.getUniqueId(), args);
                return true;
            }
        }

        messageModule.sendMessage(player, "usage");
        return true;
    }

    @Override
    protected void registerActions() {
        commandActions.put("join", (uuid, args) -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null) {
                logger.warning("Player (sender) with UUID " + uuid + " was not online when running 'join' command action");
                return;
            }

            if(gameModule.isInGame(player)) {
                messageModule.sendMessage(player, "ingame");
                return;
            }
            gameModule.addToGame(player);
            messageModule.sendMessage(player, "joingame");
        });
        commandActions.put("leave", (uuid, args) -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null) {
                logger.warning("Player (sender) with UUID " + uuid + " was not online when running 'leave' command action");
                return;
            }

            if(!gameModule.isInGame(player)) {
                messageModule.sendMessage(player, "notingame");
                return;
            }
            gameModule.removeFromGame(player);
            messageModule.sendMessage(player, "leavegame");
        });
        commandActions.put("start", (uuid, args) -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null) {
                logger.warning("Player (sender) with UUID " + uuid + " was not online when running 'start' command action");
                return;
            }

            if(!gameModule.isInGame(player)) {
                messageModule.sendMessage(player, "notingame");
                return;
            }

            gameModule.getGame(player).start();
        });
        commandActions.put("forcestart", (uuid, args) -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null) {
                logger.warning("Player (sender) with UUID " + uuid + " was not online when running 'start' command action");
                return;
            }

            if(!gameModule.isInGame(player)) {
                messageModule.sendMessage(player, "notingame");
                return;
            }

            gameModule.getGame(player).forceStart();
        });
        commandActions.put("stop", (uuid, args) -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null) {
                logger.warning("Player (sender) with UUID " + uuid + " was not online when running 'start' command action");
                return;
            }

            if(!gameModule.isInGame(player)) {
                messageModule.sendMessage(player, "notingame");
                return;
            }

            gameModule.getGame(player).stop();
        });
        commandActions.put("list", (uuid, args) -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null) {
                logger.warning("Player (sender) with UUID " + uuid + " was not online when running 'list' command action");
                return;
            }

            TextComponent.Builder message = Component.text();
            int gameCount = gameModule.getPool().getInstances().size();

            for (int i = 0; i < gameCount; i++) {
                Game game = gameModule.getPool().getInstances().get(i);
                message.append(Component.text("Game " + (i + 1) + ": " + game.getArena().getPlayers().size()));
                if (i < gameCount - 1) {
                    message.append(Component.text("\n"));
                }
            }
            player.sendMessage(message);
        });
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
