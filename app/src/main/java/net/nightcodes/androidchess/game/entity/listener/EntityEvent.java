package net.nightcodes.androidchess.game.entity.listener;

import net.nightcodes.androidchess.game.entity.base.IEntity;

public abstract class EntityEvent {

    public IEntity entity;

    public EntityEvent(IEntity entity) {
        this.entity = entity;
    }

    public IEntity getEntity() {
        return entity;
    }
}
