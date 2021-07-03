package me.vert3xo.oitc.listeners;

import me.vert3xo.oitc.utils.ScoreHelper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        ScoreHelper.removeScore(event.getPlayer());
    }
}
