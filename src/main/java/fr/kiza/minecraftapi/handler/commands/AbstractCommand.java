package fr.kiza.minecraftapi.handler.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractCommand {

    public abstract boolean execute(final CommandSender sender, final Command command, final String label, final String[] args);

    public void playerOnly(final CommandSender sender) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by a player");
        }
    }

    public boolean hasPermission(final CommandSender sender, final String permission) {
        return sender.hasPermission(permission);
    }
}
