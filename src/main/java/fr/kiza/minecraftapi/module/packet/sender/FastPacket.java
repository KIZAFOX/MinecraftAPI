package fr.kiza.minecraftapi.module.packet.sender;

import fr.kiza.minecraftapi.module.packet.PacketFactory;
import fr.kiza.minecraftapi.module.packet.PacketType;

public class FastPacket {
    public static void sendMessage(final String message) {
        PacketSender.sendPacket(PacketFactory.getBuilder(PacketType.MESSAGE_PLAYER)
                .message(message)
                .build()
        );
    }
}
