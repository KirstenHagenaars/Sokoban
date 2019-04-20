package com.example.sokoban;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameTwo extends AppCompatActivity {

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
        Level level = new Level(map, size, cross, player);
    }
}
