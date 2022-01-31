package net.nightcodes.androidchess.server;

import android.os.AsyncTask;
import android.util.Log;

import net.nightcodes.androidchess.Game;
import net.nightcodes.androidchess.Host;
import net.nightcodes.androidchess.utils.Utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private final int port;
    private String serverName;

    private boolean alive;

    private ServerSocket serverSocket;
    private Game game;

    public Server(Game game, String serverName, int port) {
        this.port = port;
        this.serverName = serverName;
        this.game = game;

        this.alive = true;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            Log.e("run():", "Server initialized.");

            while(alive) {
                Socket socket = serverSocket.accept();
                ServerHandler handler = new ServerHandler(this.game);
                Log.e("run():", "Passing new connection to worker: " + socket.getInetAddress().getHostAddress());
                handler.execute(socket);
            }

        } catch (IOException e) {
            Log.e("doInBackGround():", e.getMessage());
        }
    }

    public int getPort() {
        return port;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
}
