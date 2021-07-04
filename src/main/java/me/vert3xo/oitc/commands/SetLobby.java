package me.vert3xo.oitc.commands;

import me.vert3xo.oitc.OneInTheChamber;
import me.vert3xo.oitc.utils.ConfigurationHelper;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLobby extends AbstractCommand {
    private final OneInTheChamber plugin = OneInTheChamber.getPlugin();
    private final ConfigurationHelper config = plugin.getConfigHelper();

    public SetLobby(String command) {
        super(command);
    }

    public SetLobby(String command, boolean canConsoleUse) {
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

        ConfigurationHelper lobbyLocationHelper = plugin.getConfigHelper().getForPath("lobbyLocation");
        lobbyLocationHelper.set("world", world.getName());
        lobbyLocationHelper.set("x", x + 0.5);
        lobbyLocationHelper.set("y", y);
        lobbyLocationHelper.set("z", z + 0.5);
        player.sendMessage(ChatColor.GREEN + "Lobby location set.");
        return true;
    }
}
