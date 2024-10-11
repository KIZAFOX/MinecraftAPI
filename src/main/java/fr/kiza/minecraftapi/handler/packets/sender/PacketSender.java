package fr.kiza.minecraftapi.handler.packets.sender;

import fr.kiza.minecraftapi.core.Core;
import net.minecraft.network.protocol.Packet;
import org.bukkit.craftbukkit.v1_21_R1.entity.CraftPlayer;

public class PacketSender {
    public static void sendPacket(final Packet<?> packet) {
        ((CraftPlayer) Core.getInstance().getPlayer()).getHandle().c.sendPacket(packet);
    }
}
