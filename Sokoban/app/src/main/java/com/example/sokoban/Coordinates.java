package com.example.sokoban;

public class Coordinates {
    private int xCoordinate;
    private int yCoordinate;

    public Coordinates(int xCoordinate, int yCoordinate)
    {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int GetX()
    {
        return xCoordinate;
    }

    public int GetY()
    {
        return yCoordinate;
    }
    //move if there is no wall, move if there is a box with nothing behind it and move the box
    public void GoRight()
    {
        xCoordinate++;
    }

    public void GoLeft()
    {
        xCoordinate--;
    }

    public void GoUp()
    {
        yCoordinate--;
    }
    public void GoDown()
    {
        yCoordinate++;
    }
}
