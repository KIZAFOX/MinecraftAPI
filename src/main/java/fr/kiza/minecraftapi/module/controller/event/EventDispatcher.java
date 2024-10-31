package fr.kiza.minecraftapi.module.controller.event;

import fr.kiza.minecraftapi.module.player.PlayerListener;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class EventDispatcher implements Listener {

    private final Object handler;

    /**
     * Constructs an EventDispatcher with the given handler.
     *
     * @param handler The object containing methods annotated with @PlayerListener.
     */
    public EventDispatcher(final Object handler) {
        this.handler = handler;
    }

    /**
     * Handles the given event by invoking the appropriate method in the handler.
     *
     * @param event The event to handle.
     */
    private void handleEvent(final Event event) {
        final Class<? extends Event> eventClass = event.getClass();

        // Iterate through the methods of the handler
        Arrays.stream(handler.getClass().getDeclaredMethods()).forEach(method -> {
            if (method.isAnnotationPresent(PlayerListener.class)) {
                final PlayerListener annotation = method.getAnnotation(PlayerListener.class);

                // Check if the method is annotated with PlayerListener and matches the event
                if (annotation.value().isAssignableFrom(eventClass)) {
                    try {
                        method.setAccessible(true); // Make the method accessible if it's private
                        method.invoke(handler, event); // Invoke the method with the event
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException("Failed to invoke event handler: " + method.getName(), e);
                    }
                }
            }
        });
    }

    /**
     * Registers a specific event with the given plugin.
     *
     * @param eventClass The class of the event to register.
     * @param plugin The plugin instance to register the event with.
     * @param <T> The type of the event.
     */
    public <T extends Event> void registerEvent(final Class<T> eventClass, final Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvent(
                eventClass,
                this,
                EventPriority.NORMAL,
                (_, event) -> this.handleEvent(event),
                plugin
        );
    }

    /**
     * Registers all events from the handler with the given plugin.
     *
     * @param plugin The plugin instance to register the events with.
     */
    public void registerEvent(final Plugin plugin) {
        Arrays.stream(handler.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(PlayerListener.class))
                .forEach(method -> {
                    final Class<? extends Event> eventClass = method.getAnnotation(PlayerListener.class).value();
                    this.registerEvent(eventClass, plugin); // Register each event
                });

        // Register the dispatcher itself
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
}
