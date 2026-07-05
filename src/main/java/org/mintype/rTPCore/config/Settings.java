package org.mintype.rTPCore.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.mintype.rTPCore.RTPCore;

public class Settings {

    private final RTPCore plugin;
    private FileConfiguration config;

    public Settings(RTPCore plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {
        plugin.reloadConfig();
        this.config = plugin.getConfig();

        // optional defaults safety
        config.addDefault("radius", 1000);
        config.addDefault("min-radius", 100);
        config.addDefault("cooldown", 60);
        config.addDefault("delay", 3);
        config.addDefault("world", "world");
        config.options().copyDefaults(true);

        plugin.saveConfig();
    }

    /* ---------------- GETTERS ---------------- */

    public int getRadius() {
        return config.getInt("radius");
    }

    public int getMinRadius() {
        return config.getInt("min-radius");
    }

    public int getCooldown() {
        return config.getInt("cooldown");
    }

    public int getDelay() {
        return config.getInt("delay");
    }

    public String getWorldName() {
        return config.getString("world", "world");
    }

    /* ---------------- SETTERS ---------------- */

    public void setRadius(int value) {
        config.set("radius", clamp(value, 0, Integer.MAX_VALUE));
        save();
    }

    public void setMinRadius(int value) {
        config.set("min-radius", clamp(value, 0, getRadius()));
        save();
    }

    public void setCooldown(int value) {
        config.set("cooldown", clamp(value, 0, 100000));
        save();
    }

    public void setDelay(int value) {
        config.set("delay", clamp(value, 0, 100000));
        save();
    }

    public void setWorldName(String world) {
        config.set("world", world);
        save();
    }

    /* ---------------- UTIL ---------------- */

    private void save() {
        plugin.saveConfig();
    }

    private int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}