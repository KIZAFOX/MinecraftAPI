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

/**
 * Core is an abstract class that serves as the foundation for the Minecraft API.
 * It initializes essential modules and manages the overall API lifecycle.
 */
public abstract class Core {

    private static Core instance;

    public JavaPlugin plugin;
    public Logger logger;

    private final PlayerHandler playerHandler;
    private final EventDispatcher eventDispatcher;

    private EventListener eventListener;

    private final String
            API_START_MESSAGE = "Starting to load MinecraftAPI... by @KIZA",
            API_SUCCESS_MESSAGE = "MinecraftAPI successfully loaded!",
            DB_MISSING_MESSAGE = "Database setup missing! Check documentation. API will be disabled.";

    private final String[] STEP_MESSAGES = {
            "Setup database...", "Loading PlayerHandler...",
            "Loading EventDispatcher...", "Registering Event Listeners..."
    };

    /**
     * Initializes the Core class and its API.
     *
     * @param plugin The JavaPlugin instance associated with this plugin.
     * If the instance is not initialized, it creates a new instance of PluginCore.
     */
    public static void init(final JavaPlugin plugin) {
        if (instance == null) {
            instance = new PluginCore(plugin);
            instance.loadAPI();
        }
    }

    /**
     * Constructs a new Core instance with the specified plugin.
     *
     * @param plugin The JavaPlugin instance associated with this plugin.
     */
    protected Core(final JavaPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        this.playerHandler = new PlayerHandler();
        this.eventDispatcher = new EventDispatcher(plugin);
    }

    /**
     * Loads the API and initializes necessary components.
     * This method logs the loading process and checks for database setup.
     */
    private void loadAPI() {
        final Instant start = Instant.now();

        this.logLine();
        logger.info(colored(API_START_MESSAGE, ConsoleColor.GREEN));

        if (!initializeDatabase()) return;

        this.loadModules();
        logger.info(colored(API_SUCCESS_MESSAGE, ConsoleColor.GREEN));
        this.logExecutionTime(start);
        this.logLine();
    }

    /**
     * Initializes the database and checks its availability.
     *
     * @return true if the database is available; false otherwise.
     */
    private boolean initializeDatabase() {
        if (!Database.hasDatabase()) {
            this.logError();
            this.disablePlugin();
            return false;
        }
        this.logStepCompletion(1, ConsoleColor.YELLOW);
        return true;
    }

    /**
     * Loads the required modules for the API.
     */
    private void loadModules() {
        this.logStepCompletion(2, ConsoleColor.BLUE);
        this.logStepCompletion(3, ConsoleColor.PURPLE);
        this.registerListeners();
        this.logStepCompletion(4, ConsoleColor.YELLOW);
    }

    /**
     * Registers event listeners with the Bukkit plugin manager.
     */
    private void registerListeners() {
        this.plugin.getServer().getPluginManager().registerEvents(this.eventListener = new EventListener(), this.plugin);
    }

    /**
     * Disables the plugin if the database setup is missing.
     */
    private void disablePlugin() {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> Bukkit.getPluginManager().disablePlugin(this.plugin));
    }

    /**
     * Logs the completion of a specific step in the loading process.
     *
     * @param stepIndex The index of the step that has been completed.
     * @param color     The color to use for logging the completion message.
     */
    private void logStepCompletion(final int stepIndex, final ConsoleColor color) {
        this.logger.info(this.colored("[✓] " + STEP_MESSAGES[stepIndex - 1] + " completed successfully!", color));
    }

    /**
     * Logs the total execution time for the API loading process.
     *
     * @param start The start time of the loading process.
     */
    private void logExecutionTime(final Instant start) {
        long elapsedMillis = Duration.between(start, Instant.now()).toMillis();
        this.logger.info(colored("Execution time: " + elapsedMillis + "ms", ConsoleColor.YELLOW));
    }

    /**
     * Logs a decorative line to the console for better visibility.
     */
    private void logLine() {
        this.logger.info(colored("=========================", ConsoleColor.CYAN));
    }

    /**
     * Logs an error message if the database setup is missing.
     */
    private void logError() {
        this.logger.info(colored(DB_MISSING_MESSAGE, ConsoleColor.BACKGROUND_RED));
    }

    /**
     * Applies a color to the given message.
     *
     * @param message The message to color.
     * @param color   The color to apply.
     * @return The colored message.
     */
    private String colored(final String message, final ConsoleColor color) {
        return color + message + ConsoleColor.RESET;
    }

    /**
     * Retrieves the current instance of the Core class.
     *
     * @return The current Core instance.
     */
    public static Core getInstance() {
        return instance;
    }

    /**
     * Retrieves the player associated with this Core instance.
     *
     * @return The player instance managed by PlayerHandler.
     */
    public Player getPlayer() {
        return this.eventListener.getPlayerData().getPlayer();
    }

    /**
     * Retrieves the JavaPlugin instance associated with this Core instance.
     *
     * @return The JavaPlugin instance.
     */
    public JavaPlugin getPlugin() {
        return plugin;
    }

    /**
     * Retrieves the logger for this Core instance.
     *
     * @return The logger instance.
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Retrieves the PlayerHandler for managing player data.
     *
     * @return The PlayerHandler instance.
     */
    public PlayerHandler getPlayerHandler() {
        return playerHandler;
    }

    /**
     * Retrieves the EventDispatcher for managing event registration and handling.
     *
     * @return The EventDispatcher instance.
     */
    public EventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }
}
