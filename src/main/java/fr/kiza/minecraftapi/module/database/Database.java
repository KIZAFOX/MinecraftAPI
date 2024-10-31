package fr.kiza.minecraftapi.module.database;

import fr.kiza.hikariapi.HikariAPI;

/**
 * Utility class for database management within the Minecraft API.
 * Provides functionality to check for an active database connection.
 */
public class Database {

    /**
     * Checks if a database connection is available.
     *
     * @return {@code true} if the HikariAPI database handler is initialized, {@code false} otherwise.
     */
    public static boolean hasDatabase() {
        return HikariAPI.getDbHandler() != null;
    }
}
