package fr.kiza.minecraftapi.module.packet;

import fr.kiza.minecraftapi.module.packet.handler.PacketBuilder;
import fr.kiza.minecraftapi.module.packet.handler.types.ActionBarPacketHandler;
import fr.kiza.minecraftapi.module.packet.handler.types.MessagePacketHandler;
import fr.kiza.minecraftapi.module.packet.handler.types.TitlePacketHandler;

public class PacketFactory {
    public static PacketBuilder<?> getBuilder(PacketType packetType){
        if(packetType == PacketType.MESSAGE_PLAYER){
            return new MessagePacketHandler();
        }else if(packetType == PacketType.TITLE){
            return new TitlePacketHandler();
        }else if(packetType == PacketType.ACTION_BAR){
            return new ActionBarPacketHandler();
        }

        throw new IllegalArgumentException("Unknown packet type: " + packetType);
    }
}
