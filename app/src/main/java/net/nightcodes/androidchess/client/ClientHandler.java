package net.nightcodes.androidchess.client;

import android.util.Log;

import org.snf4j.core.EndingAction;
import org.snf4j.core.handler.AbstractStreamHandler;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.session.DefaultSessionConfig;
import org.snf4j.core.session.ISessionConfig;

import java.nio.charset.StandardCharsets;

public class ClientHandler extends AbstractStreamHandler {

    @Override
    public void read(byte[] data) {
        String content = new String(data, StandardCharsets.UTF_8).trim();
        System.out.println(content);
    }

    @Override
    public void event(SessionEvent event) {
        switch (event) {
            case READY:
                getSession().write("Hello, World!");
                break;
            case CLOSED:
                Log.e("closed", "Client closed");
                break;
        }
    }

    @Override
    public ISessionConfig getConfig() {
        return new DefaultSessionConfig().setEndingAction(EndingAction.STOP);
    }
}