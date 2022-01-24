package net.nightcodes.androidchess.game.entity;

import net.nightcodes.androidchess.game.entity.base.EntityIdentification;
import net.nightcodes.androidchess.game.entity.base.IEntity;
import net.nightcodes.androidchess.game.logic.MoveResult;
import net.nightcodes.androidchess.game.logic.movement.Location;
import net.nightcodes.androidchess.game.logic.movement.MovementPermission;

@EntityIdentification(
        name = "Bishop",
        imageAsset = "/path/to/image",
        movementPermission = {MovementPermission.DIAGONAL_RESTRICTED}
)

public class Bishop implements IEntity<Bishop> {

    @Override
    public MoveResult canMove(Location location) {
        return null;
    }

    @Override
    public Class<Bishop> getEntityType() {
        return Bishop.class;
    }

    @Override
    public String consoleIcon() {
        return "♗";
    }
}
