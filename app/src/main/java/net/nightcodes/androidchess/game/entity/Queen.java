package net.nightcodes.androidchess.game.entity;

import androidx.annotation.NonNull;

import net.nightcodes.androidchess.game.entity.base.EntityIdentification;
import net.nightcodes.androidchess.game.entity.base.IEntity;
import net.nightcodes.androidchess.game.logic.MoveResult;
import net.nightcodes.androidchess.game.logic.movement.Location;
import net.nightcodes.androidchess.game.logic.movement.MovementPermission;

@EntityIdentification(
        name = "Queen",
        imageAsset = "/path/to/image",
        movementPermission = {MovementPermission.DIAGONAL_UNLIMITED, MovementPermission.STRAIGHT_UNLIMITED}
)

public class Queen implements IEntity<Queen> {

    @Override
    public MoveResult canMove(Location location) {
        return null;
    }

    @Override
    public Class<Queen> getEntityType() {
        return Queen.class;
    }

    @Override
    public String consoleIcon() {
        return "♕";
    }
}
