package net.nightcodes.androidchess.game.entity;

import net.nightcodes.androidchess.game.entity.base.EntityIdentification;
import net.nightcodes.androidchess.game.entity.base.IEntity;
import net.nightcodes.androidchess.game.logic.MoveResult;
import net.nightcodes.androidchess.game.logic.movement.Location;
import net.nightcodes.androidchess.game.logic.movement.MovementPermission;

@EntityIdentification(
        name = "Rook",
        imageAsset = "/path/to/image",
        movementPermission = {MovementPermission.STRAIGHT_UNLIMITED}
)

public class Rook implements IEntity {

    @Override
    public MoveResult canMove(Location location) {
        return null;
    }

    @Override
    public String consoleIcon() {
        return "♖";
    }
}
