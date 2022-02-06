package net.nightcodes.androidchess.client.packet;

import com.google.gson.JsonObject;

public class WaitForServerTurnPacket implements IPacket {

    @Override
    public Packet build() {
        JsonObject jsonObject = new JsonObject();
        return new Packet(PacketType.WAIT_FOR_SERVER_TURN, jsonObject);
    }
}
