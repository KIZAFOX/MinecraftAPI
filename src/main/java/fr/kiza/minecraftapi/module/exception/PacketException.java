package fr.kiza.minecraftapi.module.exception;

public class PacketException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public PacketException() {
        super("Packet Exception: An error occurred with the packet system.");
    }
}
