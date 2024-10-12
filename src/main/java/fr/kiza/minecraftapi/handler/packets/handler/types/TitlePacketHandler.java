package fr.kiza.minecraftapi.handler.packets.handler.types;

import fr.kiza.minecraftapi.handler.packets.handler.PacketBuilder;
import fr.kiza.minecraftapi.handler.packets.sender.PacketSender;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;

public class TitlePacketHandler implements PacketBuilder<ClientboundSetTitleTextPacket> {

    protected String title, subTitle;
    protected int fadeIn, stay, fadeOut;

    @Override
    public PacketBuilder<ClientboundSetTitleTextPacket> title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public PacketBuilder<ClientboundSetTitleTextPacket> subTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    @Override
    public PacketBuilder<ClientboundSetTitleTextPacket> fadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
        return this;
    }

    @Override
    public PacketBuilder<ClientboundSetTitleTextPacket> stay(int stay) {
        this.stay = stay;
        return this;
    }

    @Override
    public PacketBuilder<ClientboundSetTitleTextPacket> fadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
        return this;
    }

    @Override
    public Packet<?> build() {
        final IChatBaseComponent
                titleComponent = IChatBaseComponent.a(this.title),
                subTitleComponent = IChatBaseComponent.a(this.subTitle);

        final Packet<?>
                titlePacket = new ClientboundSetTitleTextPacket(titleComponent),
                subTitlePacket = new ClientboundSetSubtitleTextPacket(subTitleComponent),
                animationPacket = new ClientboundSetTitlesAnimationPacket(fadeIn, stay, fadeOut);

        PacketSender.sendPacket(animationPacket);
        PacketSender.sendPacket(titlePacket);
        PacketSender.sendPacket(subTitlePacket);

        return null;
    }
}
