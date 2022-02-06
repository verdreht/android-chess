package net.nightcodes.androidchess.server;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.nightcodes.androidchess.Constants;
import net.nightcodes.androidchess.Game;
import net.nightcodes.androidchess.client.packet.BoardPacket;
import net.nightcodes.androidchess.client.packet.Packet;
import net.nightcodes.androidchess.client.packet.PacketType;
import net.nightcodes.androidchess.game.logic.board.Board;
import net.nightcodes.androidchess.game.logic.board.EntityColor;
import net.nightcodes.androidchess.game.logic.board.listener.BoardChangeListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler extends AsyncTask<Socket, Void, Boolean> {

    @SuppressLint("StaticFieldLeak")
    private final AppCompatActivity activity;

    private Socket socket;

    public ServerHandler(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Boolean doInBackground(Socket... sockets) {
        if(sockets.length > 0 && sockets[0] != null) {
            this.socket = sockets[0];

            try {
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String line = reader.readLine();
                while(line != null) {
                    Packet packet = Packet.fromData(line);
                    Log.e("doInBackground() [SRV]:", line);

                    if(packet.getPacketType() == PacketType.SERVER_JOIN) {
                        Log.e("doInBackground() [SRV]:", "Join received: " + packet);
                        writer.println(new BoardPacket(Constants.getBoard()).build().toData());
                        Log.e("doInBackground() [SRV]:", new BoardPacket(Constants.getBoard()).build().toData());
                        activity.startActivity(new Intent(activity, Game.class));

                        Constants.getBoard().setCurrentTurn(EntityColor.WHITE);
                        Log.e("isServerOnTurn", Constants.getBoard().getCurrentTurn().name());
                    } else {
                        if(packet.getPacketType() == PacketType.WAIT_FOR_SERVER_TURN) {
                            Log.e("waitForServerTurn", "Got request from client: It is waiting for a response of me.");
                            //Register new BoardChangeListener
                            Constants.getBoardEventManager().registerListeners(new BoardChangeListener(this));

                        }
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

    public void responseBoard(BoardChangeListener event, Board board) {
        new Thread(() -> {
            if(!socket.isOutputShutdown()) {
                try (PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
                    Log.e("wosnDoDrinHeast",new BoardPacket(board).build().toData());
                    writer.println(new BoardPacket(board).build().toData());
                    Log.e("serverSentBoardBack", "Server sent the board back to the client");
                    Constants.getBoardEventManager().unregisterListener(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public AppCompatActivity getActivity() {
        return activity;
    }

}
