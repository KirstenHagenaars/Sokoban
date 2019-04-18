package com.example.sokoban;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private Button level1, level2, level3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        level1 = (Button) findViewById(R.id.level1);
        level2 = (Button) findViewById(R.id.level2);
        level3 = (Button) findViewById(R.id.level3);

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open level 1
                startActivity(new Intent(MainActivity.this, Game.class));
            }
        });

        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open level2
                startActivity(new Intent(MainActivity.this, GameTwo.class));
            }
        });

        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open level 3
                startActivity(new Intent(MainActivity.this, GameThree.class));
            }
        });
    }
}
