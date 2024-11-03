package fr.kiza.minecraftapi.module.gui;

import fr.kiza.minecraftapi.module.exception.GuiException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public abstract class GuiHandler {

    protected final int rows;
    protected final String title;
    protected final Inventory inventory;

    protected final Map<Integer, GuiItem> items = new HashMap<>();

    public GuiHandler(final int rows, final String title) {
        this.rows = rows;
        this.title = title;

        this.inventory = Bukkit.createInventory(null, rows * 9, title);
    }

    public void open(final Player player) throws GuiException {
        this.setupItems();
        player.openInventory(this.inventory);
        GuiListenerRegistry.register(this);
    }

    public abstract void setupItems();

    public void addItem(final int slot, final GuiItem item) {
        this.items.put(slot, item);
        item.setItem(this.inventory, slot);
    }

    public void handleClick(final int slot, final ClickType clickType, final Player player) {
        final GuiItem item = this.items.get(slot);
        if (item != null) item.onClick(player, clickType);
    }

    public String getTitle() {
        return title;
    }

    public int getRows() {
        return rows;
    }
}
