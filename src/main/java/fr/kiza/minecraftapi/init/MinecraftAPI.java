package fr.kiza.minecraftapi.init;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark classes as part of the Minecraft API.
 * This can be used for automatic detection and initialization of API components.
 */
@Target(ElementType.TYPE) // This annotation can only be applied to types (classes).
@Retention(RetentionPolicy.RUNTIME) // The annotation will be available at runtime.
public @interface MinecraftAPI {
    // Future parameters can be added here if needed, e.g., version or module name.
}
