package com.example.gareth_aguilar.adventuregame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class CreateCharacters extends AppCompatActivity implements Serializable {
    private Button btn_continue, btn_back;
    private EditText txtinput_1, txtinput_2, txtinput_3;
    private String[] p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_characters);

        p = new String[3];
        btn_continue = findViewById(R.id.btn_continue);
        btn_back = findViewById(R.id.btn_back);
        txtinput_1 = findViewById(R.id.txtinput_1);
        txtinput_2 = findViewById(R.id.txtinput_2);
        txtinput_3 = findViewById(R.id.txtinput_3);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuActivity();
            }
        });

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p[0] = txtinput_1.getText().toString();
                p[1] = txtinput_2.getText().toString();
                p[2] = txtinput_3.getText().toString();
                openMainActivity();
            }
        });
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, Campfire.class);
        intent.putExtra("Players", p);
        startActivity(intent);
    }

    public void openMenuActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
