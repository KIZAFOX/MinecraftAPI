package fr.kiza.minecraftapi.module.gui.models;

import fr.kiza.minecraftapi.core.Core;
import fr.kiza.minecraftapi.module.gui.GuiHandler;
import fr.kiza.minecraftapi.module.gui.GuiItem;
import fr.kiza.minecraftapi.module.gui.custom.CustomGuiItem;
import fr.kiza.minecraftapi.module.item.ItemBuilder;
import fr.kiza.minecraftapi.module.tools.glass.GlassColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class CaseModel extends GuiHandler implements Listener {

    private final List<CustomGuiItem> centerItems;
    private final List<Integer> animatedSlots;

    private BukkitTask glassTask, itemTask;
    private int centerItemIndex = 0;
    private boolean isSlowingDown = false, finalItemGiven = false;

    public GuiItem finalItem = null;

    public CaseModel(final String title, final List<CustomGuiItem> centerItems) {
        super(3, title);

        this.centerItems = centerItems;
        this.animatedSlots = this.getAnimatedSlots();

        Bukkit.getPluginManager().registerEvents(this, Core.getInstance().getPlugin());
    }

    @Override
    public void setupItems() {
        this.startGlassAnimation();
        this.startCenterItemAnimation();
    }

    @Override
    public void handleClick(int slot, ClickType clickType, Player player) {
        final int centerSlot = ((this.rows / 2) * 9) + 4;
        if (slot == centerSlot && this.finalItem != null) {
            giveFinalItemToPlayer(player);
            player.closeInventory();
        }
    }

    public abstract String successMessage();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClose(final InventoryCloseEvent event) {
        final Player player = (Player) event.getPlayer();

        if (event.getInventory().equals(this.inventory) && !this.finalItemGiven) {
            cancelTasks();
            if (this.finalItem == null) {
                selectFinalItemByDropRate();
            }
            this.giveFinalItemToPlayer(player);
        }
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        if (event.getInventory().equals(this.inventory)) {
            event.setCancelled(true);
            if (this.glassTask.isCancelled() && this.itemTask.isCancelled() && this.finalItem != null) {
                final Player player = (Player) event.getWhoClicked();

                this.giveFinalItemToPlayer(player);
                player.closeInventory();
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        if (event.getPlayer().getOpenInventory().getTopInventory().equals(this.inventory)) {
            this.cancelTasks();
        }
    }

    private void cancelTasks() {
        if (this.glassTask != null && !this.glassTask.isCancelled()) this.glassTask.cancel();
        if (this.itemTask != null && !this.itemTask.isCancelled()) this.itemTask.cancel();
    }

    private List<Integer> getAnimatedSlots() {
        final List<Integer> slots = new ArrayList<>();
        final int centerRowStart = (this.rows / 2) * 9;

        for (int i = centerRowStart - 9; i < centerRowStart + 18; i++) {
            if (i >= 0 && i < this.rows * 9 && (i < centerRowStart || i >= centerRowStart + 9)) {
                slots.add(i);
            }
        }
        return slots;
    }

    private void startGlassAnimation() {
        this.glassTask = new BukkitRunnable() {
            @Override
            public void run() {
                animatedSlots.forEach(slot -> inventory.setItem(slot,
                        new ItemBuilder(GlassColor.glassColors[new Random().nextInt(GlassColor.glassColors.length)], 1).setName(" ").toItemStack()));
            }
        }.runTaskTimer(Core.getInstance().getPlugin(), 0L, 4L);
    }

    private void startCenterItemAnimation() {
        this.itemTask = new BukkitRunnable() {
            private int delay = 5;

            @Override
            public void run() {
                int centerSlot = ((rows / 2) * 9) + 4;
                addItem(centerSlot, centerItems.get(centerItemIndex));

                centerItemIndex = (centerItemIndex + 1) % centerItems.size();

                if (isSlowingDown) {
                    delay += 2;
                    if (delay >= 30) {
                        cancel();
                        selectFinalItemByDropRate(); // Sélectionne l'item final avec le taux de drop
                        addItem(centerSlot, finalItem);
                    }
                } else if (centerItemIndex == centerItems.size() - 1) {
                    isSlowingDown = true;
                }
            }
        }.runTaskTimer(Core.getInstance().getPlugin(), 0L, 5L);
    }

    private void selectFinalItemByDropRate() {
        double randomValue = new Random().nextDouble() * 100;
        double cumulativeRate = 0.0;

        for (CustomGuiItem item : centerItems) {
            cumulativeRate += item.getDropRate();
            if (randomValue <= cumulativeRate) {
                this.finalItem = item;
                break;
            }
        }
    }

    private void giveFinalItemToPlayer(Player player) {
        if (!this.finalItemGiven && this.finalItem != null) {
            player.getInventory().addItem(new ItemBuilder(this.finalItem.getMaterial(), 1)
                    .setName(this.finalItem.getName())
                    .setLore(this.finalItem.getDescription())
                    .toItemStack());

            this.finalItemGiven = true;

            player.sendMessage(this.successMessage());
        }
    }
}
