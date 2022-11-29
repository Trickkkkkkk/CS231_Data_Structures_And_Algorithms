/**
 * Di Luo
 * CS 231
 * Project 9
 * Agent.java
 */

import java.awt.Graphics;

public class Agent{
    //fields
    int x;
    int y;

    //constructor
    //a constructor that sets the position
    public Agent(int x, int y){
        this.x = x;
        this.y = y;
    }

    //functions
    //returns the x position
    public int getX(){
        return x;
    }

    //returns the y position
    public int getY(){
        return y;
    }
    
    //sets the x position
    public void setX( int newX ){
        this.x = newX;
    }

    //sets the y position
    public void setY( int newY ){
        this.y = newY;
    }

    //returns a String containing the x and y positions
    public String toString(){
        String s = "(" + x + ", " + y + ")";
        return s;
    }

    //
    public void draw(Graphics g, int x0, int y0, int scale){}

    //test the class and its functions
    public static void main(String[] args){
        Agent a = new Agent(1,1);
        System.out.println("x is " + a.getX());
        System.out.println("y is " + a.getY());
        a.setX(2);
        a.setY(3);
        System.out.println("The location is " + a.toString());
    }
}