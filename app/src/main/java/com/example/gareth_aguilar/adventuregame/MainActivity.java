package com.example.gareth_aguilar.adventuregame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_main1 = findViewById(R.id.btn_main1);
        Button btn_main2 = findViewById(R.id.btn_main2);

        btn_main2.setText("Continue");
        btn_main1.setText("");
    }
}
