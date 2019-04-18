package com.example.sokoban;
enum objects {BOX, WALL, EMPTY}

public class Level {

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

    public void GoRight()
    {
        //Move if there is nothing
        if (map[player.GetY()][player.GetX()+1] == objects.EMPTY)
            player.GoRight();

        //Move if there is a box, and if it is empty behind the box, also move the box
        if (map[player.GetY()][player.GetX()+1] == objects.BOX && map[player.GetY()][player.GetX()+2] == objects.EMPTY)
        {
            player.GoRight();
            map[player.GetY()][player.GetX()+1] = objects.EMPTY;
            map[player.GetY()][player.GetX()+2] = objects.BOX;
        }
    }

    public void GoLeft()
    {
        //Move if there is nothing
        if (map[player.GetY()][player.GetX()-1] == objects.EMPTY)
            player.GoLeft();

        //Move if there is a box, and if it is empty behind the box, also move the box
        if (map[player.GetY()][player.GetX()-1] == objects.BOX && map[player.GetY()][player.GetX()-2] == objects.EMPTY)
        {
            player.GoLeft();
            map[player.GetY()][player.GetX()-1] = objects.EMPTY;
            map[player.GetY()][player.GetX()-2] = objects.BOX;
        }
    }

    public void GoUp()
    {
        //Move if there is nothing
        if (map[player.GetY()-1][player.GetX()] == objects.EMPTY)
            player.GoUp();

        //Move if there is a box, and if it is empty behind the box, also move the box
        if (map[player.GetY()-1][player.GetX()] == objects.BOX && map[player.GetY()-2][player.GetX()] == objects.EMPTY)
        {
            player.GoUp();
            map[player.GetY()-1][player.GetX()] = objects.EMPTY;
            map[player.GetY()-2][player.GetX()] = objects.BOX;
        }
    }
    public void GoDown()
    {
        //Move if there is nothing
        if (map[player.GetY()+1][player.GetX()] == objects.EMPTY)
            player.GoDown();

        //Move if there is a box, and if it is empty behind the box, also move the box
        if (map[player.GetY()+1][player.GetX()] == objects.BOX && map[player.GetY()+2][player.GetX()] == objects.EMPTY)
        {
            player.GoDown();
            map[player.GetY()+1][player.GetX()] = objects.EMPTY;
            map[player.GetY()+2][player.GetX()] = objects.BOX;
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
}
