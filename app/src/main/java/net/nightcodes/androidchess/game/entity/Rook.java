package net.nightcodes.androidchess.game.entity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import net.nightcodes.androidchess.R;
import net.nightcodes.androidchess.game.entity.base.EntityIdentification;
import net.nightcodes.androidchess.game.entity.base.IEntity;
import net.nightcodes.androidchess.game.entity.base.ImageAssetType;
import net.nightcodes.androidchess.game.logic.MoveResult;
import net.nightcodes.androidchess.game.logic.movement.Location;
import net.nightcodes.androidchess.game.logic.movement.MovementPermission;

import java.util.HashMap;
import java.util.Map;

@EntityIdentification(
        name = "Rook",
        imageAsset = "/path/to/image",
        movementPermission = {MovementPermission.STRAIGHT_UNLIMITED}
)

public class Rook implements IEntity<Rook> {
    private Map<ImageAssetType, Drawable.ConstantState> drawables = new HashMap<>();

    @Override
    public MoveResult canMove(Location location) {
        return null;
    }

    @Override
    public Class<Rook> getEntityType() {
        return Rook.class;
    }

    @Override
    public String consoleIcon() {
        return "♖";
    }

    @Override
    public void setDrawables(Resources resources) {
        drawables.put(ImageAssetType.WHITE_BRIGHT, resources.getDrawable(R.drawable.entity_rook_white_40x40).getConstantState());
        drawables.put(ImageAssetType.WHITE_DARK, resources.getDrawable(R.drawable.entity_rook_white_2_40x40).getConstantState());
        drawables.put(ImageAssetType.BLACK_BRIGHT, resources.getDrawable(R.drawable.entity_rook_black_40x40).getConstantState());
        drawables.put(ImageAssetType.BLACK_DARK, resources.getDrawable(R.drawable.entity_rook_black_2_40x40).getConstantState());
    }

    @Override
    public Map<ImageAssetType, Drawable.ConstantState> getDrawables() {
        return this.drawables;
    }
}
