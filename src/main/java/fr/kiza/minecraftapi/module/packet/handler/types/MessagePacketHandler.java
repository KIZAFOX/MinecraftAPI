package fr.kiza.minecraftapi.module.packet.handler.types;

import fr.kiza.minecraftapi.module.packet.handler.PacketBuilder;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;

/**
 * Handles the construction of message packets, enabling
 * message configuration for client-bound chat systems.
 */
public class MessagePacketHandler implements PacketBuilder<ClientboundSystemChatPacket> {

    private String message;

    /**
     * Sets the message content for the packet.
     *
     * @param message The text content of the message.
     * @return The current instance of MessagePacketHandler for method chaining.
     */
    @Override
    public PacketBuilder<ClientboundSystemChatPacket> message(String message) {
        this.message = message;
        return this;
    }

    /**
     * Builds and returns a ClientboundSystemChatPacket with the specified message content.
     * If no message is set, an empty message is used.
     *
     * @return A ClientboundSystemChatPacket instance.
     */
    @Override
    public ClientboundSystemChatPacket build() {
        IChatBaseComponent chatComponent = IChatBaseComponent.a(this.message != null ? this.message : "");
        return new ClientboundSystemChatPacket(chatComponent, false);
    }
}
