package net.nightcodes.androidchess.game.entity.listener;

import net.nightcodes.androidchess.game.entity.base.IEntity;

public abstract class EntityEvent {

    private final IEntity entity;
    private boolean canceled;

    public EntityEvent(IEntity entity) {
        this.entity = entity;
    }

    public IEntity getEntity() {
        return entity;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}
