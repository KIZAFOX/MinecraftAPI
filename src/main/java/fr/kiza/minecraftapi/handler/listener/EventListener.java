package fr.kiza.minecraftapi.handler.listener;

import fr.kiza.minecraftapi.core.Core;
import fr.kiza.minecraftapi.handler.player.data.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {

    private static final Core instance = Core.getInstance();

    @EventHandler(priority = EventPriority.MONITOR)
    public void onLogin(final PlayerLoginEvent event){
        instance.getPlayerHandler().playerData = new PlayerData(event.getPlayer());
        instance.getPlayerHandler().getPlayerData().inject();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onLogout(final PlayerQuitEvent event){
        instance.getPlayerHandler().getPlayerData().remove();
    }
}
