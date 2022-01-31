package net.nightcodes.androidchess.server;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.nightcodes.androidchess.Constants;
import net.nightcodes.androidchess.Game;
import net.nightcodes.androidchess.Host;
import net.nightcodes.androidchess.client.packet.BoardPacket;
import net.nightcodes.androidchess.client.packet.Packet;
import net.nightcodes.androidchess.client.packet.PacketType;

import org.snf4j.core.handler.AbstractStreamHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler extends AsyncTask<Socket, Void, Boolean> {

    @SuppressLint("StaticFieldLeak")
    private final AppCompatActivity activity;

    public ServerHandler(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Boolean doInBackground(Socket... sockets) {
        if(sockets.length > 0 && sockets[0] != null) {
            Socket socket = sockets[0];

            try {
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String line = reader.readLine();
                while(line != null) {
                    Packet packet = Packet.fromData(line);
                    Log.e("doInBackground() [SRV]:", line);

                    if(packet.getPacketType() == PacketType.SERVER_JOIN) {
                        Log.e("doInBackground() [SRV]:", "Join received: " + packet);
                        writer.println(new BoardPacket().build().toData());
                        Log.e("doInBackground() [SRV]:", new BoardPacket().build().toData());
                        activity.startActivity(new Intent(activity, Game.class));
                    }

                    line = reader.readLine();
                }

                writer.close();
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public AppCompatActivity getActivity() {
        return activity;
    }
}
