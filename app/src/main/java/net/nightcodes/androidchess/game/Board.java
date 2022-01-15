package net.nightcodes.androidchess.game;

import androidx.annotation.NonNull;

import net.nightcodes.androidchess.game.entity.Bishop;
import net.nightcodes.androidchess.game.entity.King;
import net.nightcodes.androidchess.game.entity.Knight;
import net.nightcodes.androidchess.game.entity.Pawn;
import net.nightcodes.androidchess.game.entity.Queen;
import net.nightcodes.androidchess.game.entity.Rook;
import net.nightcodes.androidchess.game.logic.movement.Location;
import net.nightcodes.androidchess.game.logic.movement.exception.IllegalLocationException;

public class Board {

    private final Field[][] board = new Field[8][8];

    public static Board getInstance() {
        return new Board();
    }

    public void setup() throws IllegalLocationException {
        for(int i = 0; i < board.length; i++) {
            for(int x = 0; x < board[i].length; x++) {
                board[i][x] = new Field(new Location(i + 1, x + 1));
            }
        }
        initEntities();
    }

    private void initEntities() {

        getField(1, 1).setEntity(new Rook());
        getField(2, 1).setEntity(new Knight());
        getField(3, 1).setEntity(new Bishop());
        getField(4, 1).setEntity(new Queen());
        getField(5, 1).setEntity(new King());
        getField(6, 1).setEntity(new Bishop());
        getField(7, 1).setEntity(new Knight());
        getField(8, 1).setEntity(new Rook());

        for(int i = 1; i <= 8; i++) {
            getField(i, 2).setEntity(new Pawn());
        }

        getField(1, 8).setEntity(new Rook());
        getField(2, 8).setEntity(new Knight());
        getField(3, 8).setEntity(new Bishop());
        getField(4, 8).setEntity(new King());
        getField(5, 8).setEntity(new Queen());
        getField(6, 8).setEntity(new Bishop());
        getField(7, 8).setEntity(new Knight());
        getField(8, 8).setEntity(new Rook());

        for(int i = 1; i <= 8; i++) {
            getField(i, 7).setEntity(new Pawn());
        }

    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Field[] x : board)
        {
            for (Field y : x)
            {
                builder.append(y).append(" ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public Field getField(int x, int y) {
        return board[x - 1][y - 1];
    }

}