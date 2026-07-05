package org.mintype.rTPCore.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class RTPTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender,
                                      Command command,
                                      String alias,
                                      String[] args) {

        List<String> completions = new ArrayList<>();

        if (args.length == 1) {

            completions.add("help");
            completions.add("reload");
            completions.add("info");
            completions.add("set");

            return filter(completions, args[0]);
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("set")) {

            completions.add("radius");
            completions.add("cooldown");
            completions.add("delay");
            completions.add("world");

            return filter(completions, args[1]);
        }

        if (args.length == 3 &&
                args[0].equalsIgnoreCase("set") &&
                args[1].equalsIgnoreCase("world")) {

            Bukkit.getWorlds().forEach(world ->
                    completions.add(world.getName()));

            return filter(completions, args[2]);
        }

        return List.of();
    }

    private List<String> filter(List<String> list, String input) {

        return list.stream()
                .filter(s -> s.toLowerCase().startsWith(input.toLowerCase()))
                .toList();
    }

}