package com.example.gareth_aguilar.adventuregame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static Button btn_start, btn_load, btn_quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        btn_start = findViewById(R.id.btn_start);
        btn_load = findViewById(R.id.btn_load);
        btn_quit = findViewById(R.id.btn_quit);

        btn_start.setText("Start Adventure");

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCharacterActivity();
            }
        });

        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public void openCharacterActivity() {
        Intent intent = new Intent(this, CreateCharacters.class);
        startActivity(intent);
    }
}
