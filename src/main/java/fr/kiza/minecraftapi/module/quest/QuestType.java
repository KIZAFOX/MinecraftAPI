package fr.kiza.minecraftapi.module.quest;

/**
 * Represents the various types of quests available in the game.
 */
public enum QuestType {
    /** A quest where the objective is to collect specific items. */
    COLLECT,

    /** A quest where the objective is to kill specific entities. */
    KILL,

    /** A quest where the objective is to explore certain areas. */
    EXPLORE;

    /**
     * Returns a user-friendly string representation of the quest type.
     *
     * @return The name of the quest type in a readable format.
     */
    @Override
    public String toString() {
        return name().toLowerCase(); // Converts enum name to lowercase for easier readability
    }
}
