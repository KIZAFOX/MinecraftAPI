package fr.kiza.minecraftapi.plugin.command;

import fr.kiza.minecraftapi.handler.commands.AbstractCommand;
import fr.kiza.minecraftapi.handler.commands.handler.CommandHandler;
import fr.kiza.minecraftapi.handler.packets.PacketFactory;
import fr.kiza.minecraftapi.handler.packets.PacketType;
import fr.kiza.minecraftapi.handler.packets.sender.PacketSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandHandler(
        name="ping",
        description = "This is a ping command"
)
public class CommandTest extends AbstractCommand {
    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {
        this.playerOnly(sender);

        PacketSender.sendPacket(PacketFactory.getBuilder(PacketType.MESSAGE_PLAYER)
                .message("Pong!")
                .build()
        );

        return false;
    }
}
