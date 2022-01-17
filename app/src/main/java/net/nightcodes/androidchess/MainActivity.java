package net.nightcodes.androidchess;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import net.nightcodes.androidchess.game.Board;
import net.nightcodes.androidchess.game.entity.listener.EventManager;
import net.nightcodes.androidchess.game.entity.listener.LocationChangeListenerTest;
import net.nightcodes.androidchess.game.logic.movement.exception.IllegalLocationException;
import net.nightcodes.androidchess.ui.networkscan.Join;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    //private ActivityMainBinding binding;

    //Buttons
    private Button hostGame;
    private Button joinGame;

    private final EventManager eventManager = new EventManager();
    private final Board board = Board.getInstance(eventManager);

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch);
        hostGame = findViewById(R.id.btn_hostGame);
        joinGame = findViewById(R.id.btn_joinGame);
        
        try {
            board.setup();
        } catch (IllegalLocationException e) {
            e.printStackTrace();
        }

        eventManager.registerListener(new LocationChangeListenerTest());

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

        joinGame.setOnClickListener(view -> scanGames());
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

    public void scanGames() {
        Intent intent = new Intent(this, Join.class);
        startActivity(intent);
    }

    public void onHostGame() {
        Intent intent = new Intent(this, Host.class);
        startActivity(intent);
    }

}
