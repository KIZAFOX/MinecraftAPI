package fr.kiza.minecraftapi.module.database;

import fr.kiza.hikariapi.HikariAPI;

public class Database {
    public static boolean hasDatabase() {
        return HikariAPI.getDbHandler().pool().getDataSource() != null;
    }
}
