package net.nightcodes.androidchess.server;

import android.util.Log;

import org.snf4j.core.handler.AbstractStreamHandler;

import java.nio.charset.StandardCharsets;

public class ServerHandler extends AbstractStreamHandler {

    @Override
    public void read(byte[] data) {
        String content = new String(data, StandardCharsets.UTF_8).trim();

        Log.d("SOCKET RECEIVED", content);
    }

}
