package fr.kiza.minecraftapi.module.packet.handler;

import fr.kiza.minecraftapi.module.exception.PacketException;
import net.minecraft.network.protocol.Packet;
import org.bukkit.entity.Player;

/**
 * Interface for building custom packets.
 *
 * @param <T> The type of packet to build.
 */
public interface PacketBuilder<T> {

    /**
     * Sets the message for the packet.
     *
     * @param message The message to send.
     * @return The current instance of the builder for chaining.
     */
    default PacketBuilder<T> message(String message) {
        return this; // Implementation can be added here if necessary.
    }

    /**
     * Sets the title for the packet.
     *
     * @param title The title to set.
     * @return The current instance of the builder for chaining.
     */
    default PacketBuilder<T> title(String title) {
        return this; // Implementation can be added here.
    }

    /**
     * Sets the subtitle for the packet.
     *
     * @param subTitle The subtitle to set.
     * @return The current instance of the builder for chaining.
     */
    default PacketBuilder<T> subTitle(String subTitle) {
        return this; // Implementation can be added here.
    }

    /**
     * Sets the fade-in time for the packet.
     *
     * @param fadeIn Duration of the fade-in effect.
     * @return The current instance of the builder for chaining.
     */
    default PacketBuilder<T> fadeIn(int fadeIn) {
        return this; // Implementation can be added here.
    }

    /**
     * Sets the duration the packet remains visible.
     *
     * @param stay Duration for which the packet is displayed.
     * @return The current instance of the builder for chaining.
     */
    default PacketBuilder<T> stay(int stay) {
        return this; // Implementation can be added here.
    }

    /**
     * Sets the fade-out time for the packet.
     *
     * @param fadeOut Duration of the fade-out effect.
     * @return The current instance of the builder for chaining.
     */
    default PacketBuilder<T> fadeOut(int fadeOut) {
        return this; // Implementation can be added here.
    }

    default PacketBuilder<T> setPlayer(final Player player){
        return this;
    }

    /**
     * Builds the packet using the specified values.
     *
     * @return The constructed packet.
     */
    Packet<?> build() throws PacketException;
}
