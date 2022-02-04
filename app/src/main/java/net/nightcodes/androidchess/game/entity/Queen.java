package net.nightcodes.androidchess.game.entity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import net.nightcodes.androidchess.Constants;
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
        name = "Queen",
        imageAsset = "/path/to/image",
        movementPermission = {MovementPermission.DIAGONAL_UNLIMITED, MovementPermission.STRAIGHT_UNLIMITED}
)

public class Queen implements IEntity<Queen> {

    private EntityColor entityColor;

    private Map<ImageAssetType, Drawable.ConstantState> drawables = new HashMap<>();
    private Map<ImageAssetType, Drawable.ConstantState> whiteDrawables = new HashMap<>();
    private Map<ImageAssetType, Drawable.ConstantState> blackDrawables = new HashMap<>();

    @Override
    public MoveResult canMove(Field currentLocation, Field nextMoveLocation, Board board) {
        MoveResult moveResult = MoveResult.NOT_PERMITTED;

//        if (Constants.getBoard().getField(currentLocation.getFieldLocation().getX() + 1, currentLocation.getFieldLocation().getY()).getFieldEntity() != null)

        for (int i = 0; i < 7; i++) {
            if ((nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX() + i) &&
                    (nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY())) {
                //senkrecht nach oben
                if (Constants.getBoard().getField(currentLocation.getFieldLocation().getX() + 1, currentLocation.getFieldLocation().getY()).getFieldEntity() != null) {
                    if (Constants.getBoard().getField(currentLocation.getFieldLocation().getX() + 1, currentLocation.getFieldLocation().getY()).getFieldEntity().getEntityColor().ordinal() == currentLocation.getFieldEntity().getEntityColor().ordinal()) {
                        return MoveResult.NOT_PERMITTED;
                    }
                }
            } else if ((nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX() - i) &&
                            (nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY())) {
                //senkrecht nach unten
                if (Constants.getBoard().getField(currentLocation.getFieldLocation().getX() - 1, currentLocation.getFieldLocation().getY()).getFieldEntity() != null) {
                    if (Constants.getBoard().getField(currentLocation.getFieldLocation().getX() - 1, currentLocation.getFieldLocation().getY()).getFieldEntity().getEntityColor().ordinal() == currentLocation.getFieldEntity().getEntityColor().ordinal()) {
                        return MoveResult.NOT_PERMITTED;
                    }
                }
            } else if ((nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() + i) &&
                            (nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX())) {
                //waagrecht nach rechts
                if (Constants.getBoard().getField(currentLocation.getFieldLocation().getX(), currentLocation.getFieldLocation().getY() + 1).getFieldEntity() != null) {
                    if (Constants.getBoard().getField(currentLocation.getFieldLocation().getX(), currentLocation.getFieldLocation().getY() + 1).getFieldEntity().getEntityColor().ordinal() == currentLocation.getFieldEntity().getEntityColor().ordinal()) {
                        return MoveResult.NOT_PERMITTED;
                    }
                }
            } else if ((nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() - i) &&
                            (nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX())) {
                //waagrecht nach links
                if (Constants.getBoard().getField(currentLocation.getFieldLocation().getX(), currentLocation.getFieldLocation().getY() - 1).getFieldEntity() != null) {
                    if (Constants.getBoard().getField(currentLocation.getFieldLocation().getX(), currentLocation.getFieldLocation().getY() - 1).getFieldEntity().getEntityColor().ordinal() == currentLocation.getFieldEntity().getEntityColor().ordinal()) {
                        return MoveResult.NOT_PERMITTED;
                    }
                }
            } else if ((nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX() + i) &&
                            (nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() + i)) {
                //schräg rechts nach oben
                if (Constants.getBoard().getField(currentLocation.getFieldLocation().getX() + 1, currentLocation.getFieldLocation().getY() + 1).getFieldEntity() != null) {
                    if (Constants.getBoard().getField(currentLocation.getFieldLocation().getX() + 1, currentLocation.getFieldLocation().getY() + 1).getFieldEntity().getEntityColor().ordinal() == currentLocation.getFieldEntity().getEntityColor().ordinal()) {
                        return MoveResult.NOT_PERMITTED;
                    }
                }
            } else if ((nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX() + i) &&
                            (nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() - i)) {
                //schräg links nach oben
                if (Constants.getBoard().getField(currentLocation.getFieldLocation().getX() + 1, currentLocation.getFieldLocation().getY() - 1).getFieldEntity() != null) {
                    if (Constants.getBoard().getField(currentLocation.getFieldLocation().getX() + 1, currentLocation.getFieldLocation().getY() - 1).getFieldEntity().getEntityColor().ordinal() == currentLocation.getFieldEntity().getEntityColor().ordinal()) {
                        return MoveResult.NOT_PERMITTED;
                    }
                }
            } else if ((nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX() - i) &&
                            (nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() + i)) {
                //schräg rechts nach unten
                if (Constants.getBoard().getField(currentLocation.getFieldLocation().getX() - 1, currentLocation.getFieldLocation().getY() + 1).getFieldEntity() != null) {
                    if (Constants.getBoard().getField(currentLocation.getFieldLocation().getX() - 1, currentLocation.getFieldLocation().getY() + 1).getFieldEntity().getEntityColor().ordinal() == currentLocation.getFieldEntity().getEntityColor().ordinal()) {
                        return MoveResult.NOT_PERMITTED;
                    }
                }
            } else if ((nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX() - i) &&
                            (nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() - i)) {
                //schräg links nach unten
                if (Constants.getBoard().getField(currentLocation.getFieldLocation().getX() - 1, currentLocation.getFieldLocation().getY() - 1).getFieldEntity() != null) {
                    if (Constants.getBoard().getField(currentLocation.getFieldLocation().getX() - 1, currentLocation.getFieldLocation().getY() - 1).getFieldEntity().getEntityColor().ordinal() == currentLocation.getFieldEntity().getEntityColor().ordinal()) {
                        return MoveResult.NOT_PERMITTED;
                    }
                }
            }
            if (
                    //senkrecht nach oben
                    ((nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX() + i) &&
                    (nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY())) ||
                    //senkrecht nach unten
                    ((nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX() - i) &&
                    (nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY())) ||
                    //waagrecht nach rechts
                    ((nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() + i) &&
                    (nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX())) ||
                    //waagrecht nach links
                    ((nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() - i) &&
                    (nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX())) ||
                    //schräg rechts nach oben
                    ((nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX() + i) &&
                    (nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() + i)) ||
                    //schräg links nach oben
                    ((nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX() + i) &&
                    (nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() - i)) ||
                    //schräg rechts nach unten
                    ((nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX() - i) &&
                    (nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() + i)) ||
                    //schräg links nach unten
                    ((nextMoveLocation.getFieldLocation().getX() == currentLocation.getFieldLocation().getX() - i) &&
                    (nextMoveLocation.getFieldLocation().getY() == currentLocation.getFieldLocation().getY() - i))
            ) {
                if (nextMoveLocation.getFieldEntity() != null) {
                    if (nextMoveLocation.getFieldEntity().getEntityColor().ordinal() != currentLocation.getFieldEntity().getEntityColor().ordinal()) {
                        moveResult = MoveResult.ENTITY_HIT;
                        break;
                    }
                } else {
                    moveResult = MoveResult.PERMITTED;
                    break;
                }
            }
        }
        return moveResult;
    }

