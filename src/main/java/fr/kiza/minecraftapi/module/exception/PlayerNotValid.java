package fr.kiza.minecraftapi.module.exception;

import fr.kiza.minecraftapi.core.Core;
import fr.kiza.minecraftapi.module.exception.handler.ExceptionHandler;

public class PlayerNotValid {
    public static boolean isValid() throws ExceptionHandler {
        if(Core.getInstance().getPlayerHandler().getPlayerData().getPlayer() == null){
            throw new ExceptionHandler("Player does not exist or is offline!");
        }
        return true;
    }
}
