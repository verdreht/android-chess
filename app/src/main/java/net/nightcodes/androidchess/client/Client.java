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

                //set moveLock for Client (has to wait for the Server's first move)
                Constants.setMoveLock(true);

                while(alive) {
                    if(!queue.isEmpty()) {
                        Log.e("doInBackground[CLIENT]:", "New queue object is executing.");

                        Packet packet = queue.poll();
                        if(packet != null) {
                            writer.println(packet.toData());
                            String content = reader.readLine();
                            Log.e("doInBackground[CLIENT]:", "->" + content);

                            if(content != null) {
                                Packet response = Packet.fromData(content);
                                response.setID(packet.getID());

                                publishProgress(response);
                            }
                        }
                    }
                }

            } catch (IOException e) {
                Log.e("doInBackGround[CLIENT]:", e.getMessage());
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
//                Constants.setBoard(board);
                Constants.setTempBoardForUI(board);
//                if (!Constants.isRefreshUI()) {
//                    Constants.setRefreshUI(true);
//                }
                Log.e("seeeega", board.getCurrentTurn().name());
                if(board.getCurrentTurn() == EntityColor.WHITE) {
                    queue.add(new WaitForServerTurnPacket().build());
                    Log.e("waitForServerTurn", "Waiting for turn of server.");
                }
//                else if (board.getCurrentTurn() == EntityColor.BLACK) {
//                    Constants.setMoveLock(false);
//                    Log.e("isOnTurn[CLIENT]:", "CLIENT is on turn");
//                }

                //TODO: Implement Game reference

                Log.e("onProgressUpdate():", "Board received: " + board.toJson());
            }
        }
    }

//    public void responseBoard(BoardChangeListener event, Board board) {
//        new Thread(() -> {
//            if(!socket.isOutputShutdown()) {
//                try (PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
//                    Log.e("wosnDoDrinHeast",new BoardPacket(board).build().toData());
//                    board.setCurrentTurn(EntityColor.WHITE);
//                    writer.println(new BoardPacket(board).build().toData());
//                    Log.e("clientSentBoardBack", "Client sent the board back to the server");
//                    Constants.getBoardEventManager().unregisterListener(event);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();
//    }

    public void sendPacket(Packet packet) {
        this.queue.add(packet);
    }
}
