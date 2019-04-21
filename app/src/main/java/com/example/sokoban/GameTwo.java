package com.example.sokoban;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class GameTwo extends AppCompatActivity {
    private Button right, left, up, down, restart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_two);
        objects map[][] = {{objects.WALL, objects.WALL,objects.WALL,objects.WALL,objects.WALL,objects.WALL,objects.WALL,objects.WALL,objects.WALL},
                {objects.WALL, objects.EMPTY,objects.EMPTY,objects.EMPTY,objects.WALL,objects.WALL,objects.WALL,objects.WALL,objects.WALL},
                {objects.WALL,objects.EMPTY, objects.EMPTY, objects.EMPTY,objects.WALL,objects.WALL,objects.WALL,objects.WALL,objects.WALL},
                {objects.WALL,objects.EMPTY,objects.BOX,objects.EMPTY,objects.WALL,objects.WALL,objects.WALL,objects.EMPTY,objects.WALL},
                {objects.WALL,objects.WALL,objects.WALL,objects.EMPTY,objects.WALL,objects.WALL,objects.WALL,objects.EMPTY,objects.WALL},
                {objects.WALL,objects.WALL,objects.WALL,objects.EMPTY,objects.EMPTY,objects.EMPTY,objects.EMPTY,objects.EMPTY,objects.WALL},
                {objects.WALL,objects.WALL,objects.BOX,objects.EMPTY,objects.EMPTY,objects.WALL,objects.EMPTY,objects.EMPTY,objects.WALL},
                {objects.WALL,objects.WALL,objects.EMPTY,objects.EMPTY,objects.EMPTY,objects.WALL,objects.WALL,objects.WALL,objects.WALL},
                {objects.WALL,objects.WALL,objects.WALL,objects.WALL,objects.WALL,objects.WALL,objects.WALL,objects.WALL,objects.WALL}};
        int size = 9;
        Coordinates cross [] = {new Coordinates(7,3),new Coordinates(7,4)};
        Coordinates player = new Coordinates(1,1);
        final Level level = new Level(map, size, cross, player);
        right = (Button) findViewById(R.id.right);
        left = (Button) findViewById(R.id.left);
        up = (Button) findViewById(R.id.up);
        down = (Button) findViewById(R.id.down);
        restart = (Button) findViewById(R.id.restartButton);

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = findViewById(R.id.level2);
                level.GoRight(layout);
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = findViewById(R.id.level2);
                level.GoLeft(layout);
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = findViewById(R.id.level2);
                level.GoUp(layout);
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = findViewById(R.id.level2);
                level.GoDown(layout);
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open level 1
                Log.d("test", String.valueOf(findViewById(R.id.level2)==null));
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }
}
