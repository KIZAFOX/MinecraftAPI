package fr.kiza.minecraftapi.handler.packets.handler.types;

import fr.kiza.minecraftapi.handler.packets.handler.PacketBuilder;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;

public class MessagePacketHandler implements PacketBuilder<ClientboundSystemChatPacket> {

    protected String message;

    @Override
    public PacketBuilder<ClientboundSystemChatPacket> message(String message) {
        this.message = message;
        return this;
    }

    @Override
    public ClientboundSystemChatPacket build() {
        return new ClientboundSystemChatPacket(IChatBaseComponent.a(this.message), false);
    }
}
