package com.example.gareth_aguilar.adventuregame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainGame extends AppCompatActivity {
    private TextView txt_1, txt_2;
    private AdventureGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_m1 = findViewById(R.id.btn_main1);
        Button btn_m2 = findViewById(R.id.btn_main2);
        txt_1 = findViewById(R.id.txt_main);
        txt_2 = findViewById(R.id.txt_status);
        game = new AdventureGame();
        game.randomEvent(txt_1, btn_m1, btn_m2);

        btn_m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatsActivity();
            }
        });

        btn_m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public void openStatsActivity() {
        Intent intent = new Intent(this, CreateCharacters.class);
        startActivity(intent);
    }

    public void setMainText(String str) {
        txt_1.setText(str);
    }
    public void setStatusText(String str) {
        txt_2.setText(str);
    }
}
