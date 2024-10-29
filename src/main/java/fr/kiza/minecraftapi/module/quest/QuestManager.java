package fr.kiza.minecraftapi.module.quest;

import java.util.ArrayList;
import java.util.List;

public class QuestManager {

    private static final List<IQuest<?>> QUESTS = new ArrayList<>();

    public static void addQuest(final IQuest<?> quest) {
        QUESTS.add(quest);
    }

    public static void removeQuest(final IQuest<?> quest) {
        QUESTS.remove(quest);
    }

    public static IQuest<?> getQuest(final String name) {
        for (final IQuest<?> quest : QUESTS) {
            return quest;
        }
        return null;
    }

    public static void start(final IQuest<?> quest){
        if(!QUESTS.contains(quest)){
            addQuest(quest);
        }
        quest.start();
    }

    public static void completeQuest(final IQuest<?> quest) {
        quest.complete();
        removeQuest(quest);
    }

    public static void updateQuestProgress(IQuest<?> quest) {
        quest.updateProgress();
    }

    public static List<IQuest<?>> getActiveQuests() {
        final List<IQuest<?>> activeQuests = new ArrayList<>();
        for (final IQuest<?> quest : QUESTS) {
            if(!quest.isCompleted()) {
                activeQuests.add(quest);
            }
        }
        return activeQuests;
    }

    public static List<IQuest<?>> getAllQuests() {
        return QUESTS;
    }
}
