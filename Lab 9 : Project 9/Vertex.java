/**
 * Di Luo
 * CS 231
 * Project 9
 * Vertex.java
 */

import java.util.*;
import java.util.Collection;
import java.util.ArrayList;
import java.awt.*;
import java.awt.Graphics;
import java.io.*;

public class Vertex extends Agent implements Comparable<Vertex>{
    //fields
	private Direction direction;
	private HashMap<Direction, Vertex> vertex;
	private int cost;
	private boolean marked;
	private boolean visible;

	//constructor
	public Vertex(int x, int y){
		super(x,y);
		vertex = new HashMap<Direction, Vertex>(4);
	}

    //functions
	//set the marked field
	public void setMarked(boolean m){marked = m;}

	//get the marked field
	public boolean getMarked(){return marked;}

	//get the cost of the vertex
	public int getCost(){return cost;}

	//set the cost of vertex
	public void setCost(int c){cost = c;}
    
    //set visibility
	public void setVisible(boolean v){visible = v;}

	//get visibility
	public boolean getVisible(){return visible;}

	//returns the compass opposite of a direction 
	public Direction opposite(Direction d){
		if (d == Direction.North){return Direction.South;}
		else if (d == Direction.South){return Direction.North;}
		else if (d == Direction.East){return Direction.West;}
		else if (d == Direction.West){return Direction.East;}
		return null;
    }
    
    //if the vertex has higher cost than the otherCost, return 1
    //if the vertex has lower cost than the otherCost, return -1
    //return 0 if equals
	public int compareTo(Vertex otherCost){
	    if (cost < otherCost.getCost()){return -1;}
	    else if (cost > otherCost.getCost()){return 1;}
	    else{return 0;}
	}

	//modify the object's adjacency list/map so that it connects with the other Vertex
	public void connect(Vertex other, Direction d){
		this.vertex.put(d, other);
		other.vertex.put(other.opposite(d),this);
	}

	//returns the Vertex in the specified direction
	public Vertex getNeighbor(Direction d){
		return this.vertex.get(d);
	}

	//returns an ArrayList of all of the object's neighbors
	public Collection<Vertex> getNeighbors(){
		return this.vertex.values();
	}

	//return a string containing the number of neighbors, the cost, and the marked flag
	public String toString(){
        String s = "";
        s+="Number of neighbors: " + this.getNeighbors().size() + "\n";
        s+="Cost: " + this.cost + "\n";
        s+="Marked: " + this.marked;
		return s;
	}

	//draw the vertex
	public void draw(Graphics g, int x, int y, int scale) {
	    if (!this.visible) return;
	
        int xpos = x + this.x*scale;
        int ypos = y + this.y*scale;
        int border = 2;
        int half = scale/2;
        int eighth = scale/8;
        int sixteenth = scale/16;
	
        if (this.cost <= 2)
            // wumpus is close
            g.setColor(Color.red);
        else
            // wumpus isn't close
            g.setColor(Color.black);
            
        g.drawRect(xpos + border, ypos + border, scale - 2*border, scale - 2 * border);
        
        // draw doorways as boxes
        g.setColor(Color.black);
        if (this.vertex.containsKey(Direction.North)){
            g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
        }
        if (this.vertex.containsKey(Direction.South)){
            g.fillRect(xpos + half - sixteenth, ypos + scale - (eighth + sixteenth), eighth, eighth + sixteenth);
        }
        if (this.vertex.containsKey(Direction.West)){
            g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
        }
        if (this.vertex.containsKey(Direction.East)){
            g.fillRect(xpos + scale - (eighth + sixteenth), ypos + half - sixteenth, eighth + sixteenth, eighth);
        }
    }

	//test function
	public static void main(String[] args){
		Vertex a = new Vertex(0,0);
		Vertex b = new Vertex(0,0);
        Vertex c = new Vertex(0,0);
        
		a.setCost(5);
		b.setCost(3);
        c.setCost(2);
        
		a.connect(b, Direction.North);
        a.connect(c, Direction.East);
        
		System.out.println(a);
		System.out.println(b);
        System.out.println(c);
        
		System.out.println(a.opposite(Direction.East));
	}
}