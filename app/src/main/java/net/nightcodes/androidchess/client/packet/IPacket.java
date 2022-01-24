package net.nightcodes.androidchess.client.packet;

import com.google.gson.JsonElement;

public interface IPacket {

    Packet build(PacketType packetType, JsonElement jsonElement);

}
