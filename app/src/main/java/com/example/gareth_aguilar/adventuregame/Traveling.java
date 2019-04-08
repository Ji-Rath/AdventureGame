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
import java.util.Timer;
import java.util.TimerTask;

public class Traveling extends AppCompatActivity {

    private Button btn_travel, btn_restup;
    private ProgressBar pb_travel, pb_travelbar;
    private TextView txt_minorevent, txt_details;
    private AdventureGame game;
    private Random rand;
    private final long delay = 3000L;
    Timer timer;
    TimerTask task;
    private ImageView img_travel_p1, img_travel_p2, img_travel_p3;
    private AnimationDrawable anim1, anim2, anim3;
    private int p, p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traveling);

        Intent intent = getIntent();
        game = (AdventureGame) intent.getSerializableExtra("Game");

        btn_travel = findViewById(R.id.btn_travel);
        btn_restup = findViewById(R.id.btn_restup);
        txt_minorevent = findViewById(R.id.txt_minorevent);
        txt_details = findViewById(R.id.txt_details2);
        pb_travel = findViewById(R.id.pb_travel);
        pb_travelbar = findViewById(R.id.pb_travelbar);
        timer = new Timer();
        rand = new Random();
        pb_travelbar.setProgress(game.getProgress());

        img_travel_p1 = findViewById(R.id.img_travel_p1);
        img_travel_p2 = findViewById(R.id.img_travel_p2);
        img_travel_p3 = findViewById(R.id.img_travel_p3);

        img_travel_p1.setBackgroundResource(R.drawable.player1animation);
        anim1 = (AnimationDrawable) img_travel_p1.getBackground();


        img_travel_p2.setBackgroundResource(R.drawable.player2animation);
        anim2 = (AnimationDrawable) img_travel_p2.getBackground();

        img_travel_p3.setBackgroundResource(R.drawable.player3animation);
        anim3 = (AnimationDrawable) img_travel_p3.getBackground();

        if(!game.getPlayer(0).isAvailable()) {
            img_travel_p1.setVisibility(View.INVISIBLE);
        }
        if(!game.getPlayer(1).isAvailable()) {
            img_travel_p2.setVisibility(View.INVISIBLE);
        }
        if(!game.getPlayer(2).isAvailable()) {
            img_travel_p3.setVisibility(View.INVISIBLE);
        }

        task = new TimerTask() {
            public void run() {
                finishedWalking();
            }
        };

        btn_restup.setVisibility(View.VISIBLE);
        btn_travel.setVisibility(View.VISIBLE);
        pb_travel.setVisibility(View.INVISIBLE);

        Bundle extra = intent.getExtras();
        if(!extra.containsKey("Encounter")) {
            timer.schedule(task,delay);
            btn_restup.setVisibility(View.INVISIBLE);
            btn_travel.setVisibility(View.INVISIBLE);
            pb_travel.setVisibility(View.VISIBLE);
            anim1.start();
            anim2.start();
            anim3.start();
        }

        btn_restup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCampfireActivity();
            }
        });

        btn_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_restup.setVisibility(View.INVISIBLE);
                btn_travel.setVisibility(View.INVISIBLE);
                pb_travel.setVisibility(View.VISIBLE);
                txt_details.setText("");
                txt_minorevent.setText("");
                task = new TimerTask() {
                    public void run() {
                        finishedWalking();
                    }
                };
                timer.schedule(task, delay);
                anim1.start();
                anim2.start();
                anim3.start();
            }
        });


    }

    public void openCampfireActivity() {
        Intent intent = new Intent(this, Campfire.class);
        intent.putExtra("Game", game);
        startActivity(intent);
    }
    public void openEncounterActiviy() {
        Intent intent = new Intent(this, Encounter.class);
        intent.putExtra("Game", game);
        startActivity(intent);
    }

    public void finishedWalking() {
        runOnUiThread(new Runnable() {
            public void run() {
                for(int i=0;i<3;i++) {
                    String str = game.getPlayer(i).subtractTimer();
                    game.getPlayer(i).addSanity(-10);
                    str += game.getPlayer(i).removeRandomIllness();
                    txt_details.setText(str);
                    btn_restup.setVisibility(View.VISIBLE);
                    btn_travel.setVisibility(View.VISIBLE);
                    pb_travel.setVisibility(View.INVISIBLE);
                    anim1.stop();
                    anim2.stop();
                    anim3.stop();
                    if(!str.equals("")) {
                        i = 3;
                    }
                }
                if(txt_details.getText().toString().equals("")) {
                    switch (rand.nextInt(3)) {
                        case 0:
                            btn_restup.setVisibility(View.VISIBLE);
                            btn_travel.setVisibility(View.VISIBLE);
                            pb_travel.setVisibility(View.INVISIBLE);
                            txt_details.setText(game.addProgress(5)+game.checkSanity()+game.checkHealth());
                            pb_travelbar.setProgress(game.getProgress());
                            anim1.stop();
                            anim2.stop();
                            anim3.stop();
                            break;
                        case 1:
                            openEncounterActiviy();
                            break;
                        case 2:
                            btn_restup.setVisibility(View.VISIBLE);
                            btn_travel.setVisibility(View.VISIBLE);
                            pb_travel.setVisibility(View.INVISIBLE);
                            p = rand.nextInt(2);
                            while (!game.getPlayer(p).isAvailable()) {
                                p = rand.nextInt(2);
                            }
                            p2 = rand.nextInt(2);
                            if (game.getPlayerCount()>1 && !game.getPlayer(p2).isAvailable()) {
                                while (!game.getPlayer(p2).isAvailable()) {
                                    p2 = rand.nextInt(2);
                                }
                            } else {
                                p2 = p;
                            }
                            game.minorRandomEvent(p, p2, txt_minorevent, txt_details);
                            pb_travelbar.setProgress(game.getProgress());
                            anim1.stop();
                            anim2.stop();
                            anim3.stop();
                            break;
                    }
                }
                if(!game.getPlayer(0).isAvailable()) {
                    img_travel_p1.setVisibility(View.INVISIBLE);
                }
                if(!game.getPlayer(1).isAvailable()) {
                    img_travel_p2.setVisibility(View.INVISIBLE);
                }
                if(!game.getPlayer(2).isAvailable()) {
                    img_travel_p3.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
