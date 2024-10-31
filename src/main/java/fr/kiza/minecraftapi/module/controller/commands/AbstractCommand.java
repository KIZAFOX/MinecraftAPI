package fr.kiza.minecraftapi.module.controller.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Abstract class representing a command that can be executed by a CommandSender.
 */
public abstract class AbstractCommand {

    /**
     * Executes the command.
     *
     * @param sender The sender of the command.
     * @param command The command object.
     * @param label The label of the command.
     * @param args The arguments passed to the command.
     * @return true if the command was successful, false otherwise.
     */
    public abstract boolean execute(final CommandSender sender, final Command command, final String label, final String[] args);

    /**
     * Tab completion for the command.
     *
     * @param sender The sender of the command.
     * @param command The command object.
     * @param label The label of the command.
     * @param args The arguments passed to the command.
     * @return A list of possible completions.
     */
    public abstract List<String> tabComplete(final CommandSender sender, final Command command, final String label, final String[] args);

    /**
     * Checks if the sender has the required permission for this command.
     *
     * @param sender The sender of the command.
     * @param permission The permission string to check.
     * @return true if the sender has the permission, false otherwise.
     */
    public boolean hasPermission(final CommandSender sender, final String permission) {
        return sender.hasPermission(permission);
    }

    /**
     * Sends a message to the command sender.
     *
     * @param sender The sender of the command.
     * @param message The message to send.
     */
    protected void sendMessage(CommandSender sender, String message) {
        if (sender != null) {
            sender.sendMessage(message);
        }
    }
}
