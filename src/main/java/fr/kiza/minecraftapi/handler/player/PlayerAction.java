package fr.kiza.minecraftapi.handler.player;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface PlayerAction {
    void execute(final Player player);
}
