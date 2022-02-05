package net.nightcodes.androidchess.game.entity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import net.nightcodes.androidchess.R;
import net.nightcodes.androidchess.game.entity.base.EntityIdentification;
import net.nightcodes.androidchess.game.entity.base.IEntity;
import net.nightcodes.androidchess.game.entity.base.ImageAssetType;
import net.nightcodes.androidchess.game.logic.MoveResult;
import net.nightcodes.androidchess.game.logic.board.Board;
import net.nightcodes.androidchess.game.logic.board.EntityColor;
import net.nightcodes.androidchess.game.logic.board.Field;
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
    public MoveResult canMove(Field currentLocation, Field nextMoveLocation, Board board) {
        if (
                        //2 nach oben, (1 nach rechts) ODER (1 nach links)
                ((nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX() + 2) &&
                ((nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() + 1) || (nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() - 1)))
                ||
                        //1 nach oben, (2 nach rechts) ODER (2 nach links)
                ((nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX() + 1) &&
                ((nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() + 2) || (nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() - 2)))
                ||
                        //2 nach unten, (1 nach rechts) ODER (1 nach links)
                ((nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX() - 2) &&
                ((nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() + 1) || (nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() - 1)))
                ||
                        //1 nach unten, (2 nach rechts) ODER (2 nach links)
                ((nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX() - 1) &&
                ((nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() + 2) || (nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() - 2)))
        ) {
            if (nextMoveLocation.getFieldEntity() == null) {
                return MoveResult.PERMITTED;
            } else {
                if (nextMoveLocation.getFieldEntity().getEntityColor().ordinal() != currentLocation.getFieldEntity().getEntityColor().ordinal()) {
                    return MoveResult.ENTITY_HIT;
                } else {
                    return MoveResult.NOT_PERMITTED;
                }
            }
        } else {
            return MoveResult.NOT_PERMITTED;
        }
    }

    @Override
    public Class<Knight> getEntityType() {
        return Knight.class;
    }

    @Override
    public String getEntityTypeRaw() {
        return "Knight";
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
