package fr.kiza.minecraftapi.module.exception;

import fr.kiza.minecraftapi.core.Core;
import fr.kiza.minecraftapi.module.exception.handler.ExceptionHandler;
import org.bukkit.entity.Player;

import java.util.Optional;

public class ErrorException {

    public static Optional<Player> isValid() throws ExceptionHandler {
        if(Core.getInstance().getPlayerHandler().getPlayerData().getPlayer() == null) return Optional.empty();{
            throw new ExceptionHandler("Player does not exist or is offline!");
        }
    }
}
