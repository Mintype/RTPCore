package org.mintype.rTPCore.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mintype.rTPCore.RTPCore;
import org.mintype.rTPCore.config.Settings;

public class RTPCommand implements CommandExecutor {

    private final RTPCore plugin;
    private final Settings settings;

    public RTPCommand(RTPCore plugin) {
        this.plugin = plugin;
        this.settings = plugin.getSettings();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // /rtp
        if (args.length == 0) {

            if (!(sender instanceof Player player)) {
                sender.sendMessage(ChatColor.RED + "Only players can use this command.");
                return true;
            }

            plugin.getRtpManager().teleport(player);

            player.sendMessage(ChatColor.GREEN + "Teleporting...");
            return true;
        }

        switch (args[0].toLowerCase()) {

            case "help" -> sendHelp(sender);

            case "reload" -> {

                if (!sender.hasPermission("rtpcore.reload")) {
                    sender.sendMessage(ChatColor.RED + "No permission.");
                    return true;
                }

                settings.reload();

                sender.sendMessage(ChatColor.GREEN + "RTPCore reloaded.");
            }

            case "info" -> {

                sender.sendMessage(ChatColor.GOLD + "----- RTPCore -----");
                sender.sendMessage(ChatColor.YELLOW + "Radius: " + settings.getRadius());
                sender.sendMessage(ChatColor.YELLOW + "Min Radius: " + settings.getMinRadius());
                sender.sendMessage(ChatColor.YELLOW + "Cooldown: " + settings.getCooldown());
                sender.sendMessage(ChatColor.YELLOW + "Delay: " + settings.getDelay());
                sender.sendMessage(ChatColor.YELLOW + "World: " + settings.getWorldName());
            }

            case "set" -> {

                if (!sender.hasPermission("rtpcore.admin")) {
                    sender.sendMessage(ChatColor.RED + "No permission.");
                    return true;
                }

                if (args.length < 3) {
                    sender.sendMessage(ChatColor.RED + "Usage: /rtp set <radius|cooldown|delay|world|minradius> <value>");
                    return true;
                }

                String setting = args[1].toLowerCase();
                String value = args[2];

                try {
                    switch (setting) {

                        case "radius" -> {
                            settings.setRadius(Integer.parseInt(value));
                        }

                        case "minradius" -> {
                            settings.setMinRadius(Integer.parseInt(value));
                        }

                        case "cooldown" -> {
                            settings.setCooldown(Integer.parseInt(value));
                        }

                        case "delay" -> {
                            settings.setDelay(Integer.parseInt(value));
                        }

                        case "world" -> {
                            settings.setWorldName(value);
                        }

                        default -> {
                            sender.sendMessage(ChatColor.RED + "Unknown setting.");
                            return true;
                        }
                    }

                    sender.sendMessage(ChatColor.GREEN + "Updated " + setting + ".");

                } catch (NumberFormatException e) {
                    sender.sendMessage(ChatColor.RED + "Invalid number format.");
                }
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
        sender.sendMessage(ChatColor.YELLOW + "/rtp set minradius <value>");
        sender.sendMessage(ChatColor.YELLOW + "/rtp set cooldown <value>");
        sender.sendMessage(ChatColor.YELLOW + "/rtp set delay <value>");
        sender.sendMessage(ChatColor.YELLOW + "/rtp set world <world>");
    }
}