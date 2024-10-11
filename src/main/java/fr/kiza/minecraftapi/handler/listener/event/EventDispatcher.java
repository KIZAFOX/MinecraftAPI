package fr.kiza.minecraftapi.handler.listener.event;

import fr.kiza.minecraftapi.handler.player.PlayerListener;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class EventDispatcher implements Listener {

    private final Object handler;

    public EventDispatcher(Object handler) {
        this.handler = handler;
    }

    private void handleEvent(final Event event) {
        final Class<? extends Event> eventClass = event.getClass();

        Arrays.stream(handler.getClass().getDeclaredMethods()).forEach(methods -> {
            if(methods.isAnnotationPresent(PlayerListener.class)){
                final PlayerListener annotation = methods.getAnnotation(PlayerListener.class);

                if(annotation.value().isAssignableFrom(eventClass)){
                    try {
                        methods.setAccessible(true);
                        methods.invoke(handler, event);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public <T extends Event> void registerEvent(Class<T> eventClass, final Plugin plugin){
        plugin.getServer().getPluginManager().registerEvent(
                eventClass,
                this,
                EventPriority.NORMAL,
                (listener, event) -> this.handleEvent(event),
                plugin
        );
    }

    public void registerEvent(final Plugin plugin){
        Arrays.stream(handler.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(PlayerListener.class))
                .forEach(methods -> {
                    final Class<? extends Event> eventClass = methods.getAnnotation(PlayerListener.class).value();
                    this.registerEvent(eventClass, plugin);
                });
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
}
