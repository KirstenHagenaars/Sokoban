package com.example.sokoban;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
enum objects {BOX, WALL, EMPTY, CROSS}

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
    public void SwapPictures(int x, int y, int x2, int y2, LinearLayout layout)
    {
        LinearLayout row = (LinearLayout) layout.getChildAt(y);
        ImageView img = (ImageView) row.getChildAt(x);
        Drawable draw1 = img.getDrawable();
        //Log.d("coordinates", x+"\t"+y+"\t"+x2+"\t"+y2+"end");
        LinearLayout row2 = (LinearLayout) layout.getChildAt(y2);
        ImageView img2 = (ImageView) row2.getChildAt(x2);
        Drawable draw2 = img2.getDrawable();
        img.setImageDrawable(draw2);
        img2.setImageDrawable(draw1);
    }

    public void setBox(int x, int y, LinearLayout layout)
    {
        LinearLayout row = (LinearLayout) layout.getChildAt(y);
        row.removeViewAt(x);
        ImageView box = new ImageView(this);
        ImageView b = layout.findViewById(R.id.box);
        box.setImageDrawable(b.getDrawable());
        row.addView(box,x);
    }
    public void setFigure(int x, int y, LinearLayout layout)
    {
        LinearLayout row = (LinearLayout) layout.getChildAt(y);
        row.removeViewAt(x);
        ImageView fig = new ImageView(this);
        ImageView f = layout.findViewById(R.id.figure);
        fig.setImageDrawable(f.getDrawable());
        row.addView(fig,x);
    }
    public void setFloor(int x, int y, LinearLayout layout)
    {
        LinearLayout row = (LinearLayout) layout.getChildAt(y);
        row.removeViewAt(x);
        ImageView fl = new ImageView(this);
        ImageView f = layout.findViewById(R.id.floor);
        fl.setImageDrawable(f.getDrawable());
        row.addView(fl,x);

    }
    public void setCross(int x, int y, LinearLayout layout)
    {
        LinearLayout row = (LinearLayout) layout.getChildAt(y);
        row.removeViewAt(x);
        ImageView cr = new ImageView(this);
        ImageView c = layout.findViewById(R.id.cross);
        cr.setImageDrawable(c.getDrawable());
        row.addView(cr, x);

    }

    public void GoRight(LinearLayout layout)
    {
        //Move if there is nothing
        if (map[player.GetY()][player.GetX()+1] == objects.EMPTY)
        {
            SwapPictures(player.GetX(),player.GetY(),player.GetX()+1,player.GetY(), layout);
            player.GoRight();
        }
        if (map[(player.GetY())][player.GetX()+1] == objects.CROSS)
        {
            SwapPictures(player.GetX(),player.GetY(),player.GetX()+1,player.GetY(), layout);
            setFloor(player.GetX(), player.GetY(), layout);
            player.GoRight();
        }

        //Move if there is a box, and if it is empty behind the box, also move the box
        if (map[player.GetY()][player.GetX()+1] == objects.BOX && map[player.GetY()][player.GetX()+2] == objects.EMPTY)
        {
            SwapPictures(player.GetX()+1, player.GetY(), player.GetX()+2, player.GetY(), layout);
            SwapPictures(player.GetX(), player.GetY(), player.GetX()+1, player.GetY(), layout);
            map[player.GetY()][player.GetX()+1] = objects.EMPTY;
            map[player.GetY()][player.GetX()+2] = objects.BOX;
            player.GoRight();
        }
        if (map[player.GetY()][player.GetX()+1] == objects.BOX && map[player.GetY()][player.GetX()+2] == objects.CROSS)
        {
            SwapPictures(player.GetX()+1, player.GetY(), player.GetX()+2, player.GetY(), layout);
            SwapPictures(player.GetX(), player.GetY(), player.GetX()+1, player.GetY(), layout);
            setFloor(player.GetX(), player.GetY(), layout);
            player.GoRight();
        }
    }

    public void GoLeft(LinearLayout layout)
    {
        //Move if there is nothing
        if (map[player.GetY()][player.GetX()-1] == objects.EMPTY)
        {
            SwapPictures(player.GetX(),player.GetY(),player.GetX()-1,player.GetY(), layout);
            player.GoLeft();
        }
        if (map[(player.GetY())][player.GetX()-1] == objects.CROSS)
        {
            SwapPictures(player.GetX(),player.GetY(),player.GetX()-1,player.GetY(), layout);
            setFloor(player.GetX(), player.GetY(), layout);
            player.GoLeft();
        }

        //Move if there is a box, and if it is empty behind the box, also move the box
        if (map[player.GetY()][player.GetX()-1] == objects.BOX && map[player.GetY()][player.GetX()-2] == objects.EMPTY)
        {
            SwapPictures(player.GetX()-1, player.GetY(), player.GetX()-2, player.GetY(), layout);
            SwapPictures(player.GetX(), player.GetY(), player.GetX()-1, player.GetY(), layout);
            map[player.GetY()][player.GetX()-1] = objects.EMPTY;
            map[player.GetY()][player.GetX()-2] = objects.BOX;
            player.GoLeft();
        }
        if (map[player.GetY()][player.GetX()-1] == objects.BOX && map[player.GetY()][player.GetX()-2] == objects.CROSS)
        {
            SwapPictures(player.GetX()-1, player.GetY(), player.GetX()-2, player.GetY(), layout);
            SwapPictures(player.GetX(), player.GetY(), player.GetX()-1, player.GetY(), layout);
            setFloor(player.GetX(), player.GetY(), layout);
            player.GoLeft();
        }
    }

    public void GoUp(LinearLayout layout)
    {
        //Move if there is nothing
        if (map[(player.GetY())-1][player.GetX()] == objects.EMPTY)
        {
            SwapPictures(player.GetX(),player.GetY(),player.GetX(),player.GetY()-1, layout);
            player.GoUp();
        }
        if (map[(player.GetY())-1][player.GetX()] == objects.CROSS)
        {
            SwapPictures(player.GetX(),player.GetY(),player.GetX(),player.GetY()-1, layout);
            setFloor(player.GetX(), player.GetY(), layout);
            player.GoUp();
        }

        //Move if there is a box, and if it is empty behind the box, also move the box
        if (map[player.GetY()-1][player.GetX()] == objects.BOX && map[player.GetY()-2][player.GetX()] == objects.EMPTY)
        {
            SwapPictures(player.GetX(), player.GetY()-1, player.GetX(), player.GetY()-2, layout);
            SwapPictures(player.GetX(), player.GetY(), player.GetX(), player.GetY()-1, layout);
            map[player.GetY()-1][player.GetX()] = objects.EMPTY;
            map[player.GetY()-2][player.GetX()] = objects.BOX;
            player.GoUp();
        }
        if (map[player.GetY()-1][player.GetX()] == objects.BOX && map[player.GetY()-2][player.GetX()] == objects.CROSS)
        {
            SwapPictures(player.GetX(), player.GetY()-1, player.GetX(), player.GetY()-2, layout);
            SwapPictures(player.GetX(), player.GetY(), player.GetX(), player.GetY()-1, layout);
            setFloor(player.GetX(), player.GetY(), layout);
            player.GoUp();
        }
    }
    public void GoDown(LinearLayout layout)
    {
        //Move if there is nothing
        if (map[player.GetY()+1][player.GetX()] == objects.EMPTY)
        {
            SwapPictures(player.GetX(),player.GetY(),player.GetX(),player.GetY()+1, layout);
            player.GoDown();
        }
        if (map[(player.GetY())+1][player.GetX()] == objects.CROSS)
        {
            SwapPictures(player.GetX(),player.GetY(),player.GetX(),player.GetY()+1, layout);
            setFloor(player.GetX(), player.GetY(), layout);
            player.GoDown();
        }

        //Move if there is a box, and if it is empty behind the box, also move the box
        if (map[player.GetY()+1][player.GetX()] == objects.BOX && map[player.GetY()+2][player.GetX()] == objects.EMPTY)
        {
            SwapPictures(player.GetX(), player.GetY()+1, player.GetX(), player.GetY()+2, layout);
            SwapPictures(player.GetX(), player.GetY(), player.GetX(), player.GetY()+1, layout);
            map[player.GetY()+1][player.GetX()] = objects.EMPTY;
            map[player.GetY()+2][player.GetX()] = objects.BOX;
            player.GoDown();
        }
        if (map[player.GetY()+1][player.GetX()] == objects.BOX && map[player.GetY()+2][player.GetX()] == objects.CROSS)
        {
            SwapPictures(player.GetX(), player.GetY()+1, player.GetX(), player.GetY()+2, layout);
            SwapPictures(player.GetX(), player.GetY(), player.GetX(), player.GetY()+1, layout);
            setFloor(player.GetX(), player.GetY(), layout);
            player.GoDown();
        }
    }

    public boolean solved()
    {
        for (int i = 0; i < cross.length; i++)
        {
            if (map[cross[i].GetX()][cross[i].GetY()] != objects.BOX)
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
