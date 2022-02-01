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
        name = "Bishop",
        imageAsset = "/path/to/image",
        movementPermission = {MovementPermission.DIAGONAL_RESTRICTED}
)

public class Bishop implements IEntity<Bishop> {

    private EntityColor entityColor;

    private Map<ImageAssetType, Drawable.ConstantState> drawables = new HashMap<>();
    private Map<ImageAssetType, Drawable.ConstantState> whiteDrawables = new HashMap<>();
    private Map<ImageAssetType, Drawable.ConstantState> blackDrawables = new HashMap<>();

    public Bishop() {
    }

    @Override
    public MoveResult canMove(Field currentLocation, Field nextMoveLocation, Board board) {
        MoveResult moveResult = MoveResult.NOT_PERMITTED;
        for (int i = 1; i < 6; i++) {
            if (((nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() + i) ||
                    (nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() - i)) &&
                    ((nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX() + i) ||
                            (nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX() - i))) {
                moveResult = MoveResult.PERMITTED;
                break;
            } else if (((nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() + i) ||
                    (nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() - i)) &&
                    ((nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX() + i) ||
                            (nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX() - i)) &&
                    (nextMoveLocation.getFieldEntity().getEntityColor().ordinal() != currentLocation.getFieldEntity().getEntityColor().ordinal())) {
                moveResult = MoveResult.ENTITY_HIT;
                break;
            }
        }
        return moveResult;
    }

    @Override
    public Class<Bishop> getEntityType() {
        return Bishop.class;
    }

    @Override
    public String consoleIcon() {
        return "♗";
    }

    @Override
    public void setAllDrawables(Resources resources) {
        drawables.put(ImageAssetType.WHITE_BRIGHT, resources.getDrawable(R.drawable.entity_bishop_white_40x40).getConstantState());
        drawables.put(ImageAssetType.WHITE_DARK, resources.getDrawable(R.drawable.entity_bishop_white_2_40x40).getConstantState());
        drawables.put(ImageAssetType.BLACK_BRIGHT, resources.getDrawable(R.drawable.entity_bishop_black_40x40).getConstantState());
        drawables.put(ImageAssetType.BLACK_DARK, resources.getDrawable(R.drawable.entity_bishop_black_2_40x40).getConstantState());
    }

    @Override
    public void setWhiteDrawables(Resources resources) {
        whiteDrawables.put(ImageAssetType.WHITE_BRIGHT, resources.getDrawable(R.drawable.entity_bishop_white_40x40).getConstantState());
        whiteDrawables.put(ImageAssetType.WHITE_DARK, resources.getDrawable(R.drawable.entity_bishop_white_2_40x40).getConstantState());
    }

    @Override
    public void setBlackDrawables(Resources resources) {
        blackDrawables.put(ImageAssetType.BLACK_BRIGHT, resources.getDrawable(R.drawable.entity_bishop_black_40x40).getConstantState());
        blackDrawables.put(ImageAssetType.BLACK_DARK, resources.getDrawable(R.drawable.entity_bishop_black_2_40x40).getConstantState());
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
