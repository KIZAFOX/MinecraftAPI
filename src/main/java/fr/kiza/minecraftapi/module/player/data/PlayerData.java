package fr.kiza.minecraftapi.module.player.data;

import fr.kiza.minecraftapi.core.Core;
import fr.kiza.minecraftapi.module.tools.logger.Logger;
import org.bukkit.entity.Player;

/**
 * Represents the data associated with a specific player.
 * Provides methods for initializing, injecting, and removing player data
 * from the system as required by the Minecraft API.
 */
public class PlayerData {

    protected final Core core;

    private final Player player;

    /**
     * Constructs a new PlayerData instance associated with the given player.
     *
     */
    public PlayerData(final Player player) {
        this.core = Core.getInstance();

        this.player = player;
    }

    /**
     * Injects this player data into the system.
     * This method is intended to be called when a player logs in.
     */
    public void inject() {
        if(hasAccount()){
            this.loadData();
        }else{
            Logger.print(player.getName() + " has been injected into the core successfully.", Logger.LoggerLevel.INFO);
        }
    }

    /**
     * Removes this player data from the system.
     * This method is intended to be called when a player logs out.
     */
    public void remove() {
        this.saveData();
        Logger.print(player.getName() + " has been removed from the core successfully.", Logger.LoggerLevel.INFO);
    }

    /**
     * Loads the player’s data from persistent storage.
     * This is a placeholder method and can be customized based on the storage solution.
     */
    private void loadData() {
        Logger.print(player.getName() + "'s data has been loaded from the core.", Logger.LoggerLevel.INFO);
    }

    /**
     * Saves the player’s data to persistent storage.
     * This is a placeholder method and can be customized based on the storage solution.
     */
    private void saveData() {
        Logger.print(player.getName() + "'s data has been saved into the core.", Logger.LoggerLevel.INFO);
    }

    private boolean hasAccount(){
        return false;
    }

    /**
     * Gets the core instance associated with this PlayerData.
     *
     * @return The Core instance.
     */
    public Core getCore() {
        return core;
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
