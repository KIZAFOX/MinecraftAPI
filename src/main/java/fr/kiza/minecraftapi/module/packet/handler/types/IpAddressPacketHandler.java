package fr.kiza.minecraftapi.module.packet.handler.types;

import fr.kiza.minecraftapi.module.exception.PacketException;
import fr.kiza.minecraftapi.module.packet.handler.PacketBuilder;
import net.minecraft.network.protocol.Packet;
import org.bukkit.entity.Player;

import java.net.InetSocketAddress;

/**
 * The IpAddressPacketHandler class is responsible for handling IP address
 * retrieval from a Player object in a Minecraft server environment.
 * It implements the PacketBuilder interface, allowing for construction
 * of packets (currently not implemented).
 */
public class IpAddressPacketHandler implements PacketBuilder<Void> {

    private Player player;
    private String ipAddress, port;

    /**
     * Sets the player for this handler.
     *
     * @param player The Player object whose IP address will be retrieved.
     * @return The current instance of IpAddressPacketHandler for method chaining.
     */
    @Override
    public PacketBuilder<Void> setPlayer(final Player player) {
        this.player = player;
        return this;
    }

    /**
     * Builds the packet using the specified values.
     * This method retrieves the player's IP address and port from their
     * socket address and stores them in the respective fields.
     *
     * @return null (the packet construction is not currently implemented).
     * @throws PacketException if the player or their address is null.
     */
    @Override
    public Packet<?> build() throws PacketException {
        if (this.player == null) {
            throw new PacketException("Player is null! Please set a player first");
        }

        final InetSocketAddress address = player.getAddress();

        if (address == null) {
            throw new PacketException("Address is null! Please set a player first");
        }

        // Extract the IP address and port from the InetSocketAddress
        this.ipAddress = address.toString().split(":")[0].replace("/", "");
        this.port = address.toString().split(":")[1];
        return null; // Packet construction not implemented
    }

    /**
     * Retrieves the IP address of the specified player.
     *
     * @param player The Player object for which the IP address is requested.
     * @return The player's IP address as a string.
     * @throws RuntimeException if the player is not fully connected or their address is null.
     */
    public static String getIpAddress(final Player player) {
        try {
            if (player == null || !player.isOnline() || player.getAddress() == null) {
                throw new PacketException("Player is not fully connected or address is null.");
            }

            final IpAddressPacketHandler ipAddressPacketHandler = new IpAddressPacketHandler();
            ipAddressPacketHandler.setPlayer(player);
            ipAddressPacketHandler.build();

            return ipAddressPacketHandler.getIpAddress();
        } catch (final PacketException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the port of the specified player.
     *
     * @param player The Player object for which the port is requested.
     * @return The player's port as a string.
     * @throws RuntimeException if the player is not fully connected or their address is null.
     */
    public static String getPort(final Player player) {
        try {
            if (player == null || !player.isOnline() || player.getAddress() == null) {
                throw new PacketException("Player is not fully connected or address is null.");
            }

            final IpAddressPacketHandler ipAddressPacketHandler = new IpAddressPacketHandler();
            ipAddressPacketHandler.setPlayer(player);
            ipAddressPacketHandler.build();

            return ipAddressPacketHandler.getPort();
        } catch (final PacketException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves both the IP address and port of the specified player in the format "ip:port".
     *
     * @param player The Player object for which the IP address and port are requested.
     * @return A string in the format "ip:port".
     */
    public static String getAll(final Player player) {
        return IpAddressPacketHandler.getIpAddress(player) + ":" + IpAddressPacketHandler.getPort(player);
    }

    /**
     * Gets the player associated with this handler.
     *
     * @return The Player object associated with this handler.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the IP address of the player.
     *
     * @return The player's IP address as a string.
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Gets the port of the player.
     *
     * @return The player's port as a string.
     */
    public String getPort() {
        return port;
    }
}
