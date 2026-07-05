package org.mintype.rTPCore.manager;

import org.bukkit.entity.Player;
import org.mintype.rTPCore.config.Settings;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private final Settings settings;

    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public CooldownManager(Settings settings) {
        this.settings = settings;
    }

    // Returns true if the player is still on cooldown.
    public boolean hasCooldown(Player player) {

        if (player.hasPermission("rtpcore.bypass.cooldown")) {
            return false;
        }

        Long endTime = cooldowns.get(player.getUniqueId());

        if (endTime == null) {
            return false;
        }

        if (System.currentTimeMillis() >= endTime) {
            cooldowns.remove(player.getUniqueId());
            return false;
        }

        return true;
    }

    // Starts the cooldown.
    public void startCooldown(Player player) {

        long endTime = System.currentTimeMillis()
                + (settings.getCooldown() * 1000L);

        cooldowns.put(player.getUniqueId(), endTime);
    }

    // Returns remaining cooldown in seconds.
    public long getRemaining(Player player) {

        Long endTime = cooldowns.get(player.getUniqueId());

        if (endTime == null) {
            return 0;
        }

        return Math.max(0,
                (endTime - System.currentTimeMillis()) / 1000);
    }

    // Removes a player's cooldown.
    public void clearCooldown(Player player) {
        cooldowns.remove(player.getUniqueId());
    }

    // Clears every cooldown.
    public void clearAll() {
        cooldowns.clear();
    }
}