package org.mintype.rTPCore.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.mintype.rTPCore.RTPCore;
import org.mintype.rTPCore.config.Settings;
import org.mintype.rTPCore.teleport.SafeLocationFinder;
import org.mintype.rTPCore.teleport.TeleportTask;

public class RTPManager {

    private final RTPCore plugin;
    private final Settings settings;
    private final SafeLocationFinder safeLocationFinder;
    private final CooldownManager cooldownManager;

    public RTPManager(RTPCore plugin) {
        this.plugin = plugin;

        this.settings = plugin.getSettings();
        this.safeLocationFinder = new SafeLocationFinder(settings);
        this.cooldownManager = plugin.getCooldownManager();
    }

    public void teleport(Player player) {

        if (cooldownManager.hasCooldown(player)) {
            player.sendMessage("§cYou must wait "
                    + cooldownManager.getRemaining(player)
                    + " seconds before using RTP again.");
            return;
        }

        World world = Bukkit.getWorld(settings.getWorldName());

        if (world == null) {
            player.sendMessage("§cConfigured world doesn't exist.");
            return;
        }

        Location location = safeLocationFinder.findSafeLocation(world, 100);

        if (location == null) {
            player.sendMessage("§cUnable to find a safe location.");
            return;
        }

        player.sendMessage(ChatColor.GREEN + "Teleporting...");

        cooldownManager.startCooldown(player);

        if (settings.getDelay() <= 0) {
            player.teleport(location);
            player.sendMessage("§aTeleported!");
            return;
        }

        new TeleportTask(
                plugin,
                player,
                location,
                settings.getDelay()
        ).start();
    }
}