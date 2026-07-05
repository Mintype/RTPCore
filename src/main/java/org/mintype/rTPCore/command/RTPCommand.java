package org.mintype.rTPCore.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mintype.rTPCore.RTPCore;

public class RTPCommand implements CommandExecutor {

    private final RTPCore plugin;

    public RTPCommand(RTPCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // /rtp
        if (args.length == 0) {
            if (!(sender instanceof Player player)) {
                sender.sendMessage(ChatColor.RED + "Only players can use this command.");
                return true;
            }

            // TODO
            // plugin.getRtpManager().teleport(player);

            player.sendMessage(ChatColor.GREEN + "Teleporting...");
            return true;
        }

        switch (args[0].toLowerCase()) {

            case "help" -> {
                sendHelp(sender);
            }

            case "reload" -> {

                if (!sender.hasPermission("rtpcore.reload")) {
                    sender.sendMessage(ChatColor.RED + "No permission.");
                    return true;
                }

                plugin.reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "RTPCore reloaded.");
            }

            case "info" -> {

                sender.sendMessage(ChatColor.GOLD + "----- RTPCore -----");
                sender.sendMessage(ChatColor.YELLOW + "Radius: "
                        + plugin.getConfig().getInt("radius"));

                sender.sendMessage(ChatColor.YELLOW + "Cooldown: "
                        + plugin.getConfig().getInt("cooldown"));

                sender.sendMessage(ChatColor.YELLOW + "Delay: "
                        + plugin.getConfig().getInt("delay"));

                sender.sendMessage(ChatColor.YELLOW + "World: "
                        + plugin.getConfig().getString("world"));
            }

            case "set" -> {

                if (!sender.hasPermission("rtpcore.admin")) {
                    sender.sendMessage(ChatColor.RED + "No permission.");
                    return true;
                }

                if (args.length < 3) {
                    sender.sendMessage(ChatColor.RED + "Usage: /rtp set <setting> <value>");
                    return true;
                }

                String setting = args[1].toLowerCase();
                String value = args[2];

                switch (setting) {

                    case "radius" -> {
                        plugin.getConfig().set("radius", Integer.parseInt(value));
                    }

                    case "cooldown" -> {
                        plugin.getConfig().set("cooldown", Integer.parseInt(value));
                    }

                    case "delay" -> {
                        plugin.getConfig().set("delay", Integer.parseInt(value));
                    }

                    case "world" -> {
                        plugin.getConfig().set("world", value);
                    }

                    default -> {
                        sender.sendMessage(ChatColor.RED + "Unknown setting.");
                        return true;
                    }
                }

                plugin.saveConfig();

                sender.sendMessage(ChatColor.GREEN + "Updated " + setting + ".");
            }

            default -> sender.sendMessage(ChatColor.RED + "Unknown subcommand. Use /rtp help.");
        }

        return true;
    }

    private void sendHelp(CommandSender sender) {

        sender.sendMessage(ChatColor.GOLD + "===== RTPCore Help =====");
        sender.sendMessage(ChatColor.YELLOW + "/rtp");
        sender.sendMessage(ChatColor.YELLOW + "/rtp help");
        sender.sendMessage(ChatColor.YELLOW + "/rtp reload");
        sender.sendMessage(ChatColor.YELLOW + "/rtp info");
        sender.sendMessage(ChatColor.YELLOW + "/rtp set radius <value>");
        sender.sendMessage(ChatColor.YELLOW + "/rtp set cooldown <seconds>");
        sender.sendMessage(ChatColor.YELLOW + "/rtp set delay <seconds>");
        sender.sendMessage(ChatColor.YELLOW + "/rtp set world <world>");
    }

}