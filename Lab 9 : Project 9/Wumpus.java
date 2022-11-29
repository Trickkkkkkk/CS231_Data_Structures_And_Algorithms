/**
 * Di Luo
 * CS 231
 * Project 9
 * Wumpus.java
 */

import java.util.*;
import java.awt.Graphics;
import java.io.*;
import java.awt.*;
import java.util.Collection;
import java.awt.image.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;

public class Wumpus extends Agent{
    //fields
	private Vertex home;
	private boolean visible;
	private boolean alive;
	private BufferedImage thriving;
	private BufferedImage dead;

	//constructor
	public Wumpus(Vertex location){
		super (location.getX(), location.getY());
		this.home = location;
		home.setVisible(false);
		this.alive = true;

		// different pictures for different alive and dead wumpus
		try{
			thriving = ImageIO.read(new File("MonsterAlive.jpg"));
			dead = ImageIO.read(new File("deadMonster.jpg"));
		}
		catch(IOException e){}
	}

	//set the visibiltiy of Wumpus
	public void setVisible(boolean v){
		this.visible = v;
	}

	//get the vertex of the home of the vertex
	public Vertex getLocation(){
		return this.home;
	}

	//set the status for the wumpus
	public void setStatus(boolean a){
		this.alive = a;
	}

	//get the status for the wumpus
	public boolean getStatus(){
		return this.alive;
	}

	//the draw method for wumpus
	public void draw(Graphics g, int x0, int y0, int scale) {
		int xpos = x0 + home.getX()*scale+scale/4;
		int ypos = y0 + home.getY()*scale+scale/4;
		int border = 2;

		if (home.getVisible() == false){}
		else{
			// draw two pictures for the wumpus
			if (alive == true){
				g.drawImage(thriving, xpos, ypos, null);
			}
			else{
				g.drawImage(dead, xpos, ypos, null);
			}		
		}
	}
}