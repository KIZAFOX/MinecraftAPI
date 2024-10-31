package fr.kiza.minecraftapi.module.quest;

import java.util.UUID;

/**
 * Represents a quest within the Minecraft API.
 *
 * @param <T> the type of the reward given upon completion of the quest
 */
public interface IQuest<T> {

    /**
     * Gets the unique identifier for the quest.
     *
     * @return the UUID of the quest
     */
    UUID getID();

    /**
     * Gets the title of the quest.
     *
     * @return the title of the quest
     */
    String getTitle();

    /**
     * Gets the description of the quest, detailing its objectives.
     *
     * @return the description of the quest
     */
    String getDescription();

    /**
     * Gets the reward for completing the quest.
     *
     * @return the reward associated with the quest
     */
    T getReward();

    /**
     * Checks if the quest has been completed.
     *
     * @return true if the quest is completed; false otherwise
     */
    boolean isCompleted();

    /**
     * Starts the quest, setting up any necessary initial state.
     */
    void start();

    /**
     * Marks the quest as completed and handles any completion logic.
     */
    void complete();

    /**
     * Updates the progress of the quest based on current player actions or events.
     */
    void updateProgress();
}
