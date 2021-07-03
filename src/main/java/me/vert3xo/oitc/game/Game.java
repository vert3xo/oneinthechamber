package me.vert3xo.oitc.game;

import lombok.Getter;
import lombok.Setter;
import me.vert3xo.oitc.OneInTheChamber;
import me.vert3xo.oitc.configuration.ConfigurationHelper;
import me.vert3xo.oitc.utils.ScoreHelper;
import me.vert3xo.oitc.utils.TimeUtil;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Game {
    private final OneInTheChamber plugin = OneInTheChamber.getPlugin();
    private final ConfigurationHelper configHelper = plugin.getConfigHelper();
    private final FileConfiguration config = configHelper.getConfig();

    @Getter
    @Setter
    private Map<Player, Integer> players = new HashMap<>();

    @Getter
    private GameState gameState = GameState.LOBBY;

    private Player winner = null;

    private int counter = 0;

    public Game() {
        Bukkit.getScheduler().runTaskTimer(plugin, this::run, 0, 20);
    }

    public void run() {
        switch (gameState) {
            case RUNNING -> {
                counter++;

                if (counter >= config.getInt("gameTime")) {
                    this.setGameState(GameState.END);
                }

                for (Player player : players.keySet()) {
                    ScoreHelper helper = ScoreHelper.getPlayers().get(player.getUniqueId());
                    int scoreboardLines = Math.min(players.size(), 5) + 3;

                    helper.setTitle("&b&aOne in the Chamber&r - " + TimeUtil.makeReadable(config.getInt("gameTime") - counter));
                    helper.setSlot(scoreboardLines--, "&7&m--------------------------------");
                    helper.setSlot(scoreboardLines--, "&aPlayer&f: " + player.getDisplayName());
                    helper.setSlot(scoreboardLines--, "&7&m--------------------------------");

                    int numToDisplay = scoreboardLines;
                    for (int i = 0; i < scoreboardLines; i++) {
                        helper.setSlot(numToDisplay--, "&c" + ((Player) players.keySet().toArray()[i]).getDisplayName() + ": " + players.values().toArray()[i]);
                    }
                }
            }
        }
    }

    public void setGameState(GameState gameState) {
        switch (gameState) {
            case LOBBY -> {
            }
            case RUNNING -> {
                counter = 0;
                Location arenaLoc = new Location(
                        Bukkit.getWorld(config.getString("arenaLocation.world")),
                        config.getDouble("arenaLocation.x"),
                        config.getDouble("arenaLocation.y"),
                        config.getDouble("arenaLocation.z")
                );

                for (Player p : Bukkit.getOnlinePlayers()) {
                    players.put(p, 0);
                    preparePlayer(p);
                    p.teleport(arenaLoc);
                    p.setBedSpawnLocation(arenaLoc, true);
                }
            }
            case END -> {
                Collection<Integer> scores = players.values();
                Iterator<Integer> it = scores.iterator();
                Player topPlayer = (Player) players.keySet().toArray()[0];

                winner = (it.hasNext() ? it.next() : null) == (it.hasNext() ? it.next() : null) ? null : topPlayer;

                if (winner != null) {
                    sendMessage(ChatColor.BOLD.toString() + ChatColor.GOLD + winner.getDisplayName() + ChatColor.RESET + ChatColor.GREEN + " has won the game!");
                } else {
                    sendMessage(ChatColor.RED + "It's a draw!");
                }

                Location endLoc = new Location(
                        Bukkit.getWorld(config.getString("lobbyLocation.world")),
                        config.getDouble("lobbyLocation.x"),
                        config.getDouble("lobbyLocation.y"),
                        config.getDouble("lobbyLocation.z")
                );

                for (Player p : players.keySet()) {
                    p.getInventory().clear();
                    p.teleport(endLoc);
                    ScoreHelper.setDefaultScore(p);
                }
            }
        }

        this.gameState = gameState;
    }

    public void sendMessage(Player player, String message) {
        player.sendMessage(message);
    }

    public void sendMessage(String message) {
        for (Player p : players.keySet()) {
            p.sendMessage(message);
        }
    }

    private void playSound(Player player, Sound sound) {
        player.playSound(player.getLocation(), sound, 1, 1);
    }

    private void playSound(Sound sound) {
        for (Player p : players.keySet()) {
            p.playSound(p.getLocation(), sound, 1, 1);
        }
    }

    public void preparePlayer(Player player) {
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        giveKit(player);
    }

    private void giveKit(Player player) {
        Inventory inventory = player.getInventory();

        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = sword.getItemMeta();
        meta.spigot().setUnbreakable(true);
        sword.setItemMeta(meta);

        ItemStack bow = new ItemStack(Material.BOW);
        bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        meta = bow.getItemMeta();
        meta.spigot().setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        bow.setItemMeta(meta);

        inventory.addItem(sword, bow, new ItemStack(Material.ARROW));
    }
}
