package net.nightcodes.androidchess.game.entity.base;

import net.nightcodes.androidchess.game.logic.movement.MovementPermission;

public @interface EntityIdentification {
    String name();
    String imageAsset();
    MovementPermission[] movementPermission();
}
