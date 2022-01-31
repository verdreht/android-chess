package net.nightcodes.androidchess.client.packet;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Packet {

    private final PacketType packetType;
    private final JsonElement jsonElement;

    private int id;

    public Packet(int id, PacketType packetType, JsonElement jsonElement) {
        this.packetType = packetType;
        this.jsonElement = jsonElement;
        this.id = id;
    }

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

    public String toData() {
        JsonObject data = new JsonObject();

        JsonObject header = new JsonObject();
        header.addProperty("request_type", packetType.name());

        data.add("header", header);
        data.add("body", jsonElement);

        return data.toString();
    }

    public static Packet fromData(String content) {
        JsonElement parsedJson = JsonParser.parseString(content);

        PacketType packetType = PacketType.valueOf(parsedJson.getAsJsonObject().get("header").getAsJsonObject().get("request_type").getAsString());
        JsonObject body = parsedJson.getAsJsonObject().get("body").getAsJsonObject();

        return new Packet(packetType, body);

    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Packet packet = (Packet) o;
        return id == packet.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
