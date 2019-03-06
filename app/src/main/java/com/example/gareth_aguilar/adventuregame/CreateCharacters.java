package com.example.gareth_aguilar.adventuregame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateCharacters extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_characters);

        Button btn_continue = findViewById(R.id.btn_continue);
        Button btn_back = findViewById(R.id.btn_back);
        EditText txtinput_1 = findViewById(R.id.txtinput_1);
        EditText txtinput_2 = findViewById(R.id.txtinput_2);
        EditText txtinput_3 = findViewById(R.id.txtinput_3);
        EditText txtinput_4 = findViewById(R.id.txtinput_4);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
