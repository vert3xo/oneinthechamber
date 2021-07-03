package me.vert3xo.oitc.commands;

import me.vert3xo.oitc.OneInTheChamber;
import me.vert3xo.oitc.game.Game;
import me.vert3xo.oitc.game.GameState;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class StartGame extends AbstractCommand {
    private final Game game = OneInTheChamber.getPlugin().getGame();

    public StartGame(String command) {
        super(command);
    }

    public StartGame(String command, boolean canConsoleUse) {
        super(command, canConsoleUse);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        GameState gameState = game.getGameState();
        if (gameState.equals(GameState.RUNNING)) {
            sender.sendMessage(ChatColor.RED + "The game already started!");
            return true;
        }

        game.setGameState(GameState.RUNNING);
        sender.sendMessage(ChatColor.GREEN + "Forcibly started the game.");
        return true;
    }
}
