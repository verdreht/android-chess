package net.nightcodes.androidchess.utils;

import net.nightcodes.androidchess.game.entity.base.EntityIdentification;
import net.nightcodes.androidchess.game.entity.base.IEntity;

public class EntityHelper {

    public static EntityIdentification getAnnotation(IEntity<?> entity) {
        return entity.getClass().getAnnotation(EntityIdentification.class);
    }

}
