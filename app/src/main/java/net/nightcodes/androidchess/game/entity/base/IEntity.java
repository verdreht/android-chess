package net.nightcodes.androidchess.game.entity.base;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import net.nightcodes.androidchess.game.logic.MoveResult;
import net.nightcodes.androidchess.game.logic.movement.Location;

import java.util.Map;

public interface IEntity<T> {

    MoveResult canMove(Location location);
    Class<T> getEntityType();
    String consoleIcon();
    void setAllDrawables(Resources resources);
    void setWhiteDrawables(Resources resources);
    void setBlackDrawables(Resources resources);
    Map<ImageAssetType, Drawable.ConstantState> getAllDrawables();
    Map<ImageAssetType, Drawable.ConstantState> getWhiteDrawables();
    Map<ImageAssetType, Drawable.ConstantState> getBlackDrawables();
}
