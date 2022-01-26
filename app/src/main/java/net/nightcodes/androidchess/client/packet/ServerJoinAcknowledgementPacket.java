package net.nightcodes.androidchess.client.packet;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ServerJoinAcknowledgementPacket implements IPacket {

    @Override
    public Packet build() {
        return new Packet(PacketType.SERVER_JOIN_ACKNOWLEDGEMENT, new JsonObject());
    }
}
