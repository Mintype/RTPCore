package org.mintype.rTPCore.teleport;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.mintype.rTPCore.config.Settings;

import java.util.concurrent.ThreadLocalRandom;

public class SafeLocationFinder {

    private final Settings settings;

    public SafeLocationFinder(Settings settings) {
        this.settings = settings;
    }

    public Location findSafeLocation(World world, int maxAttempts) {

        int radius = settings.getRadius();
        int minRadius = settings.getMinRadius();

        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < maxAttempts; i++) {

            int x = randomCoordinate(random, minRadius, radius);
            int z = randomCoordinate(random, minRadius, radius);

            if (random.nextBoolean()) x *= -1;
            if (random.nextBoolean()) z *= -1;

            int y = world.getHighestBlockYAt(x, z);

            Location location = new Location(world, x + 0.5, y + 1, z + 0.5);

            if (isSafe(location)) {
                return location;
            }
        }

        return null;
    }

    private boolean isSafe(Location location) {

        Material feet = location.getBlock().getType();
        Material head = location.clone().add(0, 1, 0).getBlock().getType();
        Material ground = location.clone().subtract(0, 1, 0).getBlock().getType();

        return feet.isAir()
                && head.isAir()
                && ground.isSolid()
                && !ground.name().contains("LAVA")
                && !ground.name().contains("WATER")
                && !ground.name().contains("FIRE")
                && !ground.name().contains("CACTUS");
    }

    private int randomCoordinate(ThreadLocalRandom random, int min, int max) {
        return random.nextInt(min, max + 1);
    }
}