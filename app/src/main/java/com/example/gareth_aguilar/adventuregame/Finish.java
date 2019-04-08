package com.example.gareth_aguilar.adventuregame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Finish extends AppCompatActivity {
    private Button btn_finish_continue;
    private TextView txt_time2, txt_finish;
    private ImageView img_finish;
    private AdventureGame game;
    private Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        btn_finish_continue = findViewById(R.id.btn_finish_continue);
        txt_time2 = findViewById(R.id.txt_time2);
        txt_finish = findViewById(R.id.txt_finish);
        img_finish = findViewById(R.id.img_finish);
        rand = new Random();


        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        if(extra != null) {
            game = (AdventureGame) intent.getSerializableExtra("Game");
        }

        if(game.getProgress() >= 100) {
            img_finish.setImageResource(R.drawable.merchant);
        }

        txt_finish.setText("\tWell, this is the end isn't it? Thank you for playing whether you ultimately died or managed to reach your destination.");

        txt_time2.setText(game.getTime()+" | "+game.getLocation());

        btn_finish_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuActivity();
            }
        });
    }
    public void openMenuActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
