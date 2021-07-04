package me.vert3xo.oitc.utils;

import lombok.Getter;
import me.vert3xo.oitc.OneInTheChamber;
import org.bukkit.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;

public class ConfigurationHelper {
    private final OneInTheChamber plugin = OneInTheChamber.getPlugin();

    @Getter
    private final FileConfiguration config;

    private String path = null;

    public ConfigurationHelper(FileConfiguration config) {
        this.config = config;
    }

    private ConfigurationHelper(FileConfiguration config, String path) {
        this.config = config;
        this.path = path;
    }

    public ConfigurationHelper getForPath(String path) {
        return new ConfigurationHelper(config, path);
    }

    public <T> void set(String path, T value) {
        config.set(this.path != null ? (this.path + "." + path) : path, value);
        plugin.saveConfig();
    }

    public Object get(String path) {
        return config.get(this.path != null ? (this.path + "." + path) : path);
    }

    public String getString(String path) {
        return config.getString(this.path != null ? (this.path + "." + path) : path);
    }

    public int getInt(String path) {
        return config.getInt(this.path != null ? (this.path + "." + path) : path);
    }

    public boolean getBoolean(String path) {
        return config.getBoolean(this.path != null ? (this.path + "." + path) : path);
    }

    public double getDouble(String path) {
        return config.getDouble(this.path != null ? (this.path + "." + path) : path);
    }

    public Color getColor(String path) {
        return config.getColor(this.path != null ? (this.path + "." + path) : path);
    }

    public ItemStack getItemStack(String path) {
        return config.getItemStack(this.path != null ? (this.path + "." + path) : path);
    }

    public long getLong(String path) {
        return config.getLong(this.path != null ? (this.path + "." + path) : path);
    }

    public Vector getVector(String path) {
        return config.getVector(this.path != null ? (this.path + "." + path) : path);
    }

    public List<?> getList(String path) {
        return config.getList(this.path != null ? (this.path + "." + path) : path);
    }

}
