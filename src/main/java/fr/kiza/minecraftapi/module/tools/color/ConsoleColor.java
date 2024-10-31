package fr.kiza.minecraftapi.module.tools.color;

/**
 * Enum representing console color codes for text and background.
 * This enum provides ANSI escape codes to colorize console output in a terminal.
 */
public enum ConsoleColor {
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),

    BACKGROUND_BLACK("\u001B[40m"),
    BACKGROUND_RED("\u001B[41m"),
    BACKGROUND_GREEN("\u001B[42m"),
    BACKGROUND_YELLOW("\u001B[43m"),
    BACKGROUND_BLUE("\u001B[44m"),
    BACKGROUND_PURPLE("\u001B[45m"),
    BACKGROUND_CYAN("\u001B[46m"),
    BACKGROUND_WHITE("\u001B[47m");

    private final String code; // ANSI escape code for the color.

    /**
     * Constructs a ConsoleColor enum with the specified ANSI code.
     *
     * @param code The ANSI escape code associated with this color.
     */
    ConsoleColor(String code) {
        this.code = code;
    }

    /**
     * Returns the ANSI escape code as a string.
     *
     * @return The ANSI escape code for this color.
     */
    @Override
    public String toString() {
        return code;
    }
}
