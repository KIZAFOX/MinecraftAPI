package fr.kiza.minecraftapi.handler.player;

import fr.kiza.minecraftapi.handler.packets.handler.PacketHandler;
import org.bukkit.entity.Player;

@FunctionalInterface
public interface PlayerAction {
    void execute(final Player player, final PacketHandler packetHandler);
}
