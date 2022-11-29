/**
 * Di Luo
 * CS 231
 * Project 2: Conway's Game of Life
 * Cell.java
 */

import java.awt.Graphics;
import java.util.ArrayList;

public class Cell{
    //Creat Cell objects representing one location on a regular grid

    //fields
    private boolean alive;
    
    //constructors
    public Cell(){
        //constructor method, by default the Cell is dead
        alive = false;
    }

    public Cell( boolean alive){
        //constructor method
        this.alive = alive;
    }

    //functions
    public boolean getAlive(){
        //returens whether the Cell is alive
        return alive;
    }

    public void reset(){
        //sets the Cell state to its default value
        alive = false;
    }

    public void setAlive( boolean alive){
        //sets the Cell's alive state
        this.alive = alive;
    }

    public String toString(){
        //returens a string that indicated the alive state of the Cell as a one-character string
        if (alive = true){
            return "0";
        }
        else{
            return " ";
        }
    }

    public void draw( Graphics g, int x, int y, int scale){
        //draw a Cell
        if (this.getAlive()==true){
            g.drawRect(x, y, scale, scale);
            g.fillRect(x, y, scale, scale);
        }
        else{
            g.drawRect(x, y, scale, scale);
        }
    }

    public void updateState( ArrayList<Cell> neighbors ){
        //updates whether or not the cell is alive in the next time step, given its neighbors in the current time step
        int liveCell = 0;
        for (Cell i: neighbors){
            if (i.getAlive()==true){
                liveCell++;
            }
        }
        if (this.getAlive()==true && (liveCell==2 || liveCell==3)){
            this.setAlive(true);
        }
        else if (this.getAlive()==false && liveCell==3){
            this.setAlive(true);
        }
        else {
            this.setAlive(false);
        }
    }

    public static void main(String[] args){
        //main method testing the functions

        Cell a = new Cell(true);
        if (a.getAlive() == true){
            System.out.println("Cell a is alive with constructor.");
        }
        a.reset();
        if (a.getAlive() == false){
            System.out.println("Cell a is dead with reset.");
        }
        a.setAlive(true);
        if (a.getAlive() == true){
            System.out.println("Cell a is alive with setAlive.");
        }
        System.out.println(a.toString());
    }
}