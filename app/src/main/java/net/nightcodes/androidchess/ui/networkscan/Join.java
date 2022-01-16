package net.nightcodes.androidchess.ui.networkscan;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.nightcodes.androidchess.R;
import net.nightcodes.androidchess.server.broadcast.BroadcastReceiver;
import net.nightcodes.androidchess.ui.networkscan.view.ItemAdapter;

import java.util.concurrent.TimeUnit;

public class Join extends AppCompatActivity {

    BroadcastReceiver receiver = null;

    private RecyclerView scannedHosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        scannedHosts = findViewById(R.id.host_list);

        new Thread(() -> {
            receiver = new BroadcastReceiver(this, 4445);
            receiver.startListening();
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ItemAdapter adapter = new ItemAdapter(receiver.getServerFound());
        scannedHosts.setAdapter(adapter);
        scannedHosts.setLayoutManager(new LinearLayoutManager(this));

        AdapterManager.setAdapter(adapter);
    }

    public RecyclerView getScannedHosts() {
        return scannedHosts;
    }
}