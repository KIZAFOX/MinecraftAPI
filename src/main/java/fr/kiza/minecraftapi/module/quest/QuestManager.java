package fr.kiza.minecraftapi.module.quest;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages quests for players in the game.
 * This class handles the addition, removal, and tracking of quests for individual players.
 */
public class QuestManager {

    // A list of all quests available in the game
    private static final List<IQuest<?>> QUESTS = new ArrayList<>();

    // A mapping of players to their active quests
    private static final Map<Player, List<IQuest<?>>> playerQuests = new HashMap<>();

    /**
     * Adds a quest to the global quest list.
     *
     * @param quest The quest to be added.
     */
    public static void addQuest(final IQuest<?> quest) {
        QUESTS.add(quest);
    }

    /**
     * Removes a quest from the global quest list.
     *
     * @param quest The quest to be removed.
     */
    public static void removeQuest(final IQuest<?> quest) {
        QUESTS.remove(quest);
    }

    /**
     * Retrieves a quest by its name.
     *
     * @param name The name of the quest.
     * @return The quest if found, otherwise null.
     */
    public static IQuest<?> getQuest(final String name) {
        for (final IQuest<?> quest : QUESTS) {
            if (quest.getTitle().equals(name)) {
                return quest;
            }
        }
        return null;
    }

    /**
     * Starts a quest for the specified player.
     * If the player does not already have the quest, it adds it to their quest list.
     *
     * @param player The player who is starting the quest.
     * @param quest  The quest to be started.
     */
    public static void start(final Player player, final IQuest<?> quest) {
        // Get the player's quest list, create if not exists
        List<IQuest<?>> playerQuestList = playerQuests.computeIfAbsent(player, p -> new ArrayList<>());

        if (!playerQuestList.contains(quest)) {
            playerQuestList.add(quest);
        }
        quest.start();
    }

    /**
     * Completes a quest for the specified player.
     * Removes the quest from the player's quest list upon completion.
     *
     * @param player The player who is completing the quest.
     * @param quest  The quest to be completed.
     */
    public static void completeQuest(final Player player, final IQuest<?> quest) {
        quest.complete();
        removeQuestFromPlayer(player, quest);
    }

    /**
     * Updates the progress of a quest for a specified player.
     *
     * @param player The player whose quest progress is to be updated.
     * @param quest  The quest for which progress is to be updated.
     */
    public static void updateQuestProgress(Player player, IQuest<?> quest) {
        if (playerQuests.containsKey(player) && playerQuests.get(player).contains(quest)) {
            quest.updateProgress();
        }
    }

    /**
     * Retrieves a list of active quests for a specific player.
     *
     * @param player The player whose active quests are to be retrieved.
     * @return A list of active quests for the specified player.
     */
    public static List<IQuest<?>> getActiveQuests(Player player) {
        return playerQuests.getOrDefault(player, new ArrayList<>());
    }

    /**
     * Retrieves a list of all quests available in the game.
     *
     * @return A list of all quests.
     */
    public static List<IQuest<?>> getAllQuests() {
        return QUESTS;
    }

    /**
     * Removes a quest from a player's quest list.
     *
     * @param player The player whose quest is to be removed.
     * @param quest  The quest to be removed from the player's list.
     */
    private static void removeQuestFromPlayer(Player player, IQuest<?> quest) {
        List<IQuest<?>> playerQuestList = playerQuests.get(player);
        if (playerQuestList != null) {
            playerQuestList.remove(quest);
            // Remove the player's quest list if it's empty to clean up memory
            if (playerQuestList.isEmpty()) {
                playerQuests.remove(player);
            }
        }
    }
}
