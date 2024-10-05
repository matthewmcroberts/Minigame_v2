package com.matthew.plugin.modules.game;

import com.matthew.plugin.arena.Arena;
import com.matthew.plugin.arena.ArenaState;
import com.matthew.plugin.Team;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class GameArena implements Arena {

    private final Set<UUID> players;

    private final Location playerSpawnLoc;

    private final World world;

    public GameArena(Location playerSpawnLoc) {
        players = new HashSet<>();
        this.playerSpawnLoc = playerSpawnLoc;
        this.world = playerSpawnLoc.getWorld();
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public boolean isSpectator(Player player) {
        return false;
    }

    @Override
    public boolean isSpectator(UUID player) {
        return false;
    }

    @Override
    public boolean isReSpawning(Player player) {
        return false;
    }

    @Override
    public boolean isReSpawning(UUID player) {
        return false;
    }

    @Override
    public void init(World world) {

    }

    @Override
    public Player getPlayer(Player player) {
        for(UUID uuid : players) {
            if(uuid.equals(player.getUniqueId())) {
                return player;
            }
        }
        return null;
    }

    @Override
    public Set<UUID> getPlayers() {
        return players;
    }

    @Override
    public Team getTeam(Player player) {
        return null;
    }

    @Override
    public Team getTeam(UUID player) {
        return null;
    }

    @Override
    public ArenaState getState() {
        return null;
    }

    @Override
    public void changeState(ArenaState state) {

    }

    @Override
    public boolean isFull() {
        return getMaxPlayers() <= players.size();
    }

    @Override
    public int getMaxPlayers() {
        return 1; //hardcoded for testing
    }

    @Override
    public int getMaxPlayersInTeam() {
        return 0;
    }

    @Override
    public boolean addPlayer(Player player) {
        return players.add(player.getUniqueId());
    }

    @Override
    public boolean removePlayer(Player player) {
        return players.remove(player.getUniqueId());
    }

    @Override
    public boolean addSpectator(Player player) {
        return false;
    }

    @Override
    public boolean removeSpectator(Player player) {
        return false;
    }

    @Override
    public List<Team> getTeams() {
        return List.of();
    }

    @Override
    public void addPlacedBlock(Block block) {

    }

    @Override
    public void removePlacedBlock(Block block) {

    }

    @Override
    public boolean isPlacedBlock(Block block) {
        return false;
    }

    @Override
    public boolean isAllowingBlockPlace() {
        return false;
    }

    @Override
    public void checkWinner() {

    }

    @Override
    public Team getWinner() {
        return null;
    }

    @Override
    public void disable() {

    }

    @Override
    public void restart() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public void teleportAllToSpawn() {
        players.forEach(player -> {
            Player bukkitPlayer = Bukkit.getPlayer(player);
            if(bukkitPlayer != null) {
                bukkitPlayer.teleport(getSpawnLocation());
            }
        });
    }

    @Override
    public void teleportToSpawn(Player player) {
        player.teleport(getSpawnLocation());
    }

    @Override
    public Location getSpawnLocation() {
        return playerSpawnLoc.clone();
    }

    @Override
    public void sendMessage(Component message) {
        for (UUID playerUuid : players) {
            Player player = Bukkit.getPlayer(playerUuid);
            if (player != null) {
                player.sendMessage(message);
            }
        }
    }
}
