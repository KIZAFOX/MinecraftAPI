package fr.kiza.minecraftapi.handler.packets.handler;

import fr.kiza.minecraftapi.handler.packets.MessageBuilder;

public class PacketHandler {

    protected final MessageBuilder messageBuilder;

    public PacketHandler() {
        this.messageBuilder = new MessageBuilder(this);
    }

    public MessageBuilder getMessageBuilder() {
        return messageBuilder;
    }
}
