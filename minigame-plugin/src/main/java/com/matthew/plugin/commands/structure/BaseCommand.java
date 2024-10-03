package com.matthew.plugin.commands.structure;

import com.matthew.plugin.modules.manager.ModuleManager;
import com.matthew.plugin.modules.messages.MessageModule;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;

public abstract class BaseCommand implements TabExecutor {

    protected final String permission;

    protected final MessageModule messageModule;

    protected final Map<String, BiConsumer<UUID, String[]>> commandActions = new HashMap<>();


    public BaseCommand(String permission) {
        this.permission = permission;
        this.messageModule = ModuleManager.getInstance().getRegisteredModule(MessageModule.class);
        registerActions();
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = sender instanceof Player ? (Player) sender : null;

        if (player == null) {
            //Bukkit.getLogger().info("Sender must be a player");
            return true;
        }

        if (!player.hasPermission(permission)) {
            messageModule.sendMessage(player, "noperm");
            return true;
        }

        if (args.length == 0) {
            messageModule.sendMessage(player, "usage");
            return true;
        }

        return execute(player, args);
    }


    /**
     * Executes the specific command logic.
     *
     * @param player the player who executed the command
     * @param args the arguments passed with the command
     * @return true if the command was executed successfully, false otherwise
     */
    protected abstract boolean execute(Player player, String[] args);

    /**
     * Registers the actions associated with the command.
     * This method is intended to be implemented by subclasses to define
     * the actions that can be performed by the command.
     */
    protected abstract void registerActions();
}
