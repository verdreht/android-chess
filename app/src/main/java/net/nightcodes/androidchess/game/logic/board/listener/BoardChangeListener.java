package net.nightcodes.androidchess.game.logic.board.listener;

import android.util.Log;

import net.nightcodes.androidchess.server.ServerHandler;

public class BoardChangeListener extends BoardEvent {

    public ServerHandler serverHandler;

    public BoardChangeListener(ServerHandler serverHandler) {
        this.serverHandler = serverHandler;
    }

    @Override
    public void onChange(BoardChangeEvent event) {
        Log.e("BOARD\n", event.getBoard().toString());
        serverHandler.responseBoard(this, event.getBoard());
    }


}