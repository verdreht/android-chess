package net.nightcodes.androidchess.game.entity.base;

import net.nightcodes.androidchess.game.logic.MoveResult;
import net.nightcodes.androidchess.game.logic.movement.Location;

public interface IEntity<T> {

    MoveResult canMove(Location location);
    Class<T> getEntityType();
    String consoleIcon();
}
