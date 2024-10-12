package fr.kiza.minecraftapi.init;

import fr.kiza.minecraftapi.core.Core;
import fr.kiza.minecraftapi.handler.player.PlayerListener;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.logging.Logger;

public class APIInitializer {
    public static void init(final JavaPlugin plugin) {
        final Logger logger = plugin.getLogger();

        if(plugin.getClass().isAnnotationPresent(MinecraftAPI.class)){
            Core.init(plugin);

            Arrays.stream(plugin.getClass().getDeclaredMethods())
                    .filter(methods -> methods.isAnnotationPresent(PlayerListener.class))
                    .forEach(methods -> {
                        Core.getInstance().getEventDispatcher().registerEvent(plugin);
                        plugin.getServer().getPluginManager().registerEvents(Core.getInstance().getEventDispatcher(), plugin);
                    });

            logger.info("API detected on " + plugin.getClass().getSimpleName());
        }else{
            logger.warning("Please put @MinecraftAPI in your main class!");
        }
    }
}
