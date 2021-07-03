package me.vert3xo.oitc.commands;

import me.vert3xo.oitc.OneInTheChamber;
import me.vert3xo.oitc.game.Game;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class GameStatus extends AbstractCommand {
    private final Game game = OneInTheChamber.getPlugin().getGame();

    public GameStatus(String command) {
        super(command);
    }

    public GameStatus(String command, boolean canConsoleUse) {
        super(command, canConsoleUse);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        sender.sendMessage(ChatColor.GREEN + "Current game status is: " + ChatColor.BOLD + ChatColor.GOLD + game.getGameState().toString());
        return true;
    }
}
