package fr.kiza.minecraftapi.module.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public interface IGui {
    void onClick(final int slot, final ClickType clickType, final Player player);
}
