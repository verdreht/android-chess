package net.nightcodes.androidchess.game.logic.board;

import androidx.annotation.NonNull;

import com.google.gson.JsonObject;

import net.nightcodes.androidchess.game.entity.base.EntityIdentification;
import net.nightcodes.androidchess.game.entity.base.IEntity;
import net.nightcodes.androidchess.game.logic.movement.Location;
import net.nightcodes.androidchess.utils.EntityHelper;

public class Field {

    private final Location fieldLocation;
    private IEntity<?> fieldEntity;

    public Field(Location fieldLocation) {
        this.fieldLocation = fieldLocation;
    }

    public Location getFieldLocation() {
        return fieldLocation;
    }

    public void setEntity(IEntity<?> fieldEntity) {
        this.fieldEntity = fieldEntity;
    }

    public IEntity<?> getFieldEntity() {
        return fieldEntity;
    }

    @NonNull
    @Override
    public String toString() {
        if(fieldEntity != null) return fieldEntity.consoleIcon(); else return "  ";
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        if(fieldEntity != null) {
            EntityIdentification identification = EntityHelper.getAnnotation(fieldEntity);

            json.addProperty("entity_type", identification.name());
            json.addProperty("entity_color", (fieldEntity.getEntityColor() != null ? fieldEntity.getEntityColor().name() : EntityColor.UNKNOWN.name()));

            JsonObject location = new JsonObject();
            location.addProperty("pos_x", fieldLocation.getX());
            location.addProperty("pos_y", fieldLocation.getY());

            json.add("location", location);

            return json;

        }

        return null;
    }
}