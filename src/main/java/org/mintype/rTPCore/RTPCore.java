package org.mintype.rTPCore;

import org.bukkit.plugin.java.JavaPlugin;
import org.mintype.rTPCore.command.RTPCommand;
import org.mintype.rTPCore.command.RTPTabCompleter;
import org.mintype.rTPCore.config.Settings;
import org.mintype.rTPCore.manager.CooldownManager;
import org.mintype.rTPCore.manager.RTPManager;

public final class RTPCore extends JavaPlugin {

    private RTPManager rtpManager;
    private Settings settings;
    private CooldownManager cooldownManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        saveDefaultConfig();

        this.settings = new Settings(this);
        this.cooldownManager = new CooldownManager(settings);
        this.rtpManager = new RTPManager(this);

        getCommand("rtp").setExecutor(new RTPCommand(this));
        getCommand("rtp").setTabCompleter(new RTPTabCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public RTPManager getRtpManager() {
        return rtpManager;
    }
    public Settings getSettings() {
        return settings;
    }
    public CooldownManager getCooldownManager() {
        return cooldownManager;
    }
}
