package net.nightcodes.androidchess.client;

import android.accounts.NetworkErrorException;
import android.util.Log;

import net.nightcodes.androidchess.client.packet.Packet;

import org.snf4j.core.SelectorLoop;
import org.snf4j.core.session.IStreamSession;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.List;

public class Client {

    private final InetAddress address;
    private final int port;

    public Client(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public Client sendPackets(List<Packet> packetList) {
        new Thread(() -> {
            SelectorLoop loop = null;
            try {
                loop = new SelectorLoop();
                loop.start();

                SocketChannel channel = SocketChannel.open();
                channel.configureBlocking(false);
                channel.connect(new InetSocketAddress(address, port));

                IStreamSession session = (IStreamSession) loop.register(channel, new ClientHandler()).sync().getSession();

                try {
                    if (session.isOpen()) {
                        for (Packet packet : packetList) {
                            Log.e("hansi", packet.getJsonElement().toString());
                            System.out.println("WARUM IS DA SEGA A NEGA");
                            session.write((packet.toData()));
                        }
                    } else {
                        throw new NetworkErrorException();
                    }
                } catch (NetworkErrorException ex) {
                    Log.e("NetworkError", "FATAL Client-Server Error", ex);
                }

            } catch(Exception ex) {
                ex.printStackTrace();
            } finally {
                if(loop != null) loop.stop();
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
