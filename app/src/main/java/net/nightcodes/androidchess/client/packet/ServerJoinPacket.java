package net.nightcodes.androidchess.client.packet;

import com.google.gson.JsonElement;

public class ServerJoinPacket implements IPacket {

    @Override
    public Packet build(PacketType packetType, JsonElement jsonElement) {
        return new Packet(PacketType.SERVER_JOIN, jsonElement);
    }
}
