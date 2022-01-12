package net.nightcodes.androidchess.server;

import java.io.IOException;

public class ServerThread implements Runnable {

    private final Server server;

    public ServerThread(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        try {
            server.start();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
