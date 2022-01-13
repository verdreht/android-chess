package net.nightcodes.androidchess.game.entity.listener.location;

import net.nightcodes.androidchess.game.entity.base.IEntity;
import net.nightcodes.androidchess.game.entity.listener.EntityEvent;
import net.nightcodes.androidchess.game.logic.movement.Location;

public class EntityLocationChangedEvent extends EntityEvent {

    private final Location movedTo;

    public EntityLocationChangedEvent(IEntity entity, Location movedTo) {
        super(entity);
        this.movedTo = movedTo;
    }

    public Location getMovedTo() {
        return movedTo;
    }
}
