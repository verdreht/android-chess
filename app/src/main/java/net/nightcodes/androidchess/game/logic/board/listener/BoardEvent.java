package net.nightcodes.androidchess.game.logic.board.listener;

public abstract class BoardEvent {
    public abstract void onChange(BoardChangeEvent event);
}
