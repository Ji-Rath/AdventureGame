package com.example.gareth_aguilar.adventuregame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateCharacters extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_characters);

        Button btn_continue = findViewById(R.id.btn_continue);
        Button btn_back = findViewById(R.id.btn_back);
        final EditText txtinput_1 = findViewById(R.id.txtinput_1);
        final EditText txtinput_2 = findViewById(R.id.txtinput_2);
        final EditText txtinput_3 = findViewById(R.id.txtinput_3);
        final EditText txtinput_4 = findViewById(R.id.txtinput_4);
        final TextFileSave txt = new TextFileSave();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] p = {txtinput_1.toString(),txtinput_2.toString(),txtinput_3.toString()};
                txt.saveName(p);
                openMainActivity();
            }
        });
    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainGame.class);
        startActivity(intent);
    }
}
