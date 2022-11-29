/**
 * Di Luo
 * CS 231
 * Project 4
 * SocialAgent.java
 */

import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class SocialAgent extends Agent{
    //fields
    private boolean moved;
    
    //constructor
    //calls the super class constructor
    public SocialAgent(double x0, double y0) {
        super( x0, y0 );
    }

    //functions
    //draws a circle of radius 5 (i.e. it fits in a 10x10 box) at the Agent's location
    public void draw(Graphics g){
        if (moved){
            g.setColor(Color.darkGray);
        }
        else{
            g.setColor(Color.lightGray);
        }
        g.fillOval((int)this.getX(), (int)this.getY(), 10, 10);
    }

    //get moved
    public boolean getMoved(){
        return moved;
    }

    //set moved
    public void setMoved(boolean newMoved){
        this.moved = newMoved;
    }

    //update the state of agents in Landscape
    public void updateState(Landscape scape){
        //If the cell has more than 3 neighbors (Agents besides itself) within a radius of 15
        //with a 1% chance, the cell should move randomly within the range [-10,10)
        int x0 = (int)this.getX();
        int y0 = (int)this.getY();
        Random rnd = new Random();
        int i = rnd.nextInt(100);
        double leftLimit = -10;
        double rightLimit = 10;
        double generatedDouble1 = leftLimit + rnd.nextDouble() * (rightLimit - leftLimit);
        double generatedDouble2 = leftLimit + rnd.nextDouble() * (rightLimit - leftLimit);
        if (scape.getNeighbors(x0, y0, 15).size()>4){
            if(i < 1){
                this.setX(x0+generatedDouble1);
                this.setY(y0+generatedDouble2);
                this.moved = true;
            }
            else{
                this.moved = false;
            }
        } 
        //else the cell should move randomly within the range [-10, 10)
        else {
            this.setX(x0+generatedDouble1);
            this.setY(y0+generatedDouble2);
            this.moved = true;
        }
    }

    //test the class and its functions
    public static void main(String[] args){
        SocialAgent a = new SocialAgent(1,1);
        System.out.println("x is " + a.getX());
        System.out.println("y is " + a.getY());
        a.setX(2);
        a.setY(3);
        System.out.println("The location is " + a.toString());
    }
}