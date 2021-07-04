package me.vert3xo.oitc;

import lombok.Getter;
import me.vert3xo.oitc.commands.*;
import me.vert3xo.oitc.utils.ConfigurationHelper;
import me.vert3xo.oitc.game.Game;
import me.vert3xo.oitc.listeners.PlayerDamageListener;
import me.vert3xo.oitc.listeners.PlayerLeaveListener;
import me.vert3xo.oitc.listeners.PlayerSpawnListener;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class OneInTheChamber extends JavaPlugin {
    @Getter
    private static OneInTheChamber plugin;

    private final ConsoleCommandSender sender = getServer().getConsoleSender();

    @Getter
    private ConfigurationHelper configHelper;

    @Getter
    private Game game;

    @Override
    public void onEnable() {
        plugin = this;

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        configHelper = new ConfigurationHelper(getConfig());

        game = new Game();

        this.registerEvents();
        this.registerCommands();

        sender.sendMessage(ChatColor.GREEN + "One in the Chamber enabled!");
    }

    @Override
    public void onDisable() {
        sender.sendMessage(ChatColor.RED + "One in the Chamber disabled!");
    }

    private void registerEvents() {
        PluginManager manager = getServer().getPluginManager();

        manager.registerEvents(new PlayerSpawnListener(), this);
        manager.registerEvents(new PlayerLeaveListener(), this);
        manager.registerEvents(new PlayerDamageListener(), this);
    }

    private void registerCommands() {
        new SetLobby("setlobby");
        new SetArena("setarena");
        new SetEnd("setend");

        new StartGame("startgame", true);
        new EndGame("endgame", true);
        new GameStatus("gamestatus", true);
    }
}
