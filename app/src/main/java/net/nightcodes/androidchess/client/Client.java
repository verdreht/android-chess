package net.nightcodes.androidchess.client;

 import android.os.AsyncTask;
import android.util.Log;

import net.nightcodes.androidchess.Constants;
import net.nightcodes.androidchess.client.packet.Packet;
import net.nightcodes.androidchess.client.packet.PacketType;
 import net.nightcodes.androidchess.client.packet.WaitForServerTurnPacket;
 import net.nightcodes.androidchess.game.logic.board.Board;
 import net.nightcodes.androidchess.game.logic.board.EntityColor;

 import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class Client extends AsyncTask<ConnectionDetails, Packet, Packet> {

    private Socket socket;
    private Queue<Packet> queue = new LinkedList<>();

    private boolean alive = true;

    @Override
    protected void onPostExecute(Packet json) {
        super.onPostExecute(json);
    }

    @Override
    protected Packet doInBackground(ConnectionDetails... connectionDetails) {
        if(connectionDetails != null && connectionDetails[0] != null) {
            ConnectionDetails details = connectionDetails[0];
            try {
                socket = new Socket(details.getAddress(), details.getPort());

                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                while(alive) {
                    if(!queue.isEmpty()) {
                        Log.e("doInBackground():", "New queue object is executing.");

                        Packet packet = queue.poll();
                        if(packet != null) {
                            writer.println(packet.toData());
                            String content = reader.readLine();
                            Log.e("doInNegaBeidl():", "->" + content);

                            if(content != null) {
                                Packet response = Packet.fromData(content);
                                response.setID(packet.getID());

                                publishProgress(response);
                            }
                        }
                    }
                }

            } catch (IOException e) {
                Log.e("doInBackGround():", e.getMessage());
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Packet... packets) {
        super.onProgressUpdate(packets);
        Log.e("onProgressUpdate():", "Progress submitted");

        if(packets != null && packets[0] != null) {
            Packet response = packets[0];

            if(response.getPacketType() == PacketType.SENDING_BOARD) {
                Board board = Board.fromJson(response.getJsonElement().getAsJsonObject());
                Constants.setBoard(board);
                Log.e("seeeega", board.getCurrentTurn().name());
                if(board.getCurrentTurn() == EntityColor.WHITE) {
                    queue.add(new WaitForServerTurnPacket().build());
                    Log.e("waitForServerTurn", "Waiting for turn of server.");
                }

                //TODO: Implement Game reference

                Log.e("onProgressUpdate():", "Board received: " + board.toJson());
            }
        }
    }

    public void sendPacket(Packet packet) {
        this.queue.add(packet);
    }
}
