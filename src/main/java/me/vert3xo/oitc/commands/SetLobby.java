package me.vert3xo.oitc.commands;

import me.vert3xo.oitc.OneInTheChamber;
import me.vert3xo.oitc.configuration.ConfigurationHelper;
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

        config.set("lobbyLocation.world", world.getName());
        config.set("lobbyLocation.x", x + 0.5);
        config.set("lobbyLocation.y", y);
        config.set("lobbyLocation.z", z + 0.5);
        player.sendMessage(ChatColor.GREEN + "Lobby location set.");
        return true;
    }
}
