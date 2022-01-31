package net.nightcodes.androidchess.game.entity.base;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import net.nightcodes.androidchess.game.logic.MoveResult;
<<<<<<< Updated upstream
=======
import net.nightcodes.androidchess.game.logic.board.Board;
import net.nightcodes.androidchess.game.logic.board.EntityColor;
import net.nightcodes.androidchess.game.logic.board.Field;
>>>>>>> Stashed changes
import net.nightcodes.androidchess.game.logic.movement.Location;

import java.util.Map;

public interface IEntity<T> {

    MoveResult canMove(Field currentLocation, Field nextMoveLocation, Board board);
    Class<T> getEntityType();
    String consoleIcon();
    void setAllDrawables(Resources resources);
    void setWhiteDrawables(Resources resources);
    void setBlackDrawables(Resources resources);
    Map<ImageAssetType, Drawable.ConstantState> getAllDrawables();
    Map<ImageAssetType, Drawable.ConstantState> getWhiteDrawables();
    Map<ImageAssetType, Drawable.ConstantState> getBlackDrawables();
}
