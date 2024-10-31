package fr.kiza.minecraftapi.module.player;

import org.bukkit.event.Event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark methods as player event listeners in the Minecraft API.
 * <p>
 * Methods annotated with this will be treated as listeners for the specified event type.
 */
@Target({ElementType.METHOD}) // Indicates that this annotation can be applied to methods.
@Retention(RetentionPolicy.RUNTIME) // The annotation will be retained at runtime for reflection.
public @interface PlayerListener {

    /**
     * Specifies the type of event that the annotated method will listen to.
     *
     * @return the class of the event to listen for
     */
    Class<? extends Event> value(); // The event class that this listener responds to.
}
