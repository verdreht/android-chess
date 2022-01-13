package net.nightcodes.androidchess.game.entity.listener;

import android.os.Build;

import androidx.annotation.RequiresApi;

import net.nightcodes.androidchess.game.entity.base.IEntity;
import net.nightcodes.androidchess.game.entity.listener.location.EntityLocationChangedEvent;
import net.nightcodes.androidchess.game.logic.movement.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventManager {

    private final List<EntityListener> listenerList = new ArrayList<>();

    public void registerListener(EntityListener... listener) {
        listenerList.addAll(Arrays.asList(listener));
    }

    //TODO: Implement ListenerManager
    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean call(Class<EntityListener> instance, Object... parameters) {

        for(EntityListener event : listenerList) {
            event.onEntityLocationChange(new EntityLocationChangedEvent((IEntity) parameters[0], (Location) parameters[1]));
        }
        throw new UnsupportedOperationException();
    }

}
