package fr.kiza.minecraftapi.init;

import fr.kiza.minecraftapi.core.Core;
import fr.kiza.minecraftapi.module.player.PlayerListener;
import fr.kiza.minecraftapi.module.tools.logger.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class APIInitializer {
    public static void init(final JavaPlugin plugin) {
        if(!plugin.getClass().isAnnotationPresent(MinecraftAPI.class)){
            Logger.print("Please read the doc on the GitHub Repository.", Logger.LoggerLevel.ERROR);
        }else{
            Logger.print("API detected on " + plugin.getClass().getSimpleName(), Logger.LoggerLevel.INFO);

            Core.init(plugin);

            Arrays.stream(plugin.getClass().getDeclaredMethods())
                    .filter(methods -> methods.isAnnotationPresent(PlayerListener.class))
                    .forEach(_ -> {
                        Core.getInstance().getEventDispatcher().registerEvent(plugin);
                        plugin.getServer().getPluginManager().registerEvents(Core.getInstance().getEventDispatcher(), plugin);
                    });

            Logger.print("API successfully initialized on " + plugin.getClass().getSimpleName(), Logger.LoggerLevel.INFO);
        }
    }
}
