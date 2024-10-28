package fr.kiza.minecraftapi.plugin.command;

import fr.kiza.minecraftapi.handler.commands.AbstractCommand;
import fr.kiza.minecraftapi.handler.commands.handler.CommandRegisterer;
import fr.kiza.minecraftapi.handler.commands.handler.CommandHandler;
import fr.kiza.minecraftapi.handler.packet.PacketFactory;
import fr.kiza.minecraftapi.handler.packet.PacketType;
import fr.kiza.minecraftapi.handler.packet.sender.PacketSender;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

@CommandRegisterer
@CommandHandler(
        name="ping",
        description = "This is a ping command",
        usage = "/ping",
        aliases = {"p", "pong"}
)
public class CommandPing extends AbstractCommand {
    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof final Player player)){
            sender.sendMessage(ChatColor.RED + "This command can only be executed by a player");
            return true;
        }

        PacketSender.sendPacket(PacketFactory.getBuilder(PacketType.MESSAGE_PLAYER)
                .message("Pong! " + player.getName())
                .build()
        );
        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String label, String[] args) {
        return List.of();
    }
}
