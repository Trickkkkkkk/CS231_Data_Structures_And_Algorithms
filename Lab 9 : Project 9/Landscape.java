/**
 * Di Luo
 * CS 231
 * Project 9
 * Landscape.java
 */

import java.util.ArrayList;
import java.awt.Graphics;
import java.util.Random;

public class Landscape{
    //fields
    private int w;
    private int h;
    private Hunter hunter;
	private Wumpus wumpus;
	private Graph graph;
	private LinkedList<Agent> agent;
	private LinkedList<Agent> backAgents;
	private Vertex[][] grid;

    //constructor
    public Landscape(int w, int h){
		this.w = w;
		this.h = h;
		this.graph = new Graph();
		this.agent = new LinkedList<Agent>();
		this.backAgents = new LinkedList<Agent>();
		this.grid = new Vertex[w][h];
		Random r = new Random();

		// put the vertices into the grid
		for (int i = 0; i < w; i++){
			for (int j = 0; j < h; j++){
				Random r1 = new Random();
				Vertex vertex = new Vertex(i, j);
				grid[i][j] = vertex;
				graph.add(vertex);
				backAgents.addFirst(vertex);

				// for extension 1, comment out the code below
				// if(i > 0){
				// 	graph.addEdge(vertex, Direction.West, grid[i-1][j]);	
				// }
				// if (j>0){
				// 	graph.addEdge(vertex, Direction.North, grid[i][j-1]);
				// }
				// Extension 1: set the edges for the vertices randomly
				if(i > 0){
					if (r1.nextInt(20)>5){
						graph.addEdge(vertex, Direction.West, grid[i-1][j]);
					}					
				}
				if (j>0){
					if (r1.nextInt(20)<15){
						graph.addEdge(vertex, Direction.North, grid[i][j-1]);
					}
				}
			}
		}

		//put the wumpus into the graph
		int xw = r.nextInt(w);
		int yw = r.nextInt(h);
		this.wumpus = new Wumpus(grid[xw][yw]);
		agent.addFirst(wumpus);
		graph.shortestPath(wumpus.getLocation());
		
		//put the hunter into the graph
		int xh = r.nextInt(w);
		int yh = r.nextInt(h);
		while (xw == xh && yw==yh){
			xh = r.nextInt(w);
			yh = r.nextInt(h);
		}
		this.hunter = new Hunter(grid[xh][yh]);
		grid[xh][yh].setVisible(true);
		agent.addFirst(hunter);
	}

    //funcitons
    //return the height of the Landscape
    public int getHeight(){return h;}

    //return the width of the Landscape
    public int getWidth(){return w;}
	
	//inserts an agent at the beginning of its list of agents
	public void addAgent(Agent a){agent.addFirst(a);}

	//set the status of the hunter (loaded or not loaded)
	public void setLoaded(boolean l){hunter.load(l);}

	//get the status of the hunter
	public boolean getLoaded(){return hunter.getStatus();}

	//get the hunter's state
	public boolean getState(){return hunter.getStatus();}

	//get the wumpus state
	public boolean getWumpus(){return wumpus.getStatus();}

	//get the hunter vertex
	public Hunter getHunter(){return hunter;}

	//get the wumpus vertex
	public Wumpus getW(){return wumpus;}
	
	//the rules for the hunter to move in the grid, returns whether the hunter is still alive
	public boolean update(Direction dir){	
		// if the hunter is loaded, conduct the shoot method
		if (this.hunter.getStatus() == true){	
			this.shoot(dir);
			return this.shoot(dir);
		}
		// if not loaded, move.
		else{
			Vertex nextStep = this.hunter.getLocation().getNeighbor(dir);
			if (nextStep == null){
				return true;
			}
			else{
				// if the hunter is in the wumpus home, he dies
				if (nextStep == this.wumpus.getLocation()){
					this.wumpus.setVisible(true);
					nextStep.setVisible(true);
					return false;
				}
				else{
					this.hunter.move(nextStep);
					nextStep.setVisible(true);
					return true;
				}
			}
		}
	}

	//rules for the hunter to shoot, if shoot right, hunter wins; if wrong, hunter loses
	public boolean shoot(Direction dir){
		Vertex aim = this.hunter.getLocation().getNeighbor(dir);
		//right
		if (aim == this.wumpus.getLocation()){
			aim.setVisible(true);
			this.wumpus.setStatus(false);
			this.wumpus.setVisible(true);
			return true;
		}
		//wrong
		else{
			this.wumpus.getLocation().setVisible(true);
			this.wumpus.setVisible(true);
			return false;
		}
	}

	//returns a String representing the Landscape
	public String toString(){
		return "numbers of Agents: " + agent.size(); 
	}

	//Calls the draw method of all the agents on the Landscape
	public void draw(Graphics g, int scale){
		for (Agent bagents: backAgents){
			bagents.draw(g, bagents.getX(), bagents.getY(), scale);
		}

		for (Agent agents: agent){
			agents.draw(g, agents.getX(), agents.getY(), scale);
		}
	}
}