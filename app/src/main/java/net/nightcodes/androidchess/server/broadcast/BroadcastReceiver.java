package net.nightcodes.androidchess.server.broadcast;

import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.nightcodes.androidchess.server.broadcast.objects.RemoteServer;
import net.nightcodes.androidchess.ui.networkscan.AdapterManager;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BroadcastReceiver {

    private DatagramSocket socket = null;

    private final int port;
    private final AppCompatActivity activity;
    private final List<RemoteServer> serverFound = new ArrayList<>();

    private boolean running;

    public BroadcastReceiver(AppCompatActivity activity, int port) {
        System.out.println("[BROADCAST] Broadcast receiver initialized.");

        this.activity = activity;
        this.port = port;
        this.running = true;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void startListening() {
        try {
            socket = new DatagramSocket(new InetSocketAddress("0.0.0.0", port));

            while (running) {
                byte[] buffer = new byte[socket.getReceiveBufferSize()];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                socket.receive(packet);
                System.out.println("[RECEIVED] " + packet.getAddress().getHostAddress() + " -> " + new String(packet.getData(), StandardCharsets.UTF_8).trim());

                JsonObject broadcastContent = JsonParser.parseString(new String(packet.getData(), StandardCharsets.UTF_8).trim()).getAsJsonObject();
                JsonObject serverInfo = broadcastContent.getAsJsonObject("server");

                boolean nameExists = serverInfo.get("name").isJsonNull();

                RemoteServer serverObject = new RemoteServer((!nameExists ? serverInfo.get("name").getAsString() : null), InetAddress.getByName(serverInfo.get("ipv4").getAsString()), serverInfo.get("port").getAsInt());

                System.out.println(serverObject);

                if(!serverFound.contains(serverObject)) {
                    serverFound.add(serverObject);
                }
                activity.runOnUiThread(() -> AdapterManager.getAdapter().notifyDataSetChanged());

            }

            socket.setBroadcast(true);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public int getPort() {
        return port;
    }

    public boolean isRunning() {
        return running;
    }

    public List<RemoteServer> getServerFound() {
        return serverFound;
    }
}
