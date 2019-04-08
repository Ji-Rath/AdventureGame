package com.example.gareth_aguilar.adventuregame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayerStatus extends AppCompatActivity {

    private Button btn_return;
    private TextView txt_p1, txt_p2, txt_p3, txt_resources;
    private TextView[] playerText;
    private int playerInjured[];
    private AdventureGame game;
    private ImageView imgPortriat[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        Intent intent = getIntent();
        game = (AdventureGame) intent.getSerializableExtra("Game");
        playerText = new TextView[3];
        imgPortriat = new ImageView[3];
        playerInjured = new int[3];

        btn_return = findViewById(R.id.btn_return);
        txt_p1 = findViewById(R.id.txt_player1);
        txt_p2 = findViewById(R.id.txt_player2);
        txt_p3 = findViewById(R.id.txt_player3);
        txt_resources = findViewById(R.id.txt_resources);
        imgPortriat[0] = findViewById(R.id.img_player1);
        imgPortriat[1] = findViewById(R.id.img_player2);
        imgPortriat[2] = findViewById(R.id.img_player3);
        playerInjured[0] = R.drawable.player1hurt;
        playerInjured[1] = R.drawable.player2hurt;
        playerInjured[2] = R.drawable.player3hurt;

        playerText[0] = txt_p1;
        playerText[1] = txt_p2;
        playerText[2] = txt_p3;

        for(int i=0;i<playerText.length;i++) {
            playerText[i].setText(game.getPlayer(i).toString());
            if(game.getPlayer(i).hasDisease() || game.getPlayer(i).getHp() < 50) {
                imgPortriat[i].setImageResource(playerInjured[i]);
            }

            if (game.getPlayer(i).isAlien()) {
                imgPortriat[i].setImageResource(R.drawable.alien);
            }
        }

        txt_resources.setText("MONEY: "+game.getMoney()+" YEN"
            +"\nRATIONS: "+game.getFood()+" FOOD");

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuActivity();
            }
        });


    }

    public void openMenuActivity() {
        Intent intent = new Intent(this, Campfire.class);
        intent.putExtra("Game", game);
        startActivity(intent);
    }
}
