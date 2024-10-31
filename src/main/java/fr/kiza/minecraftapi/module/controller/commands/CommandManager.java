package fr.kiza.minecraftapi.module.controller.commands;

import fr.kiza.minecraftapi.module.controller.commands.handler.CommandHandler;
import fr.kiza.minecraftapi.module.controller.commands.handler.CommandRegisterer;
import fr.kiza.minecraftapi.module.tools.logger.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.SimplePluginManager;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Manages the registration and handling of commands in the plugin.
 */
public class CommandManager {

    /**
     * Registers commands found in the specified package.
     *
     * @param packageName The package name to search for command handlers.
     */
    public void registerCommands(final String packageName) {
        final Set<Class<?>> classes = new Reflections(packageName).getTypesAnnotatedWith(CommandHandler.class);

        classes.forEach(clazz -> {
            if (!clazz.isAnnotationPresent(CommandHandler.class)) {
                Logger.print("Class " + clazz.getName() + " does not contain @CommandHandler!", Logger.LoggerLevel.ERROR);
                return;
            }

            CommandHandler commandHandler = clazz.getAnnotation(CommandHandler.class);
            if (!clazz.isAnnotationPresent(CommandRegisterer.class)) {
                Logger.print("Class " + clazz.getName() + " does not contain @CommandRegisterer!", Logger.LoggerLevel.ERROR);
                return;
            }

            try {
                AbstractCommand executor = (AbstractCommand) clazz.getDeclaredConstructor().newInstance();
                registerDynamicCommand(commandHandler.name(), commandHandler.description(), commandHandler.usage(),
                        commandHandler.aliases(), commandHandler.permission(), executor);
            } catch (Exception e) {
                Logger.print("Error registering command " + commandHandler.name() + ": " + e.getMessage(), Logger.LoggerLevel.ERROR);
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Registers a dynamic command in Bukkit.
     *
     * @param commandName   The name of the command.
     * @param description   The description of the command.
     * @param usage         The usage of the command.
     * @param aliases       The aliases of the command.
     * @param permission    The permission required to execute the command.
     * @param executor      The command executor.
     */
    private void registerDynamicCommand(final String commandName, final String description, final String usage,
                                        final String[] aliases, final String permission, final AbstractCommand executor) {
        BukkitCommand dynamicCommand = new BukkitCommand(commandName) {
            @Override
            public boolean execute(final CommandSender sender, final String label, final String[] args) {
                return executor.execute(sender, this, label, args);
            }

            @Override
            public List<String> tabComplete(final CommandSender sender, final String alias, final String[] args) {
                return executor.tabComplete(sender, this, alias, args);
            }
        };

        dynamicCommand.setDescription(description);
        dynamicCommand.setUsage("/" + usage);

        Optional.ofNullable(aliases).ifPresent(a -> {
            if (a.length > 0) {
                dynamicCommand.setAliases(Arrays.asList(a));
            }
        });

        Optional.ofNullable(permission).filter(p -> !p.isEmpty()).ifPresent(dynamicCommand::setPermission);

        try {
            CommandMap commandMap = getCommandMap();
            if (commandMap != null) {
                commandMap.register(commandName, dynamicCommand);
            } else {
                Logger.print("CommandMap is null! Command " + commandName + " could not be registered.", Logger.LoggerLevel.ERROR);
            }
        } catch (Exception e) {
            Logger.print("Error registering command " + commandName + ": " + e.getMessage(), Logger.LoggerLevel.ERROR);
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the CommandMap for the Bukkit plugin manager.
     *
     * @return The CommandMap instance.
     * @throws Exception If unable to access the CommandMap.
     */
    private CommandMap getCommandMap() throws Exception {
        if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
            Field commandMapField = SimplePluginManager.class.getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            return (CommandMap) commandMapField.get(Bukkit.getPluginManager());
        }
        return null;
    }
}
