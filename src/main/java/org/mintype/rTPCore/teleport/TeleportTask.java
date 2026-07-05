package org.mintype.rTPCore.teleport;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.mintype.rTPCore.RTPCore;

public class TeleportTask extends BukkitRunnable {

    private final RTPCore plugin;
    private final Player player;
    private final Location destination;

    private int seconds;

    public TeleportTask(RTPCore plugin, Player player, Location destination, int delay) {
        this.plugin = plugin;
        this.player = player;
        this.destination = destination;
        this.seconds = delay;
    }

    @Override
    public void run() {

        if (!player.isOnline()) {
            cancel();
            return;
        }

        if (seconds <= 0) {

            player.teleport(destination);
            player.sendMessage(ChatColor.GREEN + "Teleported!");

            cancel();
            return;
        }

        player.sendMessage(ChatColor.YELLOW + "Teleporting in " + seconds + "...");

        seconds--;
    }

    public void start() {
        runTaskTimer(plugin, 0L, 20L);
    }
}