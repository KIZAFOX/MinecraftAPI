package fr.kiza.minecraftapi.module.gui;

import fr.kiza.minecraftapi.module.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

import java.util.List;

public abstract class GuiItem {

    public abstract Material getMaterial();
    public abstract int getAmount();
    public abstract String getName();
    public abstract List<String> getDescription();

    public void setItem(final Inventory inventory, int slot) {
        inventory.setItem(
                slot,
                new ItemBuilder(this.getMaterial(), this.getAmount())
                        .setName(this.getName())
                        .setLore(getDescription())
                        .toItemStack()
        );
    }

    public abstract void onClick(final Player player, final ClickType clickType);
}
