package net.nightcodes.androidchess.game.logic.board.listener;

import net.nightcodes.androidchess.game.logic.board.Board;

public class BoardChangeEvent {
    private final Board board;

    public BoardChangeEvent(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }
}
