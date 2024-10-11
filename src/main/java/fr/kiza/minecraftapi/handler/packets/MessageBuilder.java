package fr.kiza.minecraftapi.handler.packets;

import fr.kiza.minecraftapi.handler.packets.handler.PacketHandler;
import fr.kiza.minecraftapi.handler.packets.sender.PacketSender;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;

public class MessageBuilder {

    protected final PacketHandler packetHandler;

    public MessageBuilder(PacketHandler packetHandler) {
        this.packetHandler = packetHandler;
    }

    public void build(final String message){
        final IChatBaseComponent component = IChatBaseComponent.a(message);
        final ClientboundSystemChatPacket packet = new ClientboundSystemChatPacket(component, false);

        PacketSender.sendPacket(packet);
    }
}
