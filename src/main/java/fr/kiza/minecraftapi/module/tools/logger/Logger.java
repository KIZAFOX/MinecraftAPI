package fr.kiza.minecraftapi.module.tools.logger;

import fr.kiza.minecraftapi.core.Core;
import fr.kiza.minecraftapi.module.tools.color.ConsoleColor;

/**
 * Logger is a utility class that provides a standardized way to log messages
 * at different levels of severity. It leverages the Bukkit logger for message output.
 */
public class Logger {

    /**
     * Prints a formatted log message at the specified logger level.
     *
     * @param message    The message to log.
     * @param loggerLevel The level of the log (e.g., DEBUG, INFO, WARN, etc.).
     */
    public static void print(final String message, LoggerLevel loggerLevel) {
        final Core instance = Core.getInstance();
        final String formattedMessage = loggerLevel.getColor() + "[LEVEL:" + loggerLevel.getLabel() + "] " + message + ConsoleColor.RESET;

        // Log the message based on the specified logger level.
        switch (loggerLevel) {
            case DEBUG, INFO -> instance.getLogger().info(formattedMessage);
            case WARN -> instance.getLogger().warning(formattedMessage);
            case ERROR, FATAL -> instance.getLogger().severe(formattedMessage);
            case TRACE -> instance.getLogger().fine(formattedMessage);
        }
    }

    /**
     * Enum representing the different levels of logging severity.
     */
    public enum LoggerLevel {
        DEBUG(ConsoleColor.PURPLE, "DEBUG"),
        INFO(ConsoleColor.GREEN, "INFO"),
        WARN(ConsoleColor.YELLOW, "WARN"),
        ERROR(ConsoleColor.RED, "ERROR"),
        FATAL(ConsoleColor.BACKGROUND_RED, "FATAL"),
        TRACE(ConsoleColor.CYAN, "TRACE");

        private final ConsoleColor color; // Color associated with the logger level.
        private final String label;        // Label of the logger level.

        /**
         * Constructs a LoggerLevel with the specified color and label.
         *
         * @param color The color associated with this logger level.
         * @param label The label of the logger level.
         */
        LoggerLevel(ConsoleColor color, String label) {
            this.color = color;
            this.label = label;
        }

        /**
         * Retrieves the color associated with this logger level.
         *
         * @return The console color of the logger level.
         */
        public ConsoleColor getColor() {
            return color;
        }

        /**
         * Retrieves the label of this logger level.
         *
         * @return The label of the logger level.
         */
        public String getLabel() {
            return label;
        }

        /**
         * Returns a string representation of the logger level with its associated color.
         *
         * @return The colored string representation of the logger level.
         */
        @Override
        public String toString() {
            return color + label + ConsoleColor.RESET;
        }
    }
}
