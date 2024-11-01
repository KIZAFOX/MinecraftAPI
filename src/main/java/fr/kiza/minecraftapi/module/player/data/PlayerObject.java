package fr.kiza.minecraftapi.module.player.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public record PlayerObject(UUID uuid) {

    public static final Map<UUID, PlayerObject> PLAYER = new HashMap<>();

}
