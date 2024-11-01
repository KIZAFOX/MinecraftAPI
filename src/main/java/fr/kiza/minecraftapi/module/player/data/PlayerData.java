package fr.kiza.minecraftapi.module.player.data;

import fr.kiza.hikariapi.HikariAPI;
import fr.kiza.hikariapi.database.query.DBQuery;
import fr.kiza.minecraftapi.core.Core;
import fr.kiza.minecraftapi.module.packet.handler.types.IpAddressPacketHandler;
import fr.kiza.minecraftapi.module.tools.logger.Logger;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Represents the data associated with a specific player.
 * Provides methods for initializing, injecting, and removing player data
 * from the system as required by the Minecraft API.
 */
public class PlayerData {

    protected final Core instance;
    protected final Plugin plugin;

    private final Player player;
    private final UUID uuid;

    private final DataSource dataSource;

    public static final String TABLE = "player_data_api";

    /**
     * Constructs a new PlayerData instance associated with the given player.
     *
     */
    public PlayerData(final Player player) {
        this.instance = Core.getInstance();
        this.plugin = this.instance.getPlugin();

        this.player = player;
        this.uuid = player.getUniqueId();

        this.dataSource = HikariAPI.getDbHandler().pool().getDataSource();
    }

    /**
     * Injects this player data into the system.
     * This method is intended to be called when a player logs in.
     */
    public void inject() {
        this.plugin.getServer().getScheduler().runTaskAsynchronously(this.plugin, () -> {
            if(!hasAccount()){
                Logger.print("Waiting to create " + this.player.getName() + "'s account.", Logger.LoggerLevel.INFO);

                this.plugin.getServer().getScheduler().runTaskLater(this.plugin, () -> {
                    new DBQuery(this.dataSource)
                            .update(
                                    "INSERT INTO " + TABLE + "(uuid, username, ipAddress) VALUES (" +
                                            "'" + this.uuid + "'," +
                                            "'" + this.player.getName() + "'," +
                                            "'" + IpAddressPacketHandler.getAll(this.player) + "')");
                    Logger.print(this.player.getName() + " has been injected into the core successfully.", Logger.LoggerLevel.INFO);
                }, 20L);
            }else{
                this.loadData();
            }
        });
    }

    /**
     * Removes this player data from the system.
     * This method is intended to be called when a player logs out.
     */
    public void remove() {
        this.plugin.getServer().getScheduler().runTaskAsynchronously(this.plugin, () -> {
            this.saveData();
            PlayerObject.PLAYER.remove(this.uuid);
            Logger.print(this.player.getName() + " has been removed from the core successfully.", Logger.LoggerLevel.INFO);
        });
    }

    /**
     * Loads the player’s data from persistent storage.
     * This is a placeholder method and can be customized based on the storage solution.
     */
    private void loadData() {
        this.plugin.getServer().getScheduler().runTaskAsynchronously(this.plugin, () -> {
            if(hasAccount()){
                new DBQuery(this.dataSource).query(resultSet -> {
                    try {
                        while (resultSet.next()){
                            final String uuid = resultSet.getString("uuid");
                            final UUID formattedUUID = UUID.fromString(uuid);
                            final PlayerObject playerObject = new PlayerObject(formattedUUID);

                            PlayerObject.PLAYER.put(formattedUUID, playerObject);
                        }
                    } catch (final SQLException e){
                        throw new RuntimeException(e);
                    }
                    return null;
                }, "SELECT uuid, username, ipAddress FROM " + TABLE + " WHERE uuid = '" + this.uuid + "'");
            }

            Logger.print(player.getName() + "'s data has been loaded from the core.", Logger.LoggerLevel.INFO);
        });
    }

    /**
     * Saves the player’s data to persistent storage.
     * This is a placeholder method and can be customized based on the storage solution.
     */
    private void saveData() {
        this.plugin.getServer().getScheduler().runTaskAsynchronously(this.plugin, () -> {
            final PlayerObject playerObject = new PlayerObject(this.uuid);

            new DBQuery(this.dataSource).update("UPDATE " + TABLE + " SET uuid = '" + playerObject.uuid() + "'");
            Logger.print(player.getName() + "'s data has been saved into the core.", Logger.LoggerLevel.INFO);
        });
    }

    private boolean hasAccount(){
        return (boolean) new DBQuery(this.dataSource).query(resultSet -> {
            try {
                if(resultSet.next()){
                    return true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return false;
        }, "SELECT uuid FROM " + TABLE + " WHERE uuid = '" + uuid + "'");
    }

    /**
     * Gets the core instance associated with this PlayerData.
     *
     * @return The Core instance.
     */
    public Core getCore() {
        return instance;
    }

    /**
     * Gets the player associated with this PlayerData.
     *
     * @return The Player instance.
     */
    public Player getPlayer() {
        return player;
    }
}
