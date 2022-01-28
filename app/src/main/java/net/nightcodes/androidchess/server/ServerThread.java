package net.nightcodes.androidchess.server;

import net.nightcodes.androidchess.Host;

import java.io.IOException;

public class ServerThread implements Runnable {

    private final Server server;
    private final Host host;

    public ServerThread(Host host, Server server) {
        this.server = server;
        this.host = host;
        server.setHost(host);
    }

    @Override
    public void run() {
        try {
            server.start();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Server getServer() {
        return server;
    }

    public Host getHost() {
        return host;
    }
}
