package net.nightcodes.androidchess.game.logic.movement;

import androidx.annotation.NonNull;

import net.nightcodes.androidchess.game.logic.movement.exception.IllegalLocationException;

public class Location {

    int x;
    int y;

    public Location(int x, int y) throws IllegalLocationException {
        if(validateLocation(x, y)) {
            this.x = x;
            this.y = y;
        } else {
            throw new IllegalLocationException("The specified parameters are outside the board size.");
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) throws IllegalLocationException {
        if(validateLocation(x, 1)) {
            this.x = x;
        } else {
            throw new IllegalLocationException("The specified parameters are outside the board size.");
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int y) throws IllegalLocationException {
        if(validateLocation(1, y)) {
            this.y = y;
        } else {
            throw new IllegalLocationException("The specified parameters are outside the board size.");
        }
    }

    public static boolean validateLocation(int x, int y) {
        return x >= 1 && x <= 64 && y >= 1 && y <= 64;
    }

    @NonNull
    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
