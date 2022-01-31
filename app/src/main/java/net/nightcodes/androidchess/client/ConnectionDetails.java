package net.nightcodes.androidchess.client;

import java.net.InetAddress;

public class ConnectionDetails {

    private final InetAddress address;
    private final int port;

    public ConnectionDetails(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}