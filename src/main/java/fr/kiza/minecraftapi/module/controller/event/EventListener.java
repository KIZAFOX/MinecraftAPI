package fr.kiza.minecraftapi.module.controller.event;

import fr.kiza.minecraftapi.module.player.data.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles player login and logout events, managing player data injection and removal
 * from the system.
 */
public class EventListener implements Listener {

    private PlayerData playerData;

    private final Map<Player, PlayerData> playerDataMap = new HashMap<>();

    /**
     * Handles player login events.
     * Injects the player into the core by creating a PlayerData instance.
     *
     * @param event The player login event.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLogin(final PlayerLoginEvent event) {
        final Player player = event.getPlayer();
        this.playerData = new PlayerData(player);

        playerData.inject();
        this.playerDataMap.put(player, playerData);
    }

    /**
     * Handles player logout events.
     * Removes the player from the core and clears their data.
     *
     * @param event The player quit event.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLogout(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        final PlayerData playerData = playerDataMap.remove(player);

        if (playerData != null) {
            playerData.remove();
        }
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public Map<Player, PlayerData> getPlayerDataMap() {
        return playerDataMap;
    }
}
