package fr.kiza.minecraftapi.init;

import fr.kiza.minecraftapi.core.Core;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class APIInitializer {
    public static void init(final JavaPlugin plugin) {
        final Logger logger = plugin.getLogger();

        if(plugin.getClass().isAnnotationPresent(MinecraftAPI.class)){
            logger.info("API detected on " + plugin.getClass().getSimpleName());
            Core.init(plugin);
        }else{
            logger.warning("Please put @MinecraftAPI in your main class!");
        }
    }
}
