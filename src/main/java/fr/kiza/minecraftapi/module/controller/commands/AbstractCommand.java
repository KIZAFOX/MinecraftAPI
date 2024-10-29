package fr.kiza.minecraftapi.module.controller.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class AbstractCommand {

    public abstract boolean execute(final CommandSender sender, final Command command, final String label, final String[] args);

    public abstract List<String> tabComplete(final CommandSender sender, final Command command, final String label, final String[] args);

    public boolean hasPermission(final CommandSender sender, final String permission) {
        return sender.hasPermission(permission);
    }
}
