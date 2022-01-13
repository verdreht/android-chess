package net.nightcodes.androidchess;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.nightcodes.androidchess.databinding.ActivityMainBinding;
import net.nightcodes.androidchess.game.Board;
import net.nightcodes.androidchess.game.logic.movement.exception.IllegalLocationException;
import net.nightcodes.androidchess.server.Server;
import net.nightcodes.androidchess.server.ServerThread;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final Server server = Server.getInstance(2710);

    //Buttons
    private Button hostGame;
    private Button joinGame;

    private final Board board = Board.getInstance();

    private Thread serverThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch);
        hostGame = findViewById(R.id.btn_hostGame);
        joinGame = findViewById(R.id.btn_joinGame);



        serverThread = new Thread(new ServerThread(server));
        serverThread.setName("server");
        serverThread.start();

        try {
            board.setup();
        } catch (IllegalLocationException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Board:\n" + board.toString());
        }).start();

        //Host Game
        hostGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onHostGame();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onHostGame() {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }
}
