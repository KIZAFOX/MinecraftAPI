package fr.kiza.minecraftapi.core;

import fr.kiza.minecraftapi.handler.listener.EventListener;
import fr.kiza.minecraftapi.handler.listener.event.EventDispatcher;
import fr.kiza.minecraftapi.handler.packets.handler.PacketHandler;
import fr.kiza.minecraftapi.handler.player.handler.PlayerHandler;
import fr.kiza.minecraftapi.handler.tools.ConsoleColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public abstract class Core {

    private static Core instance;

    public JavaPlugin plugin;
    public Logger logger;

    private PlayerHandler playerHandler;
    private PacketHandler packetHandler;

    private EventDispatcher eventDispatcher;

    public static final String PREFIX = "[MinecraftAPI] ";

    public Core(){}

    public static void init(final JavaPlugin plugin) {
        if(instance == null){
            instance = new PluginCore(plugin);
            instance.loadAPI();
        }
    }

    private void loadAPI(){
        long startTime = System.currentTimeMillis();

        logger.info(ConsoleColor.CYAN + "=========================" + ConsoleColor.RESET);
        logger.info(ConsoleColor.GREEN + PREFIX + "Starting to load MinecraftAPI... by @KIZA" + ConsoleColor.RESET);
        logger.info(ConsoleColor.CYAN + "=========================" + ConsoleColor.RESET);

        logger.info(ConsoleColor.YELLOW + "[1/4] Loading PlayerHandler... " + ConsoleColor.RESET);
        this.playerHandler = new PlayerHandler();
        logger.info(ConsoleColor.GREEN + "[✓] PlayerHandler loaded successfully!" + ConsoleColor.RESET);

        logger.info(ConsoleColor.YELLOW + "[2/4] Loading PacketHandler... " + ConsoleColor.RESET);
        this.packetHandler = new PacketHandler();
        logger.info(ConsoleColor.GREEN + "[✓] PacketHandler loaded successfully!" + ConsoleColor.RESET);

        logger.info(ConsoleColor.YELLOW + "[3/4] Loading EventDispatcher... " + ConsoleColor.RESET);
        this.eventDispatcher = new EventDispatcher(this.plugin);
        logger.info(ConsoleColor.GREEN + "[✓] EventDispatcher loaded successfully!" + ConsoleColor.RESET);

        logger.info(ConsoleColor.YELLOW + "[4/4] Registering Event Listeners... " + ConsoleColor.RESET);
        this.plugin.getServer().getPluginManager().registerEvents(new EventListener(), plugin);
        logger.info(ConsoleColor.GREEN + "[✓] Listeners registered successfully!" + ConsoleColor.RESET);

        long endTime = System.currentTimeMillis(); // End time for execution tracking
        long executionTime = endTime - startTime; // Calculate execution time

        logger.info(ConsoleColor.CYAN + "=========================" + ConsoleColor.RESET);
        logger.info(ConsoleColor.GREEN + PREFIX + "MinecraftAPI successfully loaded!" + ConsoleColor.RESET);
        logger.info(ConsoleColor.CYAN + "Execution time: " + ConsoleColor.YELLOW + executionTime + "ms" + ConsoleColor.RESET);
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

    public PacketHandler getPacketHandler() {
        return packetHandler;
    }

    public EventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }
}
