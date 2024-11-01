package fr.kiza.minecraftapi.init;

import fr.kiza.minecraftapi.core.Core;
import fr.kiza.minecraftapi.module.controller.event.APIListener;
import fr.kiza.minecraftapi.module.controller.event.EventDispatcher;
import fr.kiza.minecraftapi.module.tools.logger.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class APIInitializer {

    /**
     * Initializes the API for the given plugin.
     *
     * @param plugin The instance of the JavaPlugin to initialize the API for.
     */
    public static void init(final JavaPlugin plugin) {
        if (!plugin.getClass().isAnnotationPresent(MinecraftAPI.class)) {
            plugin.getServer().getLogger().severe("Please read the doc on the GitHub Repository.");
        } else {
            Core.init(plugin);

            Logger.print("API detected on " + plugin.getClass().getSimpleName(), Logger.LoggerLevel.INFO);

            final Reflections reflections = new Reflections(plugin.getClass().getPackage().getName());
            final Set<Class<?>> listenerClasses = reflections.getTypesAnnotatedWith(APIListener.class);

            listenerClasses.forEach(listenerClass -> {
                try {
                    final Object listenerInstance = listenerClass.getDeclaredConstructor().newInstance();
                    final EventDispatcher dispatcher = new EventDispatcher(listenerInstance);

                    dispatcher.registerEvent(plugin);

                    Logger.print("Listener registered successfully: " + listenerClass.getName(), Logger.LoggerLevel.DEBUG);
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    plugin.getServer().getLogger().severe("Failed to register listener: " + listenerClass.getName());
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
