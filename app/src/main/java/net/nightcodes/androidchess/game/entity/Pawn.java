package net.nightcodes.androidchess.game.entity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import net.nightcodes.androidchess.game.entity.base.EntityIdentification;
import net.nightcodes.androidchess.game.entity.base.IEntity;
import net.nightcodes.androidchess.game.entity.base.ImageAssetType;
import net.nightcodes.androidchess.game.logic.MoveResult;
import net.nightcodes.androidchess.game.logic.movement.Location;
import net.nightcodes.androidchess.game.logic.movement.MovementPermission;

import java.util.Map;

@EntityIdentification(
        name = "Pawn",
        imageAsset = "/path/to/image",
        movementPermission = {MovementPermission.STRAIGHT_RESTRICTED}
)

public class Pawn implements IEntity<Pawn> {

    @Override
    public MoveResult canMove(Location location) {
        return null;
    }

    @Override
    public Class<Pawn> getEntityType() {
        return null;
    }

    @Override
    public String consoleIcon() {
        return "♙";
    }

    @Override
    public void setDrawables(Resources resources) {

    }

    @Override
    public Map<ImageAssetType, Drawable.ConstantState> getDrawables() {
        return null;
    }
}
