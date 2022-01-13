package net.nightcodes.androidchess.game;

import androidx.annotation.NonNull;

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

    @NonNull
    @Override
    public String toString() {
        if(fieldEntity != null) return fieldEntity.consoleIcon(); else return "  ";
    }
}