    @Override
    public Class<Queen> getEntityType() {
        return Queen.class;
    }

    @Override
    public String consoleIcon() {
        return "♕";
    }

    @Override
    public void setAllDrawables(Resources resources) {
        drawables.put(ImageAssetType.WHITE_BRIGHT, resources.getDrawable(R.drawable.entity_queen_white_40x40).getConstantState());
        drawables.put(ImageAssetType.WHITE_DARK, resources.getDrawable(R.drawable.entity_queen_white_2_40x40).getConstantState());
        drawables.put(ImageAssetType.BLACK_BRIGHT, resources.getDrawable(R.drawable.entity_queen_black_40x40).getConstantState());
        drawables.put(ImageAssetType.BLACK_DARK, resources.getDrawable(R.drawable.entity_queen_black_2_40x40).getConstantState());
    }

    @Override
    public void setWhiteDrawables(Resources resources) {
        whiteDrawables.put(ImageAssetType.WHITE_BRIGHT, resources.getDrawable(R.drawable.entity_queen_white_40x40).getConstantState());
        whiteDrawables.put(ImageAssetType.WHITE_DARK, resources.getDrawable(R.drawable.entity_queen_white_2_40x40).getConstantState());
    }

    @Override
    public void setBlackDrawables(Resources resources) {
        blackDrawables.put(ImageAssetType.BLACK_BRIGHT, resources.getDrawable(R.drawable.entity_queen_black_40x40).getConstantState());
        blackDrawables.put(ImageAssetType.BLACK_DARK, resources.getDrawable(R.drawable.entity_queen_black_2_40x40).getConstantState());
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
