package fr.kiza.minecraftapi.module.packet.sender;

import fr.kiza.minecraftapi.core.Core;
import net.minecraft.network.protocol.Packet;
import org.bukkit.craftbukkit.v1_21_R1.entity.CraftPlayer;
import org.bukkit.plugin.Plugin;

public class PacketSender {
    public static void sendPacket(final Packet<?> packet) {
        final Core core = Core.getInstance();
        final Plugin plugin = core.getPlugin();
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> ((CraftPlayer) core.getPlayer()).getHandle().c.sendPacket(packet));
    }
}
