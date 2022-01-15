package net.nightcodes.androidchess;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.nightcodes.androidchess.server.broadcast.BroadcastReceiver;

public class Join extends AppCompatActivity {

    BroadcastReceiver receiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        new Thread(() -> {
            receiver = new BroadcastReceiver(4445);
            receiver.startListening();
        }).start();
    }
}