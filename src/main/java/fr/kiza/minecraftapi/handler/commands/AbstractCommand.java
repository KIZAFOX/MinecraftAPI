package fr.kiza.minecraftapi.handler.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class AbstractCommand {

    public abstract boolean execute(final CommandSender sender, final Command command, final String label, final String[] args);

    public boolean hasPermission(final CommandSender sender, final String permission) {
        return sender.hasPermission(permission);
    }
}
