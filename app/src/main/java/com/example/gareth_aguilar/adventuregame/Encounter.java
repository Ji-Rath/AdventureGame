package com.example.gareth_aguilar.adventuregame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Encounter extends AppCompatActivity {
    private TextView txt_main, txt_details;
    private AdventureGame game;
    private Button btn_left, btn_right, btn_return;
    private ImageView img_main;
    private int encounter = 0;
    private Random rand;
    private int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter);

        rand = new Random();
        img_main = findViewById(R.id.img_main);
        btn_left = findViewById(R.id.btn_main1);
        btn_right = findViewById(R.id.btn_main2);
        btn_return = findViewById(R.id.btn_return);
        txt_main = findViewById(R.id.txt_main);
        txt_details = findViewById(R.id.txt_details);

        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            game = (AdventureGame) intent.getSerializableExtra("Game");
            p = rand.nextInt(3);
            while (!game.getPlayer(p).isAvailable()) {
                p = rand.nextInt(3);
            }
            encounter = game.randomEvent(p, btn_left, btn_right, txt_main, img_main);
        }

        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str  = "";
                switch(encounter) {
                    case 0:
                        txt_main.setText("One of the thieves draw their knife, "+game.getPlayer(p).getName()+" battles it out with his fists.");
                        str += game.getPlayer(p).addHp(-30);
                        str += game.addProgress(-5);
                    break;
                    case 1:
                        txt_main.setText(game.getPlayer(p).getName()+" puts the berries down and carries on his merry way.");
                        str += game.addProgress(5);
                        break;
                    case 2:
                        txt_main.setText("The ninjas throw their smokescreen and instantly disappear into the darkness, leaving behind some rations.");
                        str += game.addFood(6);
                        str += game.addProgress(3);
                        break;
                    case 3:
                        txt_main.setText(game.getPlayer(p).getName()+" walks away. The dog gives puppy eyes and "+game.getPlayer(p).getName()+" is now left feeling regretful.");
                        str += game.getPlayer(p).addSanity(-15);
                        str += game.addProgress(2);
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
                        txt_main.setText("The men gladly accept the offering and allow you to pass");
                        str += game.addFood(-15);
                        str += game.addMoney(-5000);
                        str += game.addProgress(10);
                    break;
                    case 1:
                        txt_main.setText(game.getPlayer(p).getName()+" shoves a handful of berries into their mouth");
                        str += game.getPlayer(p).giveDisease(4);
                        break;
                    case 2:
                        txt_main.setText(game.getPlayer(p).getName()+" gets diced up into little tiny little square pieces");
                        str += game.getPlayer(p).addHp(-100);
                        break;
                    case 3:
                        txt_main.setText(game.getPlayer(p).getName()+" pets the dog and gets their hand bitten off");
                        str += game.getPlayer(p).addHp(-40);
                        str += game.addProgress(2);
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
                openTravelActivity();
            }
        });
    }

    public void openTravelActivity() {
        Intent intent = new Intent(this, Traveling.class);
        intent.putExtra("Game", game);
        intent.putExtra("Encounter", true);
        startActivity(intent);
    }
}
