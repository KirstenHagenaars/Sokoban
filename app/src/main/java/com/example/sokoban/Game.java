package com.example.sokoban;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Game extends AppCompatActivity {

    private Button right, left, up, down, restart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        objects map[][] = {{objects.WALL, objects.WALL,objects.WALL,objects.WALL,objects.WALL},
                            {objects.WALL, objects.EMPTY,objects.EMPTY,objects.EMPTY,objects.WALL},
                            {objects.WALL,objects.EMPTY, objects.BOX, objects.EMPTY,objects.WALL},
                            {objects.WALL,objects.EMPTY,objects.EMPTY,objects.EMPTY,objects.WALL},
                            {objects.WALL,objects.WALL,objects.WALL,objects.WALL,objects.WALL}};
        int size = 5;
        Coordinates cross [] = {new Coordinates(2,3)};
        Coordinates player = new Coordinates(1,2);
        final Level level = new Level(map, size, cross, player);
        right = (Button) findViewById(R.id.right);
        left = (Button) findViewById(R.id.left);
        up = (Button) findViewById(R.id.up);
        down = (Button) findViewById(R.id.down);
        restart = (Button) findViewById(R.id.restartButton);

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = findViewById(R.id.level1);
                level.GoRight(layout);
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = findViewById(R.id.level1);
                level.GoLeft(layout);
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = findViewById(R.id.level1);
                level.GoUp(layout);
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = findViewById(R.id.level1);
                level.GoDown(layout);
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open level 1
                Log.d("test", String.valueOf(findViewById(R.id.level1)==null));
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        //if level is solved, doesnt work yet
        if (level.solved())
        {
            startActivity(new Intent(Game.this, MainActivity.class));
        }

    }
}
