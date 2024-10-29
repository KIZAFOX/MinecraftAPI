package fr.kiza.minecraftapi.module.controller.event;

import fr.kiza.minecraftapi.core.Core;
import fr.kiza.minecraftapi.module.player.data.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {

    private PlayerData playerData;

    public EventListener() {
        this.playerData = Core.getInstance().getPlayerHandler().getPlayerData();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onLogin(final PlayerLoginEvent event){
        final Player player = event.getPlayer();

        this.playerData = new PlayerData(player);
        this.playerData.inject();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onLogout(final PlayerQuitEvent event){
        this.playerData.remove();
    }
}
