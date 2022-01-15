package net.nightcodes.androidchess.server.broadcast;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.JsonObject;

import net.nightcodes.androidchess.server.Server;
import net.nightcodes.androidchess.utils.Utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class BroadcastSender {

    private DatagramSocket socket = null;
    private final int port;
    private final Server server;

    public BroadcastSender(Server server, int port) {
        this.server = server;
        this.port = port;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void performBroadcast() throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("info", "Android Chess");

        JsonObject serverSettings = new JsonObject();
        serverSettings.addProperty("name", server.getServerName());
        serverSettings.addProperty("ipv4", Utils.getIPAddress(true));
        serverSettings.addProperty("port", server.getPort());

        json.add("server", serverSettings);

        for (InetAddress broadcast : listAllBroadcastAddresses()) {
            System.out.println("[BROADCAST] " + broadcast.getHostAddress() + " -> " + json.toString());
            broadcast(json.toString(), broadcast);
        }
    }

    private void broadcast(String broadcastMessage, InetAddress address) throws IOException {
        socket = new DatagramSocket();
        socket.setBroadcast(true);

        byte[] buffer = broadcastMessage.getBytes();

        DatagramPacket packet
                = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(packet);
        socket.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<InetAddress> listAllBroadcastAddresses() throws SocketException {
        List<InetAddress> broadcastList = new ArrayList<>();
        Enumeration<NetworkInterface> interfaces
                = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();

            if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                continue;
            }

            networkInterface.getInterfaceAddresses().stream()
                    .map(InterfaceAddress::getBroadcast)
                    .filter(Objects::nonNull)
                    .forEach(broadcastList::add);
        }
        return broadcastList;
    }

    public DatagramSocket getSocket() {
        return socket;
    }
}
