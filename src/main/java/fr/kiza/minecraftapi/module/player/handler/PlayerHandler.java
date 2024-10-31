package fr.kiza.minecraftapi.module.player.handler;

import fr.kiza.minecraftapi.core.Core;
import fr.kiza.minecraftapi.module.player.PlayerAction;
import fr.kiza.minecraftapi.module.player.PlayerListener;
import fr.kiza.minecraftapi.module.player.data.PlayerData;
import fr.kiza.minecraftapi.module.tools.logger.Logger;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * Handles player-related actions and events in the Minecraft API.
 * <p>
 * This class is responsible for executing actions on players when specific events occur,
 * as well as checking for the presence of the {@link PlayerListener} annotation on methods.
 * </p>
 */
public class PlayerHandler {
    /**
     * Performs the specified action for the player when the given event occurs.
     *
     * @param action the action to be performed on the player
     * @param event  the event that triggers the action
     */
    public void performAction(final PlayerAction action, final Event event) {
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        try {
            // Get the calling class and method from the stack trace
            final String callingClassName = stackTrace[2].getClassName();
            final String callingMethodName = stackTrace[2].getMethodName();
            final Class<?> callingClass = Class.forName(callingClassName);
            final Method callingMethod = callingClass.getMethod(callingMethodName, event.getClass());

            // Check if the method is annotated with @PlayerListener
            if (callingMethod.isAnnotationPresent(PlayerListener.class)) {
                // Retrieve the player instance
                final Optional<Player> optionalPlayer = Optional.ofNullable(Core.getInstance().getPlayer());

                // Execute the action if the player is present
                optionalPlayer.ifPresent(action::execute);
            } else {
                // Log an error if the annotation is missing
                Logger.print("The method " + callingMethodName + " does not have the @PlayerListener annotation.", Logger.LoggerLevel.ERROR);
            }
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            // Handle exceptions that occur during reflection
            throw new RuntimeException("An error occurred while trying to perform the action: " + e.getMessage(), e);
        }
    }
}
