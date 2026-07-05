package org.mintype.rTPCore;

import org.bukkit.plugin.java.JavaPlugin;
import org.mintype.rTPCore.command.RTPCommand;
import org.mintype.rTPCore.command.RTPTabCompleter;

public final class RTPCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("rtp").setExecutor(new RTPCommand(this));
        getCommand("rtp").setTabCompleter(new RTPTabCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
