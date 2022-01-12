package net.nightcodes.androidchess.game.entity;

import net.nightcodes.androidchess.game.logic.movement.MovementPermission;

public @interface EntityIdentification {
    int id();
    String name();
    String imageAsset();
    MovementPermission[] movementPermission();
}
