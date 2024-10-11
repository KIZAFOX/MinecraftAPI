package fr.kiza.minecraftapi.handler.exception;

import fr.kiza.minecraftapi.handler.exception.handler.ExceptionHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class ErrorException {

    private static Optional<Player> isValid(final Player player) throws ExceptionHandler {
        if(player == null || !player.isOnline()){
            throw new ExceptionHandler("Player does not exist or is offline!");
        }
        return Optional.of(player);
    }

    public static Optional<Player> isValidPlayer(final Player player) {
        try {
            return isValid(player);
        }catch (final ExceptionHandler e){
            throw new RuntimeException(e);
        }
    }

    public static Optional<Player> isValidPlayer(final String playerName) {
        try {
            return isValid(Bukkit.getPlayer(playerName));
        }catch (final ExceptionHandler e){
            throw new RuntimeException(e);
        }
    }

    public static Optional<Player> isValidPlayer(final UUID playerUuid) throws ExceptionHandler {
        try {
            return isValid(Bukkit.getPlayer(playerUuid));
        }catch (final ExceptionHandler e){
            throw new RuntimeException(e);
        }
    }
}
