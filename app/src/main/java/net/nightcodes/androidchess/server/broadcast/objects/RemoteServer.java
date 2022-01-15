package net.nightcodes.androidchess.server.broadcast.objects;

import java.net.InetAddress;

public class RemoteServer {

    private String name;
    private InetAddress address;
    private int port;

    public RemoteServer(String name, InetAddress address, int port) {
        this.name = name;
        this.address = address;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
