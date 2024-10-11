package fr.kiza.minecraftapi.handler.tools;

import fr.kiza.minecraftapi.core.Core;

public class Logger {

    public static void print(final String message, LoggerLevel loggerLevel){
        final Core instance = Core.getInstance();
        final String formattedMessage = loggerLevel.getColor() + "[LEVEL:" + loggerLevel.getLabel() + "] " + message + ConsoleColor.RESET;

        switch (loggerLevel){
            case DEBUG, INFO -> instance.getLogger().info(formattedMessage);
            case WARN -> instance.getLogger().warning(formattedMessage);
            case ERROR, FATAL -> instance.getLogger().severe(formattedMessage);
            case TRACE -> instance.getLogger().fine(formattedMessage);
        }
    }

    public enum LoggerLevel{
        DEBUG(ConsoleColor.PURPLE, "DEBUG"),
        INFO(ConsoleColor.GREEN, "INFO"),
        WARN(ConsoleColor.YELLOW, "WARN"),
        ERROR(ConsoleColor.RED, "ERROR"),
        FATAL(ConsoleColor.BACKGROUND_RED, "FATAL"),
        TRACE(ConsoleColor.CYAN, "TRACE");

        private final ConsoleColor color;
        private final String label;

        LoggerLevel(ConsoleColor color, String label) {
            this.color = color;
            this.label = label;
        }

        public ConsoleColor getColor() {
            return color;
        }

        public String getLabel() {
            return label;
        }

        @Override
        public String toString() {
            return color + label + ConsoleColor.RESET;
        }
    }
}
