package net.nightcodes.androidchess.server;

import android.util.Log;

import net.nightcodes.androidchess.Host;
import net.nightcodes.androidchess.client.packet.Packet;
import net.nightcodes.androidchess.client.packet.PacketType;

import org.snf4j.core.handler.AbstractStreamHandler;

import java.nio.charset.StandardCharsets;

public class ServerHandler extends AbstractStreamHandler {

    private final Host host;

    public ServerHandler(Host host) {
        this.host = host;
    }

    @Override
    public void read(byte[] data) {
        Log.e("dadada", new String(data));
        Packet receivedPacket = Packet.fromData(data);

        if(receivedPacket.getPacketType() == PacketType.SERVER_JOIN) {
            host.addLogEntry("[SERVER] Received: " + receivedPacket.getJsonElement().toString());
        }
    }

}
