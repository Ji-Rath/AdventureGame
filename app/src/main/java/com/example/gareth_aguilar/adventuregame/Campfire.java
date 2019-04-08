package com.example.gareth_aguilar.adventuregame;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Campfire extends AppCompatActivity {
    private Button btn_status, btn_travel, btn_rest, btn_mainmenu;
    private TextView txt_time;
    private ProgressBar progress;
    private String[] p;
    private AdventureGame game;
    private ImageView img_camp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campfire);
        btn_status = findViewById(R.id.btn_status);
        btn_travel = findViewById(R.id.btn_travel);
        btn_mainmenu = findViewById(R.id.btn_mainmenu);
        btn_rest = findViewById(R.id.btn_rest);
        progress = findViewById(R.id.pb_main);
        txt_time = findViewById(R.id.txt_camptime);
        img_camp = findViewById(R.id.img_camp);
        img_camp.setBackgroundResource(R.drawable.campanimation);
        AnimationDrawable anim = (AnimationDrawable) img_camp.getBackground();
        anim.start();


        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        if(extra != null) {
            System.out.println();
            if(extra.containsKey("Players")) {
                p = (String[]) intent.getSerializableExtra("Players");
                game = new AdventureGame(p);
            }
            if(extra.containsKey("Game")) {
                game = (AdventureGame) intent.getSerializableExtra("Game");
            }
        }

        progress.setProgress(game.getProgress(),true);
        txt_time.setText(game.getTime()+" | "+game.getLocation());

        btn_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTravelActivity();
            }
        });

        btn_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatusActivity();
            }
        });

        btn_rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRestActivity();
            }
        });

        btn_mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainmenuActivity();
            }
        });
    }
    public void openTravelActivity() {
        Intent intent = new Intent(this, Traveling.class);
        intent.putExtra("Game", game);
        startActivity(intent);
    }

    public void openRestActivity() {
        Intent intent = new Intent(this, Rest.class);
        intent.putExtra("Game", game);
        startActivity(intent);
    }

    public void openStatusActivity() {
        Intent intent = new Intent(this, PlayerStatus.class);
        intent.putExtra("Game", game);
        startActivity(intent);
    }
    public void openMainmenuActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
