package net.nightcodes.androidchess.client.packet;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.nio.charset.StandardCharsets;

public class Packet {

    private final PacketType packetType;
    private final JsonElement jsonElement;


    public Packet(PacketType packetType, JsonElement jsonElement) {
        this.packetType = packetType;
        this.jsonElement = jsonElement;
    }

    public PacketType getPacketType() {
        return packetType;
    }

    public JsonElement getJsonElement() {
        return jsonElement;
    }

    public byte[] toData() {
        JsonObject data = new JsonObject();

        JsonObject header = new JsonObject();
        header.addProperty("request_type", packetType.name());

        data.add("header", data);
        data.add("body", jsonElement);

        return data.toString().getBytes();
    }

    public static Packet fromData(byte[] byteSequence) {
        String content = new String(byteSequence, StandardCharsets.UTF_8).trim();
        JsonElement parsedJson = JsonParser.parseString(content);

        PacketType packetType = PacketType.valueOf(parsedJson.getAsJsonObject().get("header").getAsJsonObject().get("request_type").getAsString());
        JsonObject body = parsedJson.getAsJsonObject().get("body").getAsJsonObject();

        return new Packet(packetType, body);

    }
}
