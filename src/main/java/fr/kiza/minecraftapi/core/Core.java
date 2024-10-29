package fr.kiza.minecraftapi.core;

import fr.kiza.minecraftapi.module.controller.event.EventListener;
import fr.kiza.minecraftapi.module.controller.event.EventDispatcher;
import fr.kiza.minecraftapi.module.database.Database;
import fr.kiza.minecraftapi.module.player.handler.PlayerHandler;
import fr.kiza.minecraftapi.module.tools.color.ConsoleColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

public abstract class Core {

    private static Core instance;

    public JavaPlugin plugin;
    public Logger logger;

    private PlayerHandler playerHandler;
    private EventDispatcher eventDispatcher;

    public static void init(final JavaPlugin plugin) {
        if(instance == null){
            instance = new PluginCore(plugin);
            instance.loadAPI();
        }
    }

    private void loadAPI(){
        final Instant start = Instant.now();
        final int steps = 4;

        logger.info(ConsoleColor.CYAN + "=========================" + ConsoleColor.RESET);
        logger.info(ConsoleColor.GREEN + "Starting to load MinecraftAPI... by @KIZA" + ConsoleColor.RESET);
        logger.info(ConsoleColor.CYAN + "=========================" + ConsoleColor.RESET);

        logger.info(ConsoleColor.YELLOW + "[1/" + steps +  "] Loading PlayerHandler... " + ConsoleColor.RESET);
        this.playerHandler = new PlayerHandler();
        logger.info(ConsoleColor.GREEN + "[✓] PlayerHandler loaded successfully!" + ConsoleColor.RESET);

        logger.info(ConsoleColor.YELLOW + "[2/" + steps +  "] Loading EventDispatcher... " + ConsoleColor.RESET);
        this.eventDispatcher = new EventDispatcher(this.plugin);
        logger.info(ConsoleColor.GREEN + "[✓] EventDispatcher loaded successfully!" + ConsoleColor.RESET);

        logger.info(ConsoleColor.YELLOW + "[3/" + steps +  "] Registering Event Listeners... " + ConsoleColor.RESET);
        this.plugin.getServer().getPluginManager().registerEvents(new EventListener(), plugin);
        logger.info(ConsoleColor.GREEN + "[✓] Listeners registered successfully!" + ConsoleColor.RESET);

        if(!Database.hasDatabase()){
            logger.info(ConsoleColor.BACKGROUND_RED + "You do not have database setup! Please refer to the documentation." + ConsoleColor.RESET);
            logger.info(ConsoleColor.BACKGROUND_RED + "Plugin will be disabled." + ConsoleColor.RESET);
            Bukkit.getPluginManager().disablePlugin(plugin);
        }else{
            logger.info(ConsoleColor.YELLOW + "[4/" + steps +  "] Setup database... " + ConsoleColor.RESET);
            this.plugin.getServer().getPluginManager().registerEvents(new EventListener(), plugin);
            logger.info(ConsoleColor.GREEN + "[✓] Database successfully setup!" + ConsoleColor.RESET);
        }

        logger.info(ConsoleColor.CYAN + "=========================" + ConsoleColor.RESET);
        logger.info(ConsoleColor.GREEN + "MinecraftAPI successfully loaded!" + ConsoleColor.RESET);
        logger.info(ConsoleColor.CYAN + "Execution time: " + ConsoleColor.YELLOW + Duration.between(start, Instant.now()).toMillis() + "ms" + ConsoleColor.RESET);
        logger.info(ConsoleColor.CYAN + "=========================" + ConsoleColor.RESET);
    }

    public static Core getInstance(){
        return instance;
    }

    public Player getPlayer() {
        return this.playerHandler.getPlayerData().getPlayer();
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public Logger getLogger() {
        return logger;
    }

    public PlayerHandler getPlayerHandler() {
        return playerHandler;
    }

    public EventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }
}
