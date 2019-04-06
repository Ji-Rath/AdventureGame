package com.example.gareth_aguilar.adventuregame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class Campfire extends AppCompatActivity {
    private Button btn_status, btn_travel, btn_rest;
    private ProgressBar progress;
    private String[] p;
    private AdventureGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campfire);
        btn_status = findViewById(R.id.btn_status);
        btn_travel = findViewById(R.id.btn_travel);
        btn_rest = findViewById(R.id.btn_rest);
        progress = findViewById(R.id.pb_main);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        System.out.println(extra);
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

        btn_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEncounterActivity();
            }
        });
    }
    public void openEncounterActivity() {
        Intent intent = new Intent(this, Encounter.class);
        intent.putExtra("Game", game);
        startActivity(intent);
    }
}
