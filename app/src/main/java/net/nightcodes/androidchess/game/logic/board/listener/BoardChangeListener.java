package net.nightcodes.androidchess.game.logic.board.listener;

import android.util.Log;

public class BoardChangeListener extends BoardEvent {

    @Override
    public void onChange(BoardChangeEvent event) {
        Log.e("BOARD\n", event.getBoard().toString());
    }
}
