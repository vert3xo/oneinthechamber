package me.vert3xo.oitc.listeners;

import me.vert3xo.oitc.OneInTheChamber;
import me.vert3xo.oitc.game.Game;
import me.vert3xo.oitc.game.GameState;
import me.vert3xo.oitc.utils.MapUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Map;

public class PlayerDamageListener implements Listener {
    private final OneInTheChamber plugin = OneInTheChamber.getPlugin();
    private final Game game = plugin.getGame();

    private String generateDeathMessage(Player killer, Player killed) {
        return
                ChatColor.GOLD + ChatColor.BOLD.toString() +
                        killed.getDisplayName() +
                        ChatColor.RESET + ChatColor.GREEN +
                        " was killed by " +
                        ChatColor.GOLD + ChatColor.BOLD +
                        killer.getDisplayName();
    }

    @EventHandler
    public void onPlayerShot(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Projectile projetile) || !(event.getEntity() instanceof Player damaged)) return;
        if (!(projetile.getShooter() instanceof Player damager)) return;

        Map<Player, Integer> players = game.getPlayers();
        players.put(damager, players.get(damager) + 1);

        damaged.setHealth(20);
        damaged.spigot().respawn();

        game.sendMessage(generateDeathMessage(damager, damaged));

        game.setPlayers(MapUtils.sortMapByValue(game.getPlayers()));
    }

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = player.getKiller();
        if (killer == null) return;
        event.setKeepInventory(true);

        Map<Player, Integer> players = game.getPlayers();
        players.put(killer, players.get(killer) + 1);

        player.spigot().respawn();

        event.setDeathMessage(generateDeathMessage(killer, player));

        game.setPlayers(MapUtils.sortMapByValue(game.getPlayers()));
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (
                event.getEntity() instanceof Player &&
                event.getDamager() instanceof Player &&
                game.getGameState() != GameState.RUNNING
        )
            event.setCancelled(true);
    }
}
