package fr.kiza.minecraftapi.core;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * PluginCore is the main class that initializes the Minecraft API plugin.
 * It extends the Core class, which handles the core functionalities of the API.
 */
public class PluginCore extends Core {

    /**
     * Constructs a new instance of PluginCore.
     *
     * @param plugin The JavaPlugin instance associated with this plugin.
     * This constructor calls the superclass constructor to initialize
     * the core functionalities of the API.
     */
    protected PluginCore(JavaPlugin plugin) {
        super(plugin);
    }
}
