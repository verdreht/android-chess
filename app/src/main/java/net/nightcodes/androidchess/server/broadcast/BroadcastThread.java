package net.nightcodes.androidchess.server.broadcast;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BroadcastThread implements Runnable {

    private final BroadcastSender broadcastClient;
    private final int interval;

    private boolean broadcasting;

    public BroadcastThread(BroadcastSender broadcastClient, int interval) {
        this.broadcastClient = broadcastClient;
        this.interval = interval;

        this.broadcasting = true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {
        while (broadcasting) {
            try {
                broadcastClient.performBroadcast();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isBroadcasting() {
        return broadcasting;
    }

    public void setBroadcasting(boolean broadcasting) {
        this.broadcasting = broadcasting;
    }
}
