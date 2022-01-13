package net.nightcodes.androidchess.game.entity.listener;

import net.nightcodes.androidchess.game.entity.base.IEntity;
import net.nightcodes.androidchess.game.entity.listener.location.EntityLocationChangedEvent;
import net.nightcodes.androidchess.game.logic.movement.Location;

public class LocationChangeListenerTest extends EntityListener {

    @Override
    public void onEntityLocationChange(EntityLocationChangedEvent event) {
        //Get the moved entity.
        IEntity entity = event.getEntity();

        //The Location the system is trying to move the entity to.
        Location newLocation = event.getMovedTo();

        System.out.println("Entity: " + entity);
        System.out.println("New Location: " + newLocation);

        //Cancels the event and stop propagation.
        event.setCanceled(true);
    }
}
