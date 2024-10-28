package fr.kiza.minecraftapi.plugin.command;

import fr.kiza.minecraftapi.handler.commands.AbstractCommand;
import fr.kiza.minecraftapi.handler.commands.handler.CommandHandler;
import fr.kiza.minecraftapi.handler.commands.handler.CommandRegisterer;
import fr.kiza.minecraftapi.handler.packet.sender.FastPacket;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CommandRegisterer
@CommandHandler(
        name="tool",
        description = "Tool command",
        usage = "/tool",
        aliases = {"tools", "t"}
)
public class CommandTool extends AbstractCommand {

    private final Material[] TOOLS = {
            Material.IRON_SWORD,
            Material.DIAMOND_PICKAXE,
            Material.IRON_AXE,
            Material.DIAMOND_SHOVEL,
            Material.IRON_HOE,
    };

    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof final Player player)){
            sender.sendMessage(ChatColor.RED + "This command can only be executed by a player");
            return true;
        }

        if(args.length == 0){
            this.sendUsage();
            return true;
        }else if(args.length == 1){
            final String toolName = args[0].toUpperCase();

            try {
                final Material tool = Material.valueOf(toolName);

                if(!Arrays.asList(TOOLS).contains(tool)){
                    this.sendUsage();
                }else{
                    player.getInventory().addItem(new ItemStack(tool));
                    FastPacket.sendMessage(ChatColor.GREEN + "You have been given a " + tool.name().toLowerCase().replace('_', ' ') + ".");
                }
            } catch (final IllegalArgumentException e){
                FastPacket.sendMessage(ChatColor.RED + "Invalid material. Please use a valid tool name.");
                this.sendUsage();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1){
            return Arrays.stream(TOOLS)
                    .map(Material::toString)
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    private void sendUsage(){
        FastPacket.sendMessage(ChatColor.RED + "Usage: /tool <material>");
        Arrays.stream(this.TOOLS).forEach(tools -> FastPacket.sendMessage(ChatColor.GRAY + "- " + tools));
    }
}
