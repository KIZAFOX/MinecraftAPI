package fr.kiza.minecraftapi.handler.commands;

import fr.kiza.minecraftapi.handler.commands.handler.CommandHandler;
import fr.kiza.minecraftapi.handler.tools.ClassScanner;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;
import java.util.Set;

public class CommandManager {
    public void registerCommands(final String packageName) {
        final Set<Class<?>> classes = ClassScanner.getClasses(packageName);

        classes.forEach(clazz -> {
            if (clazz.isAnnotationPresent(CommandHandler.class)){
                final CommandHandler commandHandler = clazz.getAnnotation(CommandHandler.class);

                try{
                    final AbstractCommand commandInstance = (AbstractCommand) clazz.getDeclaredConstructor().newInstance();

                    registerDynamicCommand(commandHandler.name(), commandHandler.description(), commandInstance);
                }catch(final Exception e){
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void registerDynamicCommand(final String commandName, final String description, final AbstractCommand executor) {
        BukkitCommand dynamicCommand = new BukkitCommand(commandName) {
            @Override
            public boolean execute(final CommandSender sender, final String label, final String[] args) {
                return executor.execute(sender, this, label, args);
            }
        };

        dynamicCommand.setDescription(description);

        try{
            final CommandMap commandMap = getCommandMap();

            if(commandMap != null){
                commandMap.register(commandName, dynamicCommand);
            }
        }catch (final Exception e){
            throw new RuntimeException(e);
        }
    }

    private CommandMap getCommandMap() throws Exception {
        if(Bukkit.getPluginManager() instanceof SimplePluginManager){
            final Field commandMapField = SimplePluginManager.class.getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            return (CommandMap) commandMapField.get(Bukkit.getPluginManager());
        }
        return null;
    }
}
