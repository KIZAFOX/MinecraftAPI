package fr.kiza.minecraftapi.handler.commands.handler;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandHandler {
    String name();
    String description() default "";
    String usage() default "";
    String permission() default "";
}
