package fr.kiza.minecraftapi.handler.packets.handler;

import net.minecraft.network.protocol.Packet;

public interface PacketBuilder<T> {
    PacketBuilder<T> message(String message);
    Packet<?> build();
}
