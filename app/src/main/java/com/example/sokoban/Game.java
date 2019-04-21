package com.example.sokoban;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Game extends AppCompatActivity {

    private Button right, left, up, down;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        objects map[][] = {{objects.WALL, objects.WALL,objects.WALL,objects.WALL,objects.WALL},
                            {objects.WALL, objects.EMPTY,objects.EMPTY,objects.EMPTY,objects.WALL},
                            {objects.WALL,objects.EMPTY, objects.BOX, objects.EMPTY,objects.WALL},
                            {objects.WALL,objects.EMPTY,objects.CROSS,objects.EMPTY,objects.WALL},
                            {objects.WALL,objects.WALL,objects.WALL,objects.WALL,objects.WALL}};
        int size = 5;
        Coordinates cross [] = {new Coordinates(2,3)};
        Coordinates player = new Coordinates(1,2);
        final Level level = new Level(map, size, cross, player);
        right = (Button) findViewById(R.id.right);
        left = (Button) findViewById(R.id.left);
        up = (Button) findViewById(R.id.up);
        down = (Button) findViewById(R.id.down);

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
    }
}
