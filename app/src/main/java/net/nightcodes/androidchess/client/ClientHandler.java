package net.nightcodes.androidchess.client;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import net.nightcodes.androidchess.client.packet.Packet;

import net.nightcodes.androidchess.R;
import org.snf4j.core.EndingAction;
import org.snf4j.core.handler.AbstractStreamHandler;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.session.DefaultSessionConfig;
import org.snf4j.core.session.ISessionConfig;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class ClientHandler extends AbstractStreamHandler {

    private final List<Packet> packetList;
    private final Context context;

    public ClientHandler(Context context, List<Packet> packetList) {
        this.packetList = packetList;
        this.context = context;
    }

    @Override
    public void read(byte[] data) {
        String content = new String(data, StandardCharsets.UTF_8).trim();
        System.out.println(content);
    }

    @Override
    public void event(SessionEvent event) {
        Log.wtf("Sega", "nega");
        switch (event) {
            case READY:
                for(Packet packet : packetList) {
                    Log.e("hansi", packet.getPacketType().name());
                    System.out.println("WARUM IS DA SEGA A NEGA");
                    getSession().write((packet.toData()));
                }
                break;
            case CLOSED:
                Log.e("closed", "Client closed");
                new MaterialAlertDialogBuilder(context)
                        .setMessage("Server connection closed")
                        .setNegativeButton("test", (dialog, which) -> {
                            dialog.dismiss();
                        }).show();
                break;
            case ENDING:
                Log.e("test", "lol baka?");
        }
    }
}