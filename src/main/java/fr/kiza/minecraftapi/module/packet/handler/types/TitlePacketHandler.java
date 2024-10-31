package fr.kiza.minecraftapi.module.packet.handler.types;

import fr.kiza.minecraftapi.module.exception.PacketException;
import fr.kiza.minecraftapi.module.packet.handler.PacketBuilder;
import fr.kiza.minecraftapi.module.packet.sender.PacketSender;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;

/**
 * A handler for building and sending title packets to players.
 */
public class TitlePacketHandler implements PacketBuilder<ClientboundSetTitleTextPacket> {

    private String title;
    private String subTitle;
    private int fadeIn;
    private int stay;
    private int fadeOut;

    /**
     * Sets the title text to be displayed.
     *
     * @param title The title text.
     * @return The current instance of the builder for chaining.
     */
    @Override
    public PacketBuilder<ClientboundSetTitleTextPacket> title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Sets the subtitle text to be displayed.
     *
     * @param subTitle The subtitle text.
     * @return The current instance of the builder for chaining.
     */
    @Override
    public PacketBuilder<ClientboundSetTitleTextPacket> subTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    /**
     * Sets the duration for the fade-in effect.
     *
     * @param fadeIn The duration of the fade-in effect.
     * @return The current instance of the builder for chaining.
     */
    @Override
    public PacketBuilder<ClientboundSetTitleTextPacket> fadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
        return this;
    }

    /**
     * Sets the duration for which the title remains visible.
     *
     * @param stay The duration the title stays on the screen.
     * @return The current instance of the builder for chaining.
     */
    @Override
    public PacketBuilder<ClientboundSetTitleTextPacket> stay(int stay) {
        this.stay = stay;
        return this;
    }

    /**
     * Sets the duration for the fade-out effect.
     *
     * @param fadeOut The duration of the fade-out effect.
     * @return The current instance of the builder for chaining.
     */
    @Override
    public PacketBuilder<ClientboundSetTitleTextPacket> fadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
        return this;
    }

    /**
     * Builds the packets and sends them to the player.
     *
     * @return null, as the packets are sent directly.
     */
    @Override
    public Packet<?> build() throws PacketException {
        // Create the title and subtitle components
        final IChatBaseComponent
                titleComponent = IChatBaseComponent.a(this.title),
                subTitleComponent = IChatBaseComponent.a(this.subTitle);

        // Build the title packets
        final Packet<?>
                titlePacket = new ClientboundSetTitleTextPacket(titleComponent),
                subTitlePacket = new ClientboundSetSubtitleTextPacket(subTitleComponent),
                animationPacket = new ClientboundSetTitlesAnimationPacket(fadeIn, stay, fadeOut);

        // Send the packets asynchronously
        PacketSender.sendPackets(animationPacket, titlePacket, subTitlePacket);

        return null; // No specific packet to return, as packets are sent directly
    }
}
