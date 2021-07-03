package me.vert3xo.oitc.configuration;

import lombok.Getter;
import me.vert3xo.oitc.OneInTheChamber;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigurationHelper {
    private final OneInTheChamber plugin = OneInTheChamber.getPlugin();

    @Getter
    private final FileConfiguration config;

    public ConfigurationHelper(FileConfiguration config) {
        this.config = config;
    }

    public <T> void set(String path, T value) {
        config.set(path, value);
        plugin.saveConfig();
    }
}
