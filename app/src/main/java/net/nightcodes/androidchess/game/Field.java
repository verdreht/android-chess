package net.nightcodes.androidchess.game;

import net.nightcodes.androidchess.game.entity.base.IEntity;
import net.nightcodes.androidchess.game.logic.movement.Location;

public class Field {

    private final Location fieldLocation;
    private IEntity fieldEntity;

    public Field(Location fieldLocation) {
        this.fieldLocation = fieldLocation;
    }

    public Location getFieldLocation() {
        return fieldLocation;
    }

    public void setEntity(IEntity fieldEntity) {
        this.fieldEntity = fieldEntity;
    }

    public IEntity getFieldEntity() {
        return fieldEntity;
    }
}
