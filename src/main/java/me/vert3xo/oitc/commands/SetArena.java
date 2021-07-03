package me.vert3xo.oitc.commands;

import me.vert3xo.oitc.OneInTheChamber;
import me.vert3xo.oitc.configuration.ConfigurationHelper;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetArena extends AbstractCommand {
    private final OneInTheChamber plugin = OneInTheChamber.getPlugin();
    private final ConfigurationHelper config = plugin.getConfigHelper();

    public SetArena(String command) {
        super(command);
    }

    public SetArena(String command, boolean canConsoleUse) {
        super(command, canConsoleUse);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Location pLoc = player.getLocation();

        config.set("arenaLocation.world", player.getWorld().getName());
        config.set("arenaLocation.x", (int) pLoc.getX() + 0.5);
        config.set("arenaLocation.y", (int) pLoc.getY());
        config.set("arenaLocation.z", (int) pLoc.getZ() + 0.5);
        player.sendMessage(ChatColor.GREEN + "Arena location set.");
        return true;
    }
}
