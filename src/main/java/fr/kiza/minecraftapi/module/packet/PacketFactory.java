package fr.kiza.minecraftapi.module.packet;

import fr.kiza.minecraftapi.module.packet.handler.PacketBuilder;
import fr.kiza.minecraftapi.module.packet.handler.types.ActionBarPacketHandler;
import fr.kiza.minecraftapi.module.packet.handler.types.MessagePacketHandler;
import fr.kiza.minecraftapi.module.packet.handler.types.TitlePacketHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory for creating packet builders based on the specified PacketType.
 */
public class PacketFactory {

    private static final Map<PacketType, PacketBuilder<?>> builders = new HashMap<>();

    static {
        // Registering packet handlers for each packet type
        builders.put(PacketType.MESSAGE_PLAYER, new MessagePacketHandler());
        builders.put(PacketType.TITLE, new TitlePacketHandler());
        builders.put(PacketType.ACTION_BAR, new ActionBarPacketHandler());
    }

    /**
     * Retrieves the appropriate PacketBuilder for the specified PacketType.
     *
     * @param packetType The type of packet for which a builder is requested.
     * @return A PacketBuilder associated with the given PacketType.
     * @throws IllegalArgumentException If the specified packet type is unknown.
     */
    public static PacketBuilder<?> getBuilder(final PacketType packetType) {
        final PacketBuilder<?> builder = builders.get(packetType);
        if (builder == null) {
            throw new IllegalArgumentException("Unknown packet type: " + packetType);
        }
        return builder;
    }
}
