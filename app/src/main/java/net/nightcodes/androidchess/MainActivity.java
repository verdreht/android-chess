package net.nightcodes.androidchess;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import net.nightcodes.androidchess.server.Server;

//import net.nightcodes.androidchess.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //private ActivityMainBinding binding;

    //Buttons
    private Button hostGame;
    private Button joinGame;
    private Button testGame;
    private Button joinHostButton;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch);

        hostGame = findViewById(R.id.btn_hostGame);
        joinGame = findViewById(R.id.btn_joinGame);
        testGame = findViewById(R.id.btn_testGame);

        //Host Game
        hostGame.setOnClickListener(view -> onHostGame());

        joinGame.setOnClickListener(view -> scanGames());

        //TEST chess-board
        testGame.setOnClickListener(view -> onTestGame());

        Constants.initBoard();
        Constants.setServer(new Server(, 2710));
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

    //Test chess board functionality
    public void onTestGame() {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }
}
