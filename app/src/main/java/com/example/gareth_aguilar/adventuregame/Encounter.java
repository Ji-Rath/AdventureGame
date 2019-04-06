package com.example.gareth_aguilar.adventuregame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Encounter extends AppCompatActivity {
    private TextView txt_main, txt_details;
    private AdventureGame game;
    private Button btn_left, btn_right, btn_return;
    private int encounter = 0;
    private Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter);

        rand = new Random();
        btn_left = findViewById(R.id.btn_main1);
        btn_right = findViewById(R.id.btn_main2);
        btn_return = findViewById(R.id.btn_return);
        txt_main = findViewById(R.id.txt_main);
        txt_details = findViewById(R.id.txt_details);

        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            game = (AdventureGame) intent.getSerializableExtra("Game");
            encounter = game.randomEvent(btn_left, btn_right, txt_main);
        }

        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str  = "";
                switch(encounter) {
                    case 0:
                    int p = rand.nextInt(2);
                    txt_main.setText("One of the thieves draw their knife, leading to an intense battle. "
                            + game.getPlayer(p).getName() + " gets injured in the process");
                    str += game.getPlayer(p).damagePlayer(30);
                    str += game.addProgress(-5);
                    break;
                }
                txt_details.setText(str);
                btn_left.setVisibility(View.INVISIBLE);
                btn_right.setVisibility(View.INVISIBLE);
                btn_return.setVisibility(View.VISIBLE);
            }
        });
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "";
                switch(encounter) {
                    case 0:
                    txt_main.setText("The men accept the offering and allow you to pass.");
                    str += game.takeFood(15);
                    str += game.takeMoney(5000);
                    str += game.addProgress(10);
                    break;
                }
                txt_details.setText(str);
                btn_left.setVisibility(View.INVISIBLE);
                btn_right.setVisibility(View.INVISIBLE);
                btn_return.setVisibility(View.VISIBLE);
            }
        });

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCampActivity();
            }
        });
    }

    public void openCampActivity() {
        Intent intent = new Intent(this, Campfire.class);
        intent.putExtra("Game", game);
        startActivity(intent);
    }
}
