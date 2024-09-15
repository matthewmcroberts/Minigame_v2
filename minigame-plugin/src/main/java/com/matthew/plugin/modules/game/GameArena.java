package com.matthew.plugin.modules.game;

import com.matthew.plugin.arena.Arena;
import com.matthew.plugin.arena.ArenaState;
import com.matthew.plugin.Team;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class GameArena implements Arena {

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
    public int getMaxPlayers() {
        return 0;
    }

    @Override
    public int getMaxPlayersInTeam() {
        return 0;
    }

    @Override
    public boolean addPlayer(Player player) {
        return false;
    }

    @Override
    public boolean removePlayer(Player player) {
        return false;
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
        return null;
    }
}
