package fr.kiza.minecraftapi.handler.packet.sender;

import fr.kiza.minecraftapi.handler.packet.PacketFactory;
import fr.kiza.minecraftapi.handler.packet.PacketType;

public class FastPacket {
    public static void sendMessage(final String message) {
        PacketSender.sendPacket(PacketFactory.getBuilder(PacketType.MESSAGE_PLAYER)
                .message(message)
                .build()
        );
    }
}
