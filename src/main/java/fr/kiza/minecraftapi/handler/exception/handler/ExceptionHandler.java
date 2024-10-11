package fr.kiza.minecraftapi.handler.exception.handler;

import fr.kiza.minecraftapi.core.Core;

public class ExceptionHandler extends Exception {
    public ExceptionHandler(String message) {
        super(Core.getInstance().PREFIX + message);
    }
}
