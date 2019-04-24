package com.example.sokoban;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class GameTwo extends AppCompatActivity {
    private Button right, left, up, down, restart, won;
    static MediaPlayer ring;

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
                {objects.WALL,objects.WALL,objects.EMPTY,objects.BOX,objects.EMPTY,objects.WALL,objects.EMPTY,objects.EMPTY,objects.WALL},
                {objects.WALL,objects.WALL,objects.EMPTY,objects.EMPTY,objects.EMPTY,objects.WALL,objects.WALL,objects.WALL,objects.WALL},
                {objects.WALL,objects.WALL,objects.WALL,objects.WALL,objects.WALL,objects.WALL,objects.WALL,objects.WALL,objects.WALL}};
        int size = 9;
        Coordinates cross [] = {new Coordinates(3, 1),new Coordinates(7,3)};
        Coordinates player = new Coordinates(1,1);
        boolean water = false;
        final Level level = new Level(map, size, cross, player, water);
        right = (Button) findViewById(R.id.right);
        left = (Button) findViewById(R.id.left);
        up = (Button) findViewById(R.id.up);
        down = (Button) findViewById(R.id.down);
        restart = (Button) findViewById(R.id.restartButton);
        won = (Button) findViewById(R.id.won);

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = findViewById(R.id.game2);
                level.GoRight(layout);
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = findViewById(R.id.game2);
                level.GoLeft(layout);
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = findViewById(R.id.game2);
                level.GoUp(layout);
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = findViewById(R.id.game2);
                level.GoDown(layout);
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open level 1
                Log.d("test", String.valueOf(findViewById(R.id.game2)==null));
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        won.setOnClickListener(new View.OnClickListener() {
            Dialog answer = new Dialog(GameTwo.this);
            @Override
            public void onClick(View v) {
                Button back;
                if(level.solved())
                {
                    ring= MediaPlayer.create(GameTwo.this,R.raw.winning);
                    ring.start();
                    Log.d("true won", "won");
                    answer.setContentView(R.layout.winpopup);
                    answer.show();
                    back = (Button) answer.findViewById(R.id.backmain);
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            answer.dismiss();
                            finish();
                        }
                    });
                }
                else
                {
                    ring= MediaPlayer.create(GameTwo.this,R.raw.lose);
                    ring.start();
                    Log.d("false won", "won");
                    answer.setContentView(R.layout.notwinpopup);
                    answer.show();
                    back = (Button) answer.findViewById(R.id.backlevel);
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            answer.dismiss();
                        }
                    });
                }
            }
        });
    }
}
