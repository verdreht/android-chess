package net.nightcodes.androidchess;

import net.nightcodes.androidchess.client.Client;
import net.nightcodes.androidchess.game.logic.board.Board;
import net.nightcodes.androidchess.game.logic.board.listener.BoardChangeListener;
import net.nightcodes.androidchess.game.logic.board.listener.BoardEventManager;
import net.nightcodes.androidchess.game.logic.movement.exception.IllegalLocationException;
import net.nightcodes.androidchess.server.Server;

public class Constants {

    private static Board board = Board.getInstance();

    private static Server server;
    private static Client client = new Client();
    private final static BoardEventManager boardEventManager = new BoardEventManager();
    private static boolean isServerOnTurn;
    private static final Game game = new Game();

    public static void initBoard() {
        try {
            board.setup();
        } catch (IllegalLocationException e) {
            e.printStackTrace();
        }
        boardEventManager.registerListeners(new BoardChangeListener());
    }

    public static BoardEventManager getBoardEventManager() {
        return boardEventManager;
    }

    public static Server getServer() {
        return server;
    }

    public static Board getBoard() {
        return board;
    }

    public static boolean isServerOnTurn() {
        return isServerOnTurn;
    }

    public static void setIsServerOnTurn(boolean isServerOnTurn) {
        Constants.isServerOnTurn = isServerOnTurn;
    }

    public static Game getGame() {
        return game;
    }

    public static void setBoard(Board board) {
        Constants.board = board;
    }

    public static Client getClient() {
        return client;
    }

    public static void setClient(Client client) {
        Constants.client = client;
    }

    public static void setServer(Server server) {
        Constants.server = server;
    }
}
