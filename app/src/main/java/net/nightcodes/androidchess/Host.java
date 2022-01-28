package net.nightcodes.androidchess;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.nightcodes.androidchess.server.Server;
import net.nightcodes.androidchess.server.ServerThread;
import net.nightcodes.androidchess.server.broadcast.BroadcastSender;
import net.nightcodes.androidchess.server.broadcast.BroadcastThread;

public class Host extends AppCompatActivity {

    private static final Server server = Server.getInstance(2710);
    private static Thread broadcastThread;
    private static BroadcastThread serverRunnable;

    private Thread serverThread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.host);

        EditText serverName = findViewById(R.id.serverName);
        Button submitButton = findViewById(R.id.btn_lobby_create);

        submitButton.setOnClickListener(v -> {
            if(!serverName.getText().toString().isEmpty()) {
                String input = serverName.getText().toString();
                server.setServerName(input);

                BroadcastSender sender = new BroadcastSender(server, 4445);

                serverRunnable = new BroadcastThread(sender, 5);

                broadcastThread = new Thread(serverRunnable);
                broadcastThread.start();

                submitButton.setEnabled(false);
                serverName.setEnabled(false);

                ProgressBar spinner = findViewById(R.id.loader);
                TextView spinnerText = findViewById(R.id.loader_text);

                spinner.setVisibility(View.VISIBLE);
                spinnerText.setVisibility(View.VISIBLE);

                serverThread = new Thread(new ServerThread(this, server));
                serverThread.start();

            } else {
                serverName.setError("Please select a lobby name.");
            }
        });



    }

    public void startServer(Server server) {

    }

    public void addLogEntry(String log) {
        runOnUiThread(() -> {
            LinearLayout linearLayout = findViewById(R.id.logger);
            TextView logEntry = new TextView(this);
            logEntry.setText(log);
            logEntry.setTextColor(Color.WHITE);
            logEntry.setTextSize(16);
            logEntry.setPadding(10, 10,0,0);
            linearLayout.addView(logEntry);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(serverRunnable != null) {
            serverRunnable.setBroadcasting(false);
        }
    }
}
