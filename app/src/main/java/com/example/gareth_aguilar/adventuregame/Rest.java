package com.example.gareth_aguilar.adventuregame;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class Rest extends AppCompatActivity {
    private Button btn_rest_continue;
    private TextView txt_time, txt_rest;
    private AdventureGame game;
    private Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        btn_rest_continue = findViewById(R.id.btn_rest_continue);
        txt_time = findViewById(R.id.txt_time);
        txt_rest = findViewById(R.id.txt_rested);
        rand = new Random();


        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        if(extra != null) {
            game = (AdventureGame) intent.getSerializableExtra("Game");
        }

        game.addDay();
        for(int i=0;i<3;i++) {
            game.getPlayer(i).addSanity(40);
        }

        switch(rand.nextInt(6)) {
            case 0:
                txt_rest.setText("\tThe sun is shining and the birds are chirping. Everybody wakes up peacefully and ready for the day ahead.");
                break;
            case 1:
                txt_rest.setText("\tAlthough there were ominous sounds emmiting from the darkness, nobody was abducted. "+game.getPlayer(0).getName()+" specifically is ambitious to continue on the journey.");
                break;
            case 2:
                txt_rest.setText("\tThe group is suddenly awoken by the sounds of thunder. They unwillingly arise and continue their march.");
                break;
            case 3:
                txt_rest.setText("\tA rather peaceful chirping awakes the group. A calm path awaits them.");
                break;
            case 4:
                txt_rest.setText("\t"+game.getPlayer(0).getName()+", awoken by a nightmare lets out a screeching yell. The startled group dash out into the wild. An unsettling journey awaits them");
                break;
            case 5:
                txt_rest.setText("\tThe adventurers wake up rather easily to a sunny and windy day. They appear ready to continue their journey.");
                break;
        }

        txt_time.setText(game.getTime()+" | "+game.getLocation());

        btn_rest_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCampfireActivity();
            }
        });
    }
    public void openCampfireActivity() {
        Intent intent = new Intent(this, Campfire.class);
        intent.putExtra("Game", game);
        startActivity(intent);
    }
}
