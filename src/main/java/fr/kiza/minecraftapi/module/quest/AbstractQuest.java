package fr.kiza.minecraftapi.module.quest;

import java.util.UUID;

/**
 * An abstract base class for quests in the Minecraft API.
 *
 * @param <T> the type of the reward given upon completion of the quest
 */
public abstract class AbstractQuest<T> implements IQuest<T> {

    protected final UUID id;              // Unique identifier for the quest
    protected final String title;          // Title of the quest
    protected final String description;    // Description of the quest
    protected final T reward;              // Reward for completing the quest

    private boolean isComplete;            // Indicates if the quest is completed

    /**
     * Constructs a new quest with the specified title, description, and reward.
     *
     * @param title the title of the quest
     * @param description the description of the quest
     * @param reward the reward for completing the quest
     */
    public AbstractQuest(String title, String description, T reward) {
        this.id = UUID.randomUUID();       // Generate a unique ID for the quest
        this.title = title;                // Set the quest title
        this.description = description;    // Set the quest description
        this.reward = reward;              // Set the quest reward
        this.isComplete = false;           // Initialize as not completed

        QuestManager.addQuest(this);       // Register the quest in the QuestManager
    }

    @Override
    public UUID getID() {
        return this.id;                    // Return the quest ID
    }

    @Override
    public String getTitle() {
        return this.title;                 // Return the quest title
    }

    @Override
    public String getDescription() {
        return this.description;           // Return the quest description
    }

    @Override
    public T getReward() {
        return this.reward;                // Return the quest reward
    }

    @Override
    public boolean isCompleted() {
        return this.isComplete;            // Return the completion status of the quest
    }

    /**
     * Sets the completion status of the quest.
     *
     * @param complete true if the quest is completed; false otherwise
     */
    public void setComplete(boolean complete) {
        isComplete = complete;             // Update the completion status
    }

    @Override
    public abstract void start();         // Abstract method to start the quest

    @Override
    public abstract void complete();      // Abstract method to complete the quest

    @Override
    public abstract void updateProgress(); // Abstract method to update quest progress
}
