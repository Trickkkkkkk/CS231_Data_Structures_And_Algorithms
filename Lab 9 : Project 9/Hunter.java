/**
 * Di Luo
 * CS 231
 * Project 9
 * Hunter.java
 */

import java.util.*;
import java.awt.Graphics;
import java.io.*;
import java.awt.*;
import java.util.Collection;
import java.awt.image.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;

public class Hunter extends Agent{
	//fields
	private Vertex location;
	private boolean loaded;
	private BufferedImage hunterUnloaded;
	private BufferedImage hunterLoaded;
	
	//constructor
	public Hunter(Vertex loc){
		super(loc.getX(), loc.getY());
		this.location = loc;
		location.setVisible(true);
		try{
			hunterUnloaded = ImageIO.read(new File("HunterNotLoaded.png"));
			hunterLoaded = ImageIO.read(new File("HunterLoaded.jpg"));
		}
		catch(IOException e){}
	}

	//get the status of the hunter
	public boolean getStatus(){
		return this.loaded;
	}

	//change the status of the hunter
	public void load(boolean l){
		this.loaded = l;
	}

	//move method which updates the location of the Hunter as hunter moves
	public void move(Vertex v){
		this.location = v;
	}

	//get the location vertex
	public Vertex getLocation(){
		return this.location;
	}

	//the draw method for hunter
	public void draw(Graphics g, int x0, int y0, int scale) {
		
		int xpos = x0 + location.getX()*scale+scale/4;
		int ypos = y0 + location.getY()*scale+scale/10;
		int border = 2;
		
		// draw different images for the hunter depending on the hunter's status
		if (this.loaded == true){
			g.drawImage(hunterLoaded, xpos, ypos, null);
		}
		else{
			g.drawImage(hunterUnloaded, xpos, ypos, null);
		}
	}
}