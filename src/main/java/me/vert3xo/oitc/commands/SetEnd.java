package me.vert3xo.oitc.commands;

import me.vert3xo.oitc.OneInTheChamber;
import me.vert3xo.oitc.utils.ConfigurationHelper;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetEnd extends AbstractCommand {
    private OneInTheChamber plugin = OneInTheChamber.getPlugin();

    public SetEnd(String command) {
        super(command);
    }

    public SetEnd(String command, boolean canConsoleUse) {
        super(command, canConsoleUse);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Location pLoc = player.getLocation();

        World world = player.getWorld();
        int x = (int) pLoc.getX();
        int y = (int) pLoc.getY();
        int z = (int) pLoc.getZ();

        world.setSpawnLocation(x, y, z);

        ConfigurationHelper endLocationHelper = plugin.getConfigHelper().getForPath("endLocation");
        endLocationHelper.set("world", world.getName());
        endLocationHelper.set("x", x + 0.5);
        endLocationHelper.set("y", y);
        endLocationHelper.set("z", z + 0.5);
        player.sendMessage(ChatColor.GREEN + "End location set.");
        return true;
    }
}
