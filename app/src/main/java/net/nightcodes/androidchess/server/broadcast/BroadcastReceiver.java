package net.nightcodes.androidchess.server.broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class BroadcastReceiver {

    private DatagramSocket socket = null;

    private final int port;
    private boolean running;

    public BroadcastReceiver(int port) {
        System.out.println("[BROADCAST] Broadcast receiver initialized.");

        this.port = port;
        this.running = true;
    }

    public void startListening() {
        try {
            socket = new DatagramSocket(new InetSocketAddress("0.0.0.0", port));

            byte[] buffer = new byte[socket.getReceiveBufferSize()];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (running) {
                socket.receive(packet);
                System.out.println(new String(packet.getData(), StandardCharsets.UTF_8));
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
}
