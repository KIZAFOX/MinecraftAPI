package fr.kiza.minecraftapi.module.tools;

import fr.kiza.minecraftapi.module.commands.handler.CommandHandler;
import org.reflections.Reflections;

import java.util.Set;

public class ClassScanner {
    public static Set<Class<?>> getClasses(final String packageName) {
        return new Reflections(packageName).getTypesAnnotatedWith(CommandHandler.class);
    }
}
