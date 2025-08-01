package com.yourname.fling;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.Random;

public class FlingPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("FlingPlugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("FlingPlugin disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("fling")) {
            if (!sender.hasPermission("fling.use")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
                return true;
            }

            if (args.length != 1) {
                sender.sendMessage(ChatColor.YELLOW + "Usage: /fling <player>");
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Player not found!");
                return true;
            }

            flingPlayer(target);
            sender.sendMessage(ChatColor.GREEN + "You flung " + target.getName() + " into the air!");
            return true;
        }
        return false;
    }

    private void flingPlayer(Player player) {
        Random random = new Random();
        double x = (random.nextDouble() * 2 - 1) * 2;
        double z = (random.nextDouble() * 2 - 1) * 2;
        double y = 2 + random.nextDouble() * 2;
        Vector velocity = new Vector(x, y, z);
        player.setVelocity(velocity);
    }
}
