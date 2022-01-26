package net.nightcodes.androidchess.client.packet;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ServerJoinPacket implements IPacket {

    @Override
    public Packet build() {
        JsonObject jsonObject = new JsonObject();
        return new Packet(PacketType.SERVER_JOIN, jsonObject);
    }
}
