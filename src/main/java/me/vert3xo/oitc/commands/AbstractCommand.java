package me.vert3xo.oitc.commands;

import me.vert3xo.oitc.OneInTheChamber;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractCommand implements CommandExecutor {
    private final String command;
    private boolean canConsoleUse = false;

    public AbstractCommand(String command) {
        this.command = command;
        setExecutor();
    }

    public AbstractCommand(String command, boolean canConsoleUse) {
        this.command = command;
        this.canConsoleUse = canConsoleUse;
        setExecutor();
    }

    public abstract boolean execute(CommandSender sender, String[] args);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String alias, String[] args) {
        if (!(commandSender instanceof Player) && !canConsoleUse) {
            commandSender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }
        return execute(commandSender, args);
    }

    private void setExecutor() {
        OneInTheChamber.getPlugin().getCommand(command).setExecutor(this);
    }
}
