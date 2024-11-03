package fr.kiza.minecraftapi.module.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class GuiListenerRegistry implements Listener {

    private static final Map<String, GuiHandler> ACTIVE_GUIS = new HashMap<>();

    public static void init(final JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new GuiListenerRegistry(), plugin);
    }

    public static void register(final GuiHandler guiHandler) {
        ACTIVE_GUIS.put(guiHandler.getTitle(), guiHandler);
    }

    public static void unregister(final GuiHandler guiHandler) {
        ACTIVE_GUIS.remove(guiHandler.getTitle());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(final InventoryClickEvent event) {
        if(!(event.getWhoClicked() instanceof Player)) return;

        final GuiHandler gui = ACTIVE_GUIS.get(event.getView().getTitle());

        if(gui != null) {
            event.setCancelled(true);
            gui.handleClick(event.getSlot(), event.getClick(), (Player) event.getWhoClicked());
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClose(final InventoryCloseEvent event) {
        final String title = event.getView().getTitle();
        final GuiHandler gui = ACTIVE_GUIS.get(title);

        if(gui != null) unregister(gui);
    }
}
