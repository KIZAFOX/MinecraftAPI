package fr.kiza.minecraftapi.handler.exception;

import fr.kiza.minecraftapi.core.Core;
import fr.kiza.minecraftapi.handler.exception.handler.ExceptionHandler;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class ErrorException {

    public static Optional<Player> isValid() throws ExceptionHandler {
        if(Core.getInstance().getPlayerHandler().getPlayerData().getPlayer() == null) return Optional.empty();{
            throw new ExceptionHandler("Player does not exist or is offline!");
        }
    }
}
