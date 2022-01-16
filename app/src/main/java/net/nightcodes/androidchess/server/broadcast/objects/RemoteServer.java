package net.nightcodes.androidchess.server.broadcast.objects;

import androidx.annotation.NonNull;

import java.net.InetAddress;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoteServer that = (RemoteServer) o;
        return port == that.port && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, port);
    }

    @NonNull
    @Override
    public String toString() {
        return "RemoteServer{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", port=" + port +
                '}';
    }
}
