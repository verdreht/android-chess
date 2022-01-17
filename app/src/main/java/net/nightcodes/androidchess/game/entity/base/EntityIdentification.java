package net.nightcodes.androidchess.game.entity.base;

import net.nightcodes.androidchess.game.logic.movement.MovementPermission;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EntityIdentification {
    String name();
    String imageAsset();
    MovementPermission[] movementPermission();
}
