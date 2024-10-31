package fr.kiza.minecraftapi.module.packet.sender;

import fr.kiza.minecraftapi.core.Core;
import fr.kiza.minecraftapi.module.exception.PacketException;
import fr.kiza.minecraftapi.module.packet.PacketFactory;
import fr.kiza.minecraftapi.module.packet.PacketType;
import fr.kiza.minecraftapi.module.tools.logger.Logger;
import net.minecraft.network.protocol.Packet;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_21_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * Utility class for sending packets to players.
 */
public class PacketSender {

    /**
     * Sends a single packet to the registered player.
     *
     * @param packet The packet to send.
     */
    public static void sendPacket(final Packet<?> packet) {
        runAsync(() -> {
            final Core core = Core.getInstance();
            final Player player = core.getPlayer();

            validatePlayer(player);

            ((CraftPlayer) player).getHandle().c.sendPacket(packet);
        });
    }

    /**
     * Sends multiple packets to the registered player.
     *
     * @param packets An array of packets to send.
     */
    public static void sendPackets(final Packet<?>... packets) {
        runAsync(() -> {
            final Core core = Core.getInstance();
            final Player player = core.getPlayer();

            validatePlayer(player);

            Arrays.stream(packets).forEach(packet -> ((CraftPlayer) player).getHandle().c.sendPacket(packet));
        });
    }

    /**
     * Sends a message packet to the registered player.
     *
     * @param message The message content to send.
     */
    public static void sendMessage(final String message) {
        final Core core = Core.getInstance();
        final Player player = core.getPlayer();

        validatePlayer(player);

        try {
            PacketSender.sendPacket(PacketFactory.getBuilder(PacketType.MESSAGE_PLAYER)
                    .message(message)
                    .build());
        } catch (final PacketException e) {
            System.err.println("Failed to send message to player " + player.getName() + ": " + e.getMessage());
        }
    }

    private static void runAsync(final Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(Core.getInstance().getPlugin(), runnable);
    }

    /**
     * Validates if the player is registered.
     *
     * @param player The player to validate.
     */
    private static void validatePlayer(final Player player) {
        if (player == null) {
            Logger.print("No registered player found to send packet.", Logger.LoggerLevel.ERROR);
            throw new IllegalStateException("Player not registered.");
        }
    }
}
