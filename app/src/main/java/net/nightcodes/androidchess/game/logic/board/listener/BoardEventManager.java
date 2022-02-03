package net.nightcodes.androidchess.game.logic.board.listener;

import net.nightcodes.androidchess.game.logic.board.Board;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BoardEventManager {
    private final Set<BoardEvent> boardListeners = new HashSet<>();

    public void registerListeners(BoardEvent... events) {
        boardListeners.addAll(Arrays.asList(events));
    }

    public void call(Board board) {
        for (BoardEvent event : boardListeners) {
            event.onChange(new BoardChangeEvent(board));
        }
    }
}
