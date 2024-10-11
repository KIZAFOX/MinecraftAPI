package fr.kiza.minecraftapi.handler.player.handler;

import fr.kiza.minecraftapi.core.Core;
import fr.kiza.minecraftapi.handler.player.PlayerAction;
import fr.kiza.minecraftapi.handler.player.PlayerListener;
import fr.kiza.minecraftapi.handler.player.data.PlayerData;
import fr.kiza.minecraftapi.handler.tools.Logger;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.lang.reflect.Method;
import java.util.Optional;

public class PlayerHandler {

    public PlayerData playerData;

    public void performAction(final PlayerAction action, final Event event){
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        try {
            final String
                    callingClassName = stackTrace[2].getClassName(),
                    callingMethodName = stackTrace[2].getMethodName();
            final Class<?> callinngClass = Class.forName(callingClassName);
            final Method callingMethod = callinngClass.getMethod(callingMethodName, event.getClass());

            if(callingMethod.isAnnotationPresent(PlayerListener.class)){
                final Optional<Player> optionalPlayer = Optional.ofNullable(Core.getInstance().getPlayer());

                optionalPlayer.ifPresent(action::execute);
            }else{
                Logger.print("The method " + callingMethodName + " does not have the @PlayerInteraction annotation.", Logger.LoggerLevel.ERROR);

            }
        }catch (ClassNotFoundException | NoSuchMethodException e){
            throw new RuntimeException(e);
        }
    }

    public PlayerData getPlayerData() {
        return playerData;
    }
}
