package fr.kiza.minecraftapi.module.player.data;

import fr.kiza.minecraftapi.core.Core;
import fr.kiza.minecraftapi.module.exception.PlayerNotValid;
import fr.kiza.minecraftapi.module.exception.handler.ExceptionHandler;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerData {

    private final Player player;
    private final Map<Player, UUID> players;

    public PlayerData(Player player) {
        this.player = player;
        this.players = new HashMap<>();
    }

    public void inject() {
        this.players.put(this.player, this.player.getUniqueId());
        try {
            if(PlayerNotValid.isValid()){
                Core.getInstance().logger.info(this.player.getName() + " successfully injected into the core.");
            }
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public void remove() {
        try {
            if(PlayerNotValid.isValid()){
                this.players.remove(this.player);
                Core.getInstance().logger.info(this.player.getName() + " successfully removed from the core.");
            }
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Map<Player, UUID> getPlayers() {
        return players;
    }
}
