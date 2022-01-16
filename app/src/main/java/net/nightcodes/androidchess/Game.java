package net.nightcodes.androidchess;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.nightcodes.androidchess.server.Server;
import net.nightcodes.androidchess.server.ServerThread;
import net.nightcodes.androidchess.server.broadcast.BroadcastSender;

public class Game extends AppCompatActivity {

    private Thread serverThread;
    private BroadcastSender broadcast;

    private Server server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

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