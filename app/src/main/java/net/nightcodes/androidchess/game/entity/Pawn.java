package net.nightcodes.androidchess.game.entity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import net.nightcodes.androidchess.R;
import net.nightcodes.androidchess.game.entity.base.EntityIdentification;
import net.nightcodes.androidchess.game.entity.base.IEntity;
import net.nightcodes.androidchess.game.entity.base.ImageAssetType;
import net.nightcodes.androidchess.game.logic.MoveResult;
<<<<<<< Updated upstream
=======
import net.nightcodes.androidchess.game.logic.board.Board;
import net.nightcodes.androidchess.game.logic.board.EntityColor;
import net.nightcodes.androidchess.game.logic.board.Field;
>>>>>>> Stashed changes
import net.nightcodes.androidchess.game.logic.movement.Location;
import net.nightcodes.androidchess.game.logic.movement.MovementPermission;

import java.util.HashMap;
import java.util.Map;

@EntityIdentification(
        name = "Pawn",
        imageAsset = "/path/to/image",
        movementPermission = {MovementPermission.STRAIGHT_RESTRICTED}
)

public class Pawn implements IEntity<Pawn> {
    private Map<ImageAssetType, Drawable.ConstantState> drawables = new HashMap<>();
    private Map<ImageAssetType, Drawable.ConstantState> whiteDrawables = new HashMap<>();
    private Map<ImageAssetType, Drawable.ConstantState> blackDrawables = new HashMap<>();

    @Override
    public MoveResult canMove(Field currentLocation, Field nextMoveLocation, Board board) {
        if (currentLocation.getFieldEntity().equals(Pawn.class)) {
            return MoveResult.PERMITTED;
        } else {
            return MoveResult.NOT_PERMITTED;
        }

    }

    @Override
    public Class<Pawn> getEntityType() {
        return Pawn.class;
    }

    @Override
    public String consoleIcon() {
        return "♙";
    }

    @Override
    public void setAllDrawables(Resources resources) {
        drawables.put(ImageAssetType.WHITE_BRIGHT, resources.getDrawable(R.drawable.entity_pawn_white_40x40).getConstantState());
        drawables.put(ImageAssetType.WHITE_DARK, resources.getDrawable(R.drawable.entity_pawn_white_2_40x40).getConstantState());
        drawables.put(ImageAssetType.BLACK_BRIGHT, resources.getDrawable(R.drawable.entity_pawn_black_40x40).getConstantState());
        drawables.put(ImageAssetType.BLACK_DARK, resources.getDrawable(R.drawable.entity_pawn_black_2_40x40).getConstantState());
    }

    @Override
    public void setWhiteDrawables(Resources resources) {
        whiteDrawables.put(ImageAssetType.WHITE_BRIGHT, resources.getDrawable(R.drawable.entity_pawn_white_40x40).getConstantState());
        whiteDrawables.put(ImageAssetType.WHITE_DARK, resources.getDrawable(R.drawable.entity_pawn_white_2_40x40).getConstantState());
    }

    @Override
    public void setBlackDrawables(Resources resources) {
        blackDrawables.put(ImageAssetType.BLACK_BRIGHT, resources.getDrawable(R.drawable.entity_pawn_black_40x40).getConstantState());
        blackDrawables.put(ImageAssetType.BLACK_DARK, resources.getDrawable(R.drawable.entity_pawn_black_2_40x40).getConstantState());
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
}
