package fr.kiza.minecraftapi.handler.player.data;

import fr.kiza.minecraftapi.core.Core;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public record PlayerData(Player player) {

    private static final Core instance = Core.getInstance();

    public static final Map<Player, UUID> PLAYERS = new HashMap<>();

    public void inject() {
        PLAYERS.put(player, player.getUniqueId());
        instance.logger.info(player.getName() + " successfully injected into the core.");
    }

    public void remove() {
        PLAYERS.remove(player);
        instance.logger.info(player.getName() + " successfully removed from the core.");
    }

    public Player getPlayer() {
        return player;
    }
}
