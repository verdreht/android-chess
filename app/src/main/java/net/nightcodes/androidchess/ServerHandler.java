package net.nightcodes.androidchess;

import org.snf4j.core.handler.AbstractStreamHandler;

import java.util.Arrays;

public class ServerHandler extends AbstractStreamHandler {

    @Override
    public void read(byte[] data) {
        System.out.println(Arrays.toString(data));
    }


    @Override
    public void read(Object msg) {
        System.out.println(msg.toString());
    }
}
