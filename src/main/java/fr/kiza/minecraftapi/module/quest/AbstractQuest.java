package fr.kiza.minecraftapi.module.quest;

import java.util.UUID;

public abstract class AbstractQuest<T> implements IQuest<T> {

    protected final UUID id;
    protected final String title, description;
    protected final T reward;

    private boolean isComplete;

    public AbstractQuest(String title, String description, T reward) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.reward = reward;
        this.isComplete = false;

        QuestManager.addQuest(this);
    }

    @Override
    public UUID getID() {
        return this.id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public T getReward() {
        return this.reward;
    }

    @Override
    public boolean isCompleted() {
        return this.isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    @Override
    public abstract void start();

    @Override
    public abstract void complete();

    @Override
    public abstract void updateProgress();

}
