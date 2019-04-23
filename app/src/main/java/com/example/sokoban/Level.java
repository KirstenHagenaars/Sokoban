package com.example.sokoban;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
enum objects {BOX, WALL, EMPTY, WATER}

public class Level extends AppCompatActivity{

    private objects map[][];        //map of the current level, containing walls, boxes and empty 'objects'
    private int size;               //height/width of the current level
    private Coordinates cross [];   //coordinates of all the crosses in the level
    private Coordinates player;     //coordinates of the player


    public Level (objects map[][], int size, Coordinates cross[], Coordinates player)
    {
        this.map = map;
        this.size = size;
        this.cross = cross;
        this.player = player;
    }

    //Need to take care of index out of bounds for map!
    public void setBox(int x, int y, LinearLayout layout)
    {
        LinearLayout row = (LinearLayout) layout.getChildAt(y);
        ImageView img = (ImageView) row.getChildAt(x);
        Coordinates here = new Coordinates(x,y);
        if (isCross(here))
            img.setImageResource(R.mipmap.boxcross);
        else
            img.setImageResource(R.mipmap.box);
    }

    public void setPlayer(int x, int y, LinearLayout layout)
    {
        LinearLayout row = (LinearLayout) layout.getChildAt(y);
        ImageView img = (ImageView) row.getChildAt(x);
        Coordinates here = new Coordinates(x,y);
        if (isCross(here))
            img.setImageResource(R.mipmap.figurecross);
        else
            img.setImageResource(R.mipmap.figure);
    }
    public void setFloor(int x, int y, LinearLayout layout)
    {
        LinearLayout row = (LinearLayout) layout.getChildAt(y);
        ImageView img = (ImageView) row.getChildAt(x);
        Coordinates here = new Coordinates(x,y);
        if (isCross(here))
            img.setImageResource(R.mipmap.crossfloor);
        else
            img.setImageResource(R.mipmap.floor);
    }


    public void GoRight(LinearLayout layout)
    {
        //Move if there is nothing
        if (map[player.GetY()][player.GetX()+1] == objects.EMPTY)
        {
            setFloor(player.GetX(), player.GetY(), layout);
            setPlayer(player.GetX()+1, player.GetY(), layout);
            player.GoRight();
        }

        //Move if there is a box, and if it is empty behind the box, also move the box
        else if (map[player.GetY()][player.GetX()+1] == objects.BOX && map[player.GetY()][player.GetX()+2] == objects.EMPTY)
        {
            setFloor(player.GetX(), player.GetY(), layout);
            setPlayer(player.GetX()+1, player.GetY(), layout);
            setBox(player.GetX()+2, player.GetY(), layout);
            map[player.GetY()][player.GetX()+1] = objects.EMPTY;
            map[player.GetY()][player.GetX()+2] = objects.BOX;
            player.GoRight();
        }
        else if (map[player.GetY()][player.GetX()+1] == objects.BOX && map[player.GetY()][player.GetX()+2] == objects.WATER)
        {
            setFloor(player.GetX(), player.GetY(), layout);
            setPlayer(player.GetX()+1, player.GetY(), layout);
            setFloor(player.GetX()+2, player.GetY(), layout);
            map[player.GetY()][player.GetX()+1] = objects.EMPTY;
            map[player.GetY()][player.GetX()+2] = objects.EMPTY;
            player.GoRight();
        }
    }

    public void GoLeft(LinearLayout layout)
    {
        //Move if there is nothing
        if (map[player.GetY()][player.GetX()-1] == objects.EMPTY)
        {
            setFloor(player.GetX(), player.GetY(), layout);
            setPlayer(player.GetX()-1, player.GetY(), layout);
            player.GoLeft();
        }

        //Move if there is a box, and if it is empty behind the box, also move the box
        else if (map[player.GetY()][player.GetX()-1] == objects.BOX && map[player.GetY()][player.GetX()-2] == objects.EMPTY)
        {
            setFloor(player.GetX(), player.GetY(), layout);
            setPlayer(player.GetX()-1, player.GetY(), layout);
            setBox(player.GetX()-2, player.GetY(), layout);
            map[player.GetY()][player.GetX()-1] = objects.EMPTY;
            map[player.GetY()][player.GetX()-2] = objects.BOX;
            player.GoLeft();
        }
        else if (map[player.GetY()][player.GetX()-1] == objects.BOX && map[player.GetY()][player.GetX()-2] == objects.WATER)
        {
            setFloor(player.GetX(), player.GetY(), layout);
            setPlayer(player.GetX()-1, player.GetY(), layout);
            setFloor(player.GetX()-2, player.GetY(), layout);
            map[player.GetY()][player.GetX()-1] = objects.EMPTY;
            map[player.GetY()][player.GetX()-2] = objects.EMPTY;
            player.GoLeft();
        }
    }

