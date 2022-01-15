package net.nightcodes.androidchess;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.nightcodes.androidchess.server.Server;
import net.nightcodes.androidchess.server.ServerThread;
import net.nightcodes.androidchess.server.broadcast.BroadcastSender;

public class Game extends AppCompatActivity {

    private Thread serverThread;
    private final BroadcastSender broadcast;

    private final Server server;

    public Game(Server server) {
        this.server = server;
        this.broadcast = new BroadcastSender(server, 4445);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        serverThread = new Thread(new ServerThread(server));
        serverThread.setName("server");
        serverThread.start();
    }

    public Thread getServerThread() {
        return serverThread;
    }

    public BroadcastSender getBroadcast() {
        return broadcast;
    }

    public Server getServer() {
        return server;
    }
}