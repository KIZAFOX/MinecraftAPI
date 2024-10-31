package fr.kiza.minecraftapi.module.player;

import org.bukkit.entity.Player;

/**
 * A functional interface representing an action that can be executed on a {@link Player}.
 * <p>
 * This interface allows you to define custom actions for player-related operations
 * in a concise manner, using lambda expressions or method references.
 * </p>
 */
@FunctionalInterface
public interface PlayerAction {

    /**
     * Executes the action for the given player.
     *
     * @param player the player on whom the action is executed
     */
    void execute(final Player player); // Method to define the action to be performed on the player.
}