    public void GoUp(LinearLayout layout)
    {
        //Move if there is nothing
        if (map[player.GetY()-1][player.GetX()] == objects.EMPTY)
        {
            setFloor(player.GetX(), player.GetY(), layout);
            setPlayer(player.GetX(), player.GetY()-1, layout);
            player.GoUp();
        }

        //Move if there is a box, and if it is empty behind the box, also move the box
        else if (map[player.GetY()-1][player.GetX()] == objects.BOX && map[player.GetY()-2][player.GetX()] == objects.EMPTY)
        {
            setFloor(player.GetX(), player.GetY(), layout);
            setPlayer(player.GetX(), player.GetY()-1, layout);
            setBox(player.GetX(), player.GetY()-2, layout);
            map[player.GetY()-1][player.GetX()] = objects.EMPTY;
            map[player.GetY()-2][player.GetX()] = objects.BOX;
            player.GoUp();
        }
        //Move if there is a box and behind the box there is water and turn water into floor
        else if (map[player.GetY()-1][player.GetX()] == objects.BOX && map[player.GetY()-2][player.GetX()] == objects.WATER)
        {
            setFloor(player.GetX(), player.GetY(), layout);
            setPlayer(player.GetX(), player.GetY()-1, layout);
            setFloor(player.GetX(), player.GetY()-2, layout);
            map[player.GetY()-1][player.GetX()] = objects.EMPTY;
            map[player.GetY()-2][player.GetX()] = objects.EMPTY;
            player.GoUp();
        }
    }
    public void GoDown(LinearLayout layout)
    {
        //Move if there is nothing
        if (map[player.GetY()+1][player.GetX()] == objects.EMPTY)
        {
            setFloor(player.GetX(), player.GetY(), layout);
            setPlayer(player.GetX(), player.GetY()+1, layout);
            player.GoDown();
        }

        //Move if there is a box, and if it is empty behind the box, also move the box
        else if (map[player.GetY()+1][player.GetX()] == objects.BOX && map[player.GetY()+2][player.GetX()] == objects.EMPTY)
        {
            setFloor(player.GetX(), player.GetY(), layout);
            setPlayer(player.GetX(), player.GetY()+1, layout);
            setBox(player.GetX(), player.GetY()+2, layout);
            map[player.GetY()+1][player.GetX()] = objects.EMPTY;
            map[player.GetY()+2][player.GetX()] = objects.BOX;
            player.GoDown();
        }
        //Move if there is a box and behind the box there is water and turn water into floor
        else if(map[player.GetY()+1][player.GetX()] == objects.BOX && map[player.GetY()+2][player.GetX()] == objects.WATER)
        {
            setFloor(player.GetX(), player.GetY(), layout);
            setPlayer(player.GetX(), player.GetY()+1, layout);
            setFloor(player.GetX(), player.GetY()+2, layout);
            map[player.GetY()+1][player.GetX()] = objects.EMPTY;
            map[player.GetY()+2][player.GetX()] = objects.EMPTY;
            player.GoDown();
        }
    }

    public boolean solved()
    {
        for(Coordinates coord: cross)
        {
            if(map[coord.GetY()][coord.GetX()]!= objects.BOX)
                return false;
        }
        return true;
    }

    public boolean isCross(Coordinates coordinates)
    {
        boolean iscross = false;
        for (int i = 0; i < cross.length; i++)
        {
            if(coordinates.GetX()==cross[i].GetX() && coordinates.GetY()== cross[i].GetY())
                iscross = true;
        }
        return iscross;
    }
}
