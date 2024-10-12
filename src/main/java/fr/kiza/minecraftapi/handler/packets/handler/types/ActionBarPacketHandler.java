package fr.kiza.minecraftapi.handler.packets.handler.types;

import fr.kiza.minecraftapi.handler.packets.handler.PacketBuilder;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;

public class ActionBarPacketHandler implements PacketBuilder<ClientboundSetActionBarTextPacket> {

    protected String message;

    @Override
    public PacketBuilder<ClientboundSetActionBarTextPacket> message(String message) {
        this.message = message;
        return this;
    }

    @Override
    public Packet<?> build() {
        return new ClientboundSetActionBarTextPacket(IChatBaseComponent.a(this.message));
    }
}
