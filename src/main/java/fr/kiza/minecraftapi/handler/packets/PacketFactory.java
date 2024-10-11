package fr.kiza.minecraftapi.handler.packets;

import fr.kiza.minecraftapi.handler.packets.handler.PacketBuilder;
import fr.kiza.minecraftapi.handler.packets.handler.types.MessagePacketHandler;

import java.util.Objects;

public class PacketFactory {
    public static PacketBuilder<?> getBuilder(PacketType packetType){
        if(Objects.requireNonNull(packetType) == PacketType.MESSAGE_PLAYER) {
            new MessagePacketHandler();
        }
        throw new IllegalArgumentException("Unknown packet type: " + packetType);
    }
}
