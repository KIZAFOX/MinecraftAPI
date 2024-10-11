package fr.kiza.minecraftapi.plugin;

import fr.kiza.minecraftapi.core.Core;
import fr.kiza.minecraftapi.handler.player.PlayerListener;
import fr.kiza.minecraftapi.handler.tools.Logger;
import fr.kiza.minecraftapi.init.APIInitializer;
import fr.kiza.minecraftapi.init.MinecraftAPI;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

@MinecraftAPI
public final class MinecraftAPITest extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        APIInitializer.init(this);

        Core.getInstance().getEventDispatcher().registerEvent(PlayerJoinEvent.class, this);
        this.getServer().getPluginManager().registerEvents(Core.getInstance().getEventDispatcher(), this);

        Logger.print("THIS IS A @DEBUG MESSAGE FROM LOGGER", Logger.LoggerLevel.DEBUG);
        Logger.print("THIS IS AN @INFO MESSAGE FROM LOGGER", Logger.LoggerLevel.INFO);
        Logger.print("THIS IS A @WARN MESSAGE FROM LOGGER", Logger.LoggerLevel.WARN);
        Logger.print("THIS IS AN @ERROR MESSAGE FROM LOGGER", Logger.LoggerLevel.ERROR);
        Logger.print("THIS IS A @FATAL MESSAGE FROM LOGGER", Logger.LoggerLevel.FATAL);
        Logger.print("THIS IS A @TRACE MESSAGE FROM LOGGER", Logger.LoggerLevel.TRACE);
    }

    @PlayerListener(PlayerJoinEvent.class)
    public void onLogin(final PlayerJoinEvent event){
        Core.getInstance().getPlayerHandler().performAction(((player, packetHandler) -> {
            packetHandler.getMessageBuilder().build(ChatColor.YELLOW + "Welcome to my server " + ChatColor.AQUA + player.getName() + ChatColor.YELLOW + "!");
        }), event);
    }
}
