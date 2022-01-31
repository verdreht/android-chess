package net.nightcodes.androidchess.client.packet;

import net.nightcodes.androidchess.Constants;

public class BoardPacket implements IPacket {

    @Override
    public Packet build() {
        return new Packet(PacketType.SENDING_BOARD, Constants.getBoard().toJson());
    }
}
