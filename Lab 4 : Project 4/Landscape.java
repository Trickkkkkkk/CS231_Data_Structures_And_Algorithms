/**
 * Di Luo
 * CS 231
 * Project 4
 * Landscape.java
 */

import java.util.ArrayList;
import java.awt.Graphics;

public class Landscape{
    //fields
    private int width;
    private int height;
    private LinkedList<Agent> agentList;
    private ArrayList<Agent> shuffledList;

    //constructor
    //sets the width and height fields, and initializes the agent list
    public Landscape(int w, int h){
        this.width = w;
        this.height = h;
        agentList = new LinkedList<Agent>();
    }

    //functions
    //returns the height
    public int getHeight(){
        return this.height;
    }

    //returns the width.
    public int getWidth(){
        return this.width;
    }

    //inserts an agent at the beginning of its list of agents.
    public void addAgent( Agent a ) {
        agentList.addFirst(a);
    }

    //returns a String representing the Landscape.
    public String toString() {
        ArrayList<Agent> a = agentList.toArrayList(); 
        String s = "The number of the Agents is " + a.size() + ".";
        return s;
    }

    //returns a list of the Agents within radius distance of the location x0, y0.
    public ArrayList<Agent> getNeighbors(double x0, double y0, double radius) {
        ArrayList<Agent> a = agentList.toArrayList();
        a.removeIf(i -> (Math.pow(i.getX()-x0, 2)+Math.pow(i.getY()-y0, 2)>Math.pow(radius, 2)));
        return a;
    }

    //Calls the draw method of all the agents on the Landscape
    public void draw(Graphics g) {
        ArrayList<Agent> a = agentList.toArrayList();
        for (Agent i: a){
            i.draw(g);
        }
    }

    //updates the state of each agent
    public void updateAgents(){
        shuffledList = agentList.toShuffledList();
        for (Agent i: shuffledList){
            i.updateState(this);
        }
    }

    //test function to check getNeighbors()
    public static void main(String[] args){
        Landscape l = new Landscape(500, 500);
        l.addAgent( new SocialAgent(5, 5));
        l.addAgent( new SocialAgent(10, 10));
        l.addAgent( new SocialAgent(15, 15));
        l.addAgent( new SocialAgent(20, 20));
        ArrayList<Agent> n1 = l.getNeighbors(5, 5, 10);
        ArrayList<Agent> n2 = l.getNeighbors(5, 5, 15);
        ArrayList<Agent> n3 = l.getNeighbors(5, 5, 25);
        System.out.println("The agent at (5, 5) has " + n1.size() + " neighbors with radius 10 including itself");
        System.out.println("The agent at (5, 5) has " + n2.size() + " neighbors with radius 15 including itself");
        System.out.println("The agent at (5, 5) has " + n3.size() + " neighbors with radius 25 including itself");
        LandscapeDisplay display = new LandscapeDisplay(l);
        display.repaint();
    }
}