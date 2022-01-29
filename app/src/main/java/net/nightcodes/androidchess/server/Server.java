package net.nightcodes.androidchess.server;

import net.nightcodes.androidchess.Host;
import net.nightcodes.androidchess.utils.Utils;

import java.io.IOException;

public class Server {

    private final int port;
    private String serverName;
    private Host host;

    private Server(int port) {
        this.port = port;
    }

    public static Server getInstance(int port) {
        return new Server(port);
    }

    public void start() throws IOException, InterruptedException {
        host.addLogEntry("[SERVER] Lobby has been created! >> " + serverName);
        host.addLogEntry("[SERVER] Initialized server! >> " + Utils.getIPAddress(true) + ":" + port);

        

    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getPort() {
        return port;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }
}
