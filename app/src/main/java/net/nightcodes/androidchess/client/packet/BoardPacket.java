package net.nightcodes.androidchess.client.packet;

import net.nightcodes.androidchess.Constants;
import net.nightcodes.androidchess.game.logic.board.Board;

public class BoardPacket implements IPacket {

    private final Board board;
    public BoardPacket(Board board) {
        this.board = board;
    }

    @Override
    public Packet build() {
        return new Packet(PacketType.SENDING_BOARD, board.toJson());
    }
}
