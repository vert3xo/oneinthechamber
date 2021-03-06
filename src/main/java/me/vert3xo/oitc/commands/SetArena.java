package me.vert3xo.oitc.commands;

import me.vert3xo.oitc.OneInTheChamber;
import me.vert3xo.oitc.utils.ConfigurationHelper;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
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

        World world = player.getWorld();
        int x = (int) pLoc.getX();
        int y = (int) pLoc.getY();
        int z = (int) pLoc.getZ();

        world.setSpawnLocation(x, y, z);

        ConfigurationHelper arenaLocationHelper = plugin.getConfigHelper().getForPath("arenaLocation");
        arenaLocationHelper.set("world", world.getName());
        arenaLocationHelper.set("x", x + 0.5);
        arenaLocationHelper.set("y", y);
        arenaLocationHelper.set("z", z + 0.5);
        player.sendMessage(ChatColor.GREEN + "Arena location set.");
        return true;
    }
}
