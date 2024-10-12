package fr.kiza.minecraftapi.handler.packets;

import fr.kiza.minecraftapi.handler.packets.handler.PacketBuilder;
import fr.kiza.minecraftapi.handler.packets.handler.types.MessagePacketHandler;
import fr.kiza.minecraftapi.handler.packets.handler.types.TitlePacketHandler;

public class PacketFactory {
    public static PacketBuilder<?> getBuilder(PacketType packetType){
        if(packetType == PacketType.MESSAGE_PLAYER){
            return new MessagePacketHandler();
        }else if(packetType == PacketType.TITLE){
            return new TitlePacketHandler();
        }

        throw new IllegalArgumentException("Unknown packet type: " + packetType);
    }
}
