package fr.kiza.minecraftapi.handler.commands;

import fr.kiza.minecraftapi.handler.commands.handler.CommandHandler;
import fr.kiza.minecraftapi.handler.commands.handler.CommandRegisterer;
import fr.kiza.minecraftapi.handler.tools.ClassScanner;
import fr.kiza.minecraftapi.handler.tools.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;

public class CommandManager {
    public void registerCommands(final String packageName) {
        final Set<Class<?>> classes = ClassScanner.getClasses(packageName);

        classes.forEach(clazz -> {
            if (clazz.isAnnotationPresent(CommandHandler.class)) {
                final CommandHandler commandHandler = clazz.getAnnotation(CommandHandler.class);

                try{
                    if(clazz.isAnnotationPresent(CommandRegisterer.class)){
                        this.registerDynamicCommand(commandHandler.name(), commandHandler.description(), commandHandler.usage(), commandHandler.aliases(), commandHandler.permission(), (AbstractCommand) clazz.getDeclaredConstructor().newInstance());
                    }else{
                        Logger.print("This class does not contain @CommandRegisterer!", Logger.LoggerLevel.ERROR);
                    }
                }catch(final Exception e){
                    throw new RuntimeException(e);
                }
            }else{
                Logger.print("This class does not contain @CommandHandler!", Logger.LoggerLevel.ERROR);
            }
        });
    }

    private void registerDynamicCommand(final String commandName, final String description, final String usage, final String[] aliases, final String permission, final AbstractCommand executor) {
        BukkitCommand dynamicCommand = new BukkitCommand(commandName) {
            @Override
            public boolean execute(final CommandSender sender, String label, String[] args) {
                return executor.execute(sender, this, label, args);
            }
        };

        dynamicCommand.setDescription(description);
        dynamicCommand.setUsage("/" + usage);

        if(aliases != null && aliases.length > 0){
            dynamicCommand.setAliases(Arrays.asList(aliases));
        }

        if(permission != null && !permission.isEmpty()){
            dynamicCommand.setPermission(permission);
        }

        try{
            final CommandMap commandMap = getCommandMap();

            if(commandMap != null){
                commandMap.register(commandName, dynamicCommand);
            }
        }catch(final Exception e){
            throw new RuntimeException(e);
        }
    }

    private CommandMap getCommandMap() throws Exception {
        if(Bukkit.getPluginManager() instanceof SimplePluginManager) {
            final Field commandMapField = SimplePluginManager.class.getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            return (CommandMap) commandMapField.get(Bukkit.getPluginManager());
        }
        return null;
    }
}