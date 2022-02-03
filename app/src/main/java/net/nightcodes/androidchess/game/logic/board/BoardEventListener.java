package net.nightcodes.androidchess.game.logic.board;

import android.os.AsyncTask;
import android.util.Log;

import net.nightcodes.androidchess.Constants;

public class BoardEventListener extends AsyncTask<Void, Board, Void> {
    public static void printChanges() {
        Log.e("BOARD\n", Constants.getBoard().toString());
    }

    public static boolean isBoardChanged() {
        Board temp = Constants.getBoard();
        if (temp != Constants.getBoard()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        while (true) {
            if (isBoardChanged()) {
                printChanges();
                publishProgress();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Board... values) {
        super.onProgressUpdate(values);
    }
}
