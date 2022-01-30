package net.nightcodes.androidchess.client;

import android.content.Context;

import net.nightcodes.androidchess.client.packet.Packet;

import org.snf4j.core.SelectorLoop;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.List;

public class Client {

    private final InetAddress address;
    private final int port;

    private final Context context;

    public Client(Context context, InetAddress address, int port) {
        this.address = address;
        this.port = port;
        this.context = context;
    }

    public Client sendPackets(List<Packet> packetList) {
        new Thread(() -> {
            SelectorLoop loop;
            try {
                loop = new SelectorLoop();
                loop.start();

                SocketChannel channel = SocketChannel.open();
                channel.configureBlocking(false);
                channel.connect(new InetSocketAddress(address, port));

                loop.register(channel, new ClientHandler(context, packetList));

                loop.join();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }).start();
        return this;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }


}
