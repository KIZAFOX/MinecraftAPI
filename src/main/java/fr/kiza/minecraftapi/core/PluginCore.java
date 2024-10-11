package fr.kiza.minecraftapi.core;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginCore extends Core{
    public PluginCore(final JavaPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }
}
