package fr.kiza.minecraftapi.module.quest;

import java.util.UUID;

public interface IQuest<T> {

    UUID getID();

    String getTitle();

    String getDescription();

    T getReward();

    boolean isCompleted();

    void start();

    void complete();

    void updateProgress();

}
