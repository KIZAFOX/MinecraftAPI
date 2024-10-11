package fr.kiza.minecraftapi.handler.packets;

import fr.kiza.minecraftapi.handler.packets.handler.PacketBuilder;
import fr.kiza.minecraftapi.handler.packets.handler.types.MessagePacketHandler;

public class PacketFactory {
    public static PacketBuilder<?> getBuilder(String packetId){
        if(packetId.equals(MessagePacketHandler.PACKET_ID)){
            return new MessagePacketHandler();
        }
        throw new IllegalArgumentException("Unknown packet type: " + packetId);
    }
}
