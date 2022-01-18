package net.nightcodes.androidchess;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.nightcodes.androidchess.server.Server;
import net.nightcodes.androidchess.server.broadcast.BroadcastSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game extends AppCompatActivity implements View.OnClickListener {

    //initialize fields -- START
    private Button field_a1;
    private Button field_a2;
    private Button field_a3;
    private Button field_a4;
    private Button field_a5;
    private Button field_a6;
    private Button field_a7;
    private Button field_a8;
    private Button field_b1;
    private Button field_b2;
    private Button field_b3;
    private Button field_b4;
    private Button field_b5;
    private Button field_b6;
    private Button field_b7;
    private Button field_b8;
    private Button field_c1;
    private Button field_c2;
    private Button field_c3;
    private Button field_c4;
    private Button field_c5;
    private Button field_c6;
    private Button field_c7;
    private Button field_c8;
    private Button field_d1;
    private Button field_d2;
    private Button field_d3;
    private Button field_d4;
    private Button field_d5;
    private Button field_d6;
    private Button field_d7;
    private Button field_d8;
    private Button field_e1;
    private Button field_e2;
    private Button field_e3;
    private Button field_e4;
    private Button field_e5;
    private Button field_e6;
    private Button field_e7;
    private Button field_e8;
    private Button field_f1;
    private Button field_f2;
    private Button field_f3;
    private Button field_f4;
    private Button field_f5;
    private Button field_f6;
    private Button field_f7;
    private Button field_f8;
    private Button field_g1;
    private Button field_g2;
    private Button field_g3;
    private Button field_g4;
    private Button field_g5;
    private Button field_g6;
    private Button field_g7;
    private Button field_g8;
    private Button field_h1;
    private Button field_h2;
    private Button field_h3;
    private Button field_h4;
    private Button field_h5;
    private Button field_h6;
    private Button field_h7;
    private Button field_h8;
    //initialize fields -- END
    private List<Button> fieldList = new ArrayList<>();

    private boolean isFirstClick = true;

    private Thread serverThread;
    private BroadcastSender broadcast;

    private Server server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        //instancing board!!

        //set button IDs -- START
        field_a1 = findViewById(R.id.field_a1);
        field_a2 = findViewById(R.id.field_a2);
        field_a3 = findViewById(R.id.field_a3);
        field_a4 = findViewById(R.id.field_a4);
        field_a5 = findViewById(R.id.field_a5);
        field_a6 = findViewById(R.id.field_a6);
        field_a7 = findViewById(R.id.field_a7);
        field_a8 = findViewById(R.id.field_a8);
        field_b1 = findViewById(R.id.field_b1);
        field_b2 = findViewById(R.id.field_b2);
        field_b3 = findViewById(R.id.field_b3);
        field_b4 = findViewById(R.id.field_b4);
        field_b5 = findViewById(R.id.field_b5);
        field_b6 = findViewById(R.id.field_b6);
        field_b7 = findViewById(R.id.field_b7);
        field_b8 = findViewById(R.id.field_b8);
        field_c1 = findViewById(R.id.field_c1);
        field_c2 = findViewById(R.id.field_c2);
        field_c3 = findViewById(R.id.field_c3);
        field_c4 = findViewById(R.id.field_c4);
        field_c5 = findViewById(R.id.field_c5);
        field_c6 = findViewById(R.id.field_c6);
        field_c7 = findViewById(R.id.field_c7);
        field_c8 = findViewById(R.id.field_c8);
        field_d1 = findViewById(R.id.field_d1);
        field_d2 = findViewById(R.id.field_d2);
        field_d3 = findViewById(R.id.field_d3);
        field_d4 = findViewById(R.id.field_d4);
        field_d5 = findViewById(R.id.field_d5);
        field_d6 = findViewById(R.id.field_d6);
        field_d7 = findViewById(R.id.field_d7);
        field_d8 = findViewById(R.id.field_d8);
        field_e1 = findViewById(R.id.field_e1);
        field_e2 = findViewById(R.id.field_e2);
        field_e3 = findViewById(R.id.field_e3);
        field_e4 = findViewById(R.id.field_e4);
        field_e5 = findViewById(R.id.field_e5);
        field_e6 = findViewById(R.id.field_e6);
        field_e7 = findViewById(R.id.field_e7);
        field_e8 = findViewById(R.id.field_e8);
        field_f1 = findViewById(R.id.field_f1);
        field_f2 = findViewById(R.id.field_f2);
        field_f3 = findViewById(R.id.field_f3);
        field_f4 = findViewById(R.id.field_f4);
        field_f5 = findViewById(R.id.field_f5);
        field_f6 = findViewById(R.id.field_f6);
        field_f7 = findViewById(R.id.field_f7);
        field_f8 = findViewById(R.id.field_f8);
        field_g1 = findViewById(R.id.field_g1);
        field_g2 = findViewById(R.id.field_g2);
        field_g3 = findViewById(R.id.field_g3);
        field_g4 = findViewById(R.id.field_g4);
        field_g5 = findViewById(R.id.field_g5);
        field_g6 = findViewById(R.id.field_g6);
        field_g7 = findViewById(R.id.field_g7);
        field_g8 = findViewById(R.id.field_g8);
        field_h1 = findViewById(R.id.field_h1);
        field_h2 = findViewById(R.id.field_h2);
        field_h3 = findViewById(R.id.field_h3);
        field_h4 = findViewById(R.id.field_h4);
        field_h5 = findViewById(R.id.field_h5);
        field_h6 = findViewById(R.id.field_h6);
        field_h7 = findViewById(R.id.field_h7);
        field_h8 = findViewById(R.id.field_h8);
        //set button IDs -- END

        //add Buttons to List -- START
        this.fieldList.addAll(Arrays.asList(
                field_a1,field_a2,field_a3,field_a4,field_a5,field_a6,field_a7,field_a8,
                field_b1,field_b2,field_b3,field_b4,field_b5,field_b6,field_b7,field_b8,
                field_c1,field_c2,field_c3,field_c4,field_c5,field_c6,field_c7,field_c8,
                field_d1,field_d2,field_d3,field_d4,field_d5,field_d6,field_d7,field_d8,
                field_e1,field_e2,field_e3,field_e4,field_e5,field_e6,field_e7,field_e8,
                field_f1,field_f2,field_f3,field_f4,field_f5,field_f6,field_f7,field_f8,
                field_g1,field_g2,field_g3,field_g4,field_g5,field_g6,field_g7,field_g8,
                field_h1,field_h2,field_h3,field_h4,field_h5,field_h6,field_h7,field_h8
                ));
        //add Buttons to List -- END

        setOnClickListenerForAllFields(this.fieldList);


    }

    public Thread getServerThread() {
        return serverThread;
    }

    public BroadcastSender getBroadcast() {
        return broadcast;
    }

    public Server getServer() {
        return server;
    }

    @Override
    public void onClick(View view) {
        if (isFirstClick) {
            if (view instanceof Button) {
                //get ButtonID
                //get Background of button
                //set isFirstClick
            }

        }
    }

    /*
    set OnClickListener for all fields
     */
    public void setOnClickListenerForAllFields(List<Button> buttonList) {
        for (Button field : buttonList) {
            field.setOnClickListener(this);
        }
    }
}