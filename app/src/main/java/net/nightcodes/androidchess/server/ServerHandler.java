package net.nightcodes.androidchess.server;

import com.google.gson.JsonObject;

import org.snf4j.core.handler.AbstractStreamHandler;

import java.nio.charset.StandardCharsets;

public class ServerHandler extends AbstractStreamHandler {

    @Override
    public void read(byte[] data) {
        String content = new String(data, StandardCharsets.UTF_8).trim();

        if(content.equals("test")) {
            System.out.println(content);

            JsonObject element = new JsonObject();
            element.addProperty("test_property", "Hello world!");

            getSession().write(element.toString().getBytes());
        }
    }

}
