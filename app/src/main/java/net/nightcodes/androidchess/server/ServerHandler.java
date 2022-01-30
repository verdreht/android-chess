package net.nightcodes.androidchess.server;

import android.content.Intent;
import android.util.Log;

import net.nightcodes.androidchess.Game;
import net.nightcodes.androidchess.Host;
import net.nightcodes.androidchess.client.packet.Packet;
import net.nightcodes.androidchess.client.packet.PacketType;

import org.snf4j.core.handler.AbstractStreamHandler;

public class ServerHandler extends AbstractStreamHandler {

    private final Host host;

    public ServerHandler(Host host) {
        this.host = host;
    }

    @Override
    public void read(byte[] data) {
        Log.e("Hööö received", new String(data));
        Packet receivedPacket = Packet.fromData(data);

        if(receivedPacket.getPacketType() == PacketType.SERVER_JOIN) {
            host.addLogEntry("[SERVER] Received: " + receivedPacket.getJsonElement().toString());
            Intent intent = new Intent(host, Game.class);
            host.startActivity(intent);
        }
    }
}
