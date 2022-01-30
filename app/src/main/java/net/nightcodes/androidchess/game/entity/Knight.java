package net.nightcodes.androidchess.game.entity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import net.nightcodes.androidchess.R;
import net.nightcodes.androidchess.game.entity.base.EntityIdentification;
import net.nightcodes.androidchess.game.entity.base.IEntity;
import net.nightcodes.androidchess.game.entity.base.ImageAssetType;
import net.nightcodes.androidchess.game.logic.MoveResult;
import net.nightcodes.androidchess.game.logic.board.EntityColor;
import net.nightcodes.androidchess.game.logic.movement.Location;
import net.nightcodes.androidchess.game.logic.movement.MovementPermission;

import java.util.HashMap;
import java.util.Map;

@EntityIdentification(
        name = "Knight",
        imageAsset = "/path/to/image",
        movementPermission = {MovementPermission.CUSTOM}
)

public class Knight implements IEntity<Knight> {

    private EntityColor entityColor;

    private Map<ImageAssetType, Drawable.ConstantState> drawables = new HashMap<>();
    private Map<ImageAssetType, Drawable.ConstantState> whiteDrawables = new HashMap<>();
    private Map<ImageAssetType, Drawable.ConstantState> blackDrawables = new HashMap<>();

    @Override
    public MoveResult canMove(Location location) {

        return null;
    }

    @Override
    public Class<Knight> getEntityType() {
        return Knight.class;
    }

    @Override
    public String consoleIcon() {
        return "♘";
    }

    @Override
    public void setAllDrawables(Resources resources) {
        drawables.put(ImageAssetType.WHITE_BRIGHT, resources.getDrawable(R.drawable.entity_knight_white_40x40).getConstantState());
        drawables.put(ImageAssetType.WHITE_DARK, resources.getDrawable(R.drawable.entity_knight_white_2_40x40).getConstantState());
        drawables.put(ImageAssetType.BLACK_BRIGHT, resources.getDrawable(R.drawable.entity_knight_black_40x40).getConstantState());
        drawables.put(ImageAssetType.BLACK_DARK, resources.getDrawable(R.drawable.entity_knight_black_2_40x40).getConstantState());
    }

    @Override
    public void setWhiteDrawables(Resources resources) {
        whiteDrawables.put(ImageAssetType.WHITE_BRIGHT, resources.getDrawable(R.drawable.entity_knight_white_40x40).getConstantState());
        whiteDrawables.put(ImageAssetType.WHITE_DARK, resources.getDrawable(R.drawable.entity_knight_white_2_40x40).getConstantState());
    }

    @Override
    public void setBlackDrawables(Resources resources) {
        blackDrawables.put(ImageAssetType.BLACK_BRIGHT, resources.getDrawable(R.drawable.entity_knight_black_40x40).getConstantState());
        blackDrawables.put(ImageAssetType.BLACK_DARK, resources.getDrawable(R.drawable.entity_knight_black_2_40x40).getConstantState());
    }

    @Override
    public Map<ImageAssetType, Drawable.ConstantState> getAllDrawables() {
        return this.drawables;
    }

    @Override
    public Map<ImageAssetType, Drawable.ConstantState> getWhiteDrawables() {
        return whiteDrawables;
    }

    @Override
    public Map<ImageAssetType, Drawable.ConstantState> getBlackDrawables() {
        return blackDrawables;
    }

    @Override
    public EntityColor getEntityColor() {
        return entityColor;
    }

    @Override
    public IEntity<?> setEntityColor(EntityColor entityColor) {
        this.entityColor = entityColor;
        return this;
    }
}
