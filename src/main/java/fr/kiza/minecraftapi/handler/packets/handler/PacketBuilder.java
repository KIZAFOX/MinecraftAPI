package fr.kiza.minecraftapi.handler.packets.handler;

import net.minecraft.network.protocol.Packet;

public interface PacketBuilder<T> {

    default PacketBuilder<T> message(String message){
        return this;
    }

    default PacketBuilder<T> title(String title){
        return this;
    }

    default PacketBuilder<T> subTitle(String subTitle){
        return this;
    }

    default PacketBuilder<T> fadeIn(int fadeIn){
        return this;
    }

    default PacketBuilder<T> stay(int stay){
        return this;
    }

    default PacketBuilder<T> fadeOut(int fadeOut){
        return this;
    }

    Packet<?> build();

}
