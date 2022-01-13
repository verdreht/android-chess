package net.nightcodes.androidchess.game.entity.base;

import androidx.annotation.NonNull;

import net.nightcodes.androidchess.game.logic.MoveResult;
import net.nightcodes.androidchess.game.logic.movement.Location;

public interface IEntity {

    MoveResult canMove(Location location);

    @NonNull
    String toString();

}
