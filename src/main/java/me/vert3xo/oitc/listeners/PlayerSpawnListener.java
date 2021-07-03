package me.vert3xo.oitc.listeners;

import me.vert3xo.oitc.OneInTheChamber;
import me.vert3xo.oitc.game.Game;
import me.vert3xo.oitc.game.GameState;
import me.vert3xo.oitc.utils.ScoreHelper;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

public class PlayerSpawnListener implements Listener {
    private final OneInTheChamber plugin = OneInTheChamber.getPlugin();
    private final Game game = plugin.getGame();
    private final GameState gameState = game.getGameState();
    private final FileConfiguration config = plugin.getConfig();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();

        ScoreHelper helper = ScoreHelper.createScore(player);
        ScoreHelper.setDefaultScore(player);
    }

    @EventHandler
    public void onSpawn(PlayerSpawnLocationEvent event) {
        if (config.getString("lobbyLocation.world").equals("")) return;

        Location spawnLoc;

        if (gameState != GameState.RUNNING) {
            spawnLoc = new Location(
                    Bukkit.getWorld(config.getString("lobbyLocation.world")),
                    config.getDouble("lobbyLocation.x"),
                    config.getDouble("lobbyLocation.y"),
                    config.getDouble("lobbyLocation.z")
            );
        } else {
            spawnLoc = new Location(
                    Bukkit.getWorld(config.getString("arenaLocation.world")),
                    config.getDouble("arenaLocation.x"),
                    config.getDouble("arenaLocation.y"),
                    config.getDouble("arenaLocation.z")
            );
        }

        event.setSpawnLocation(spawnLoc);
    }
}
