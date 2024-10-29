package fr.kiza.test.minecraftapi;

import fr.kiza.minecraftapi.module.quest.AbstractQuest;
import fr.kiza.minecraftapi.module.quest.IQuest;
import fr.kiza.minecraftapi.module.quest.QuestManager;

public final class MinecraftAPITest{
    public static void main(String[] args) {
        final IQuest<Integer> firstQuest = new CollectQuest("Diamond x10!", "Collect 10 diamonds.", 10, 100);
        final IQuest<Integer> second = new CollectQuest("Diamond x20!", "Collect 10 diamonds.", 20, 100);
        final IQuest<Integer> thirdQuest = new CollectQuest("Diamond x15!", "Collect 10 diamonds.", 15, 100);

        QuestManager.getAllQuests().forEach(quest -> System.out.println("- " + quest.getID()));

        QuestManager.start(firstQuest);
        QuestManager.start(second);
        QuestManager.start(thirdQuest);

        QuestManager.getActiveQuests().forEach(quest -> System.out.println("- " + quest.getID()));

        for(int i = 0; i < 10; i++){
            firstQuest.updateProgress();
        }
    }

    static class CollectQuest extends AbstractQuest<Integer> {
        private final int itemsToCollect;
        private int itemsCollected;

        public CollectQuest(String title, String description, int itemsToCollect, Integer reward) {
            super(title, description, reward);
            this.itemsToCollect = itemsToCollect;
            this.itemsCollected = 0;
        }

        @Override
        public void start() {
            System.out.println("Starting collect quest!");
        }

        @Override
        public void complete() {
            this.setComplete(true);
            System.out.println("Collected " + itemsToCollect + " diamonds.");
            System.out.println("Quest completed!");
        }

        @Override
        public void updateProgress() {
            itemsCollected++;
            System.out.println("Diamond collected: " + itemsCollected + "/" + itemsToCollect);
            if (itemsCollected >= itemsToCollect) {
                complete();
            }
        }
    }
}