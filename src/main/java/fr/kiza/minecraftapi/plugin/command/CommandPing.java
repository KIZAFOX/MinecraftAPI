package fr.kiza.minecraftapi.plugin.command;

import fr.kiza.minecraftapi.handler.commands.AbstractCommand;
import fr.kiza.minecraftapi.handler.commands.handler.CommandRegisterer;
import fr.kiza.minecraftapi.handler.commands.handler.CommandHandler;
import fr.kiza.minecraftapi.handler.packets.PacketFactory;
import fr.kiza.minecraftapi.handler.packets.PacketType;
import fr.kiza.minecraftapi.handler.packets.sender.PacketSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

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
        PacketSender.sendPacket(PacketFactory.getBuilder(PacketType.MESSAGE_PLAYER)
                .message("Pong!")
                .build()
        );

        return false;
    }
}
