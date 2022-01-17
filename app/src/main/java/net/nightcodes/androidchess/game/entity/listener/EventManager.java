package net.nightcodes.androidchess.game.entity.listener;

import android.os.Build;

import androidx.annotation.RequiresApi;

import net.nightcodes.androidchess.game.entity.Rook;
import net.nightcodes.androidchess.game.entity.base.IEntity;
import net.nightcodes.androidchess.game.entity.listener.location.EntityLocationChangedEvent;
import net.nightcodes.androidchess.game.logic.movement.Location;
import net.nightcodes.androidchess.game.logic.movement.exception.IllegalLocationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EventManager {

    private final List<EntityListener> listenerList = new ArrayList<>();

    public void registerListener(EntityListener... listener) {
        listenerList.addAll(Arrays.asList(listener));
    }

    //TODO: Implement ListenerManager
    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean call(EventType eventType, Object... parameters) throws InvocationTargetException, IllegalAccessException {

        if(eventType == EventType.ENTITY_LOCATION_CHANGE) {
            for (EntityListener event : listenerList) {
                List<Method> methods = Arrays.stream(event.getClass().getMethods()).filter(method -> method.getName().equalsIgnoreCase("onEntityLocationChange")).collect(Collectors.toList());

                for (Method method : methods) {
                    System.out.println(method.getName());
                    try {
                        return (boolean) method.invoke(event, new EntityLocationChangedEvent(new Rook(), new Location(1,1)));
                    } catch (IllegalLocationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
        
    }

    public enum EventType {
        ENTITY_LOCATION_CHANGE
    }

}
