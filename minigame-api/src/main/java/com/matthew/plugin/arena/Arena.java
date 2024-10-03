package com.matthew.plugin.arena;

import com.matthew.plugin.Team;
import net.kyori.adventure.text.Component;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface Arena {

    String getName();

    boolean isSpectator(Player player);

    boolean isSpectator(UUID player);

    /**
     * Check if a player in the spectator state is respawning
     */
    boolean isReSpawning(Player player);

    boolean isReSpawning(UUID player);

    void init(World world);

    Player getPlayer(Player player);

    Set<UUID> getPlayers();

    Team getTeam(Player player);

    Team getTeam(UUID player);

    ArenaState getState();

    void changeState(ArenaState state);

    boolean isFull();

    int getMaxPlayers();

    int getMaxPlayersInTeam();

    boolean addPlayer(Player player);

    boolean removePlayer(Player player);

    boolean addSpectator(Player player);

    boolean removeSpectator(Player player);

    List<Team> getTeams();

    void addPlacedBlock(Block block); //unsure if I want to allow players to place blocks

    void removePlacedBlock(Block block);

    boolean isPlacedBlock(Block block);

    boolean isAllowingBlockPlace();

    void checkWinner();

    @Nullable
    Team getWinner();

    void disable();

    void restart();

    void destroy();

    World getWorld();

    void sendMessage(Component message);
}
