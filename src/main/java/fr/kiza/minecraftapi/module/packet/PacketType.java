package fr.kiza.minecraftapi.module.packet;

/**
 * Represents the various types of packets that can be handled by the Minecraft API.
 */
public enum PacketType {

    /**
     * A packet type for sending messages directly to a player.
     */
    MESSAGE_PLAYER,

    /**
     * A packet type for displaying titles (big messages) to a player.
     */
    TITLE,

    /**
     * A packet type for sending action bar messages to a player.
     */
    ACTION_BAR,

    /**
     * A packet type for getting player's IP Address.
     */
    IP_ADDRESS_PLAYER
}
