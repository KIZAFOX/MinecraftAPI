package fr.kiza.minecraftapi.module.packet.handler.types;

import fr.kiza.minecraftapi.module.packet.handler.PacketBuilder;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;

/**
 * Builds and handles action bar packets, allowing for the setting of custom
 * messages displayed in the player's action bar.
 */
public class ActionBarPacketHandler implements PacketBuilder<ClientboundSetActionBarTextPacket> {

    private String message;

    /**
     * Sets the message to display in the action bar.
     *
     * @param message The text content for the action bar message.
     * @return The current instance of ActionBarPacketHandler for fluent chaining.
     */
    @Override
    public PacketBuilder<ClientboundSetActionBarTextPacket> message(String message) {
        this.message = message;
        return this;
    }

    /**
     * Constructs the ClientboundSetActionBarTextPacket with the specified message.
     * If no message is set, an empty action bar message is used.
     *
     * @return A ClientboundSetActionBarTextPacket instance for sending to the player.
     */
    @Override
    public Packet<?> build() {
        IChatBaseComponent actionBarComponent = IChatBaseComponent.a(this.message != null ? this.message : "");
        return new ClientboundSetActionBarTextPacket(actionBarComponent);
    }
}
