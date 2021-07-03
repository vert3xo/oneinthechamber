package me.vert3xo.oitc.commands;

import me.vert3xo.oitc.OneInTheChamber;
import me.vert3xo.oitc.game.Game;
import me.vert3xo.oitc.game.GameState;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class EndGame extends AbstractCommand {
    private final Game game = OneInTheChamber.getPlugin().getGame();

    public EndGame(String command) {
        super(command);
    }

    public EndGame(String command, boolean canConsoleUse) {
        super(command, canConsoleUse);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        GameState gameState = game.getGameState();
        if (!gameState.equals(GameState.RUNNING)) {
            sender.sendMessage(ChatColor.RED + "The game is not running!");
            return true;
        }

        game.setGameState(GameState.END);
        sender.sendMessage(ChatColor.GREEN + "Forcibly stopped the game.");
        return true;
    }
}
