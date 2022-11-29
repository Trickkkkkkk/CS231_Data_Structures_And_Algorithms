/**
 * Di Luo
 * CS 231
 * Project 4
 * Agent.java
 */

import java.awt.Graphics;

public class Agent{
    //fields
    private double x;
    private double y;

    //constructor
    //a constructor that sets the position
    public Agent(double x0, double y0){
        this.x = x0;
        this.y = y0;
    }

    //functions
    //returns the x position
    public double getX(){
        return x;
    }

    //returns the y position
    public double getY(){
        return y;
    }
    
    //sets the x position
    public void setX( double newX ){
        this.x = newX;
    }

    //sets the y position
    public void setY( double newY ){
        this.y = newY;
    }

    //returns a String containing the x and y positions
    public String toString(){
        String s = "(" + x + ", " + y + ")";
        return s;
    }

    //
    public void updateState( Landscape scape ){

    }

    //
    public void draw(Graphics g){

    }

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