/**
 * Di Luo
 * CS 231
 * Project 4
 * CategorizedSocialAgent.java
 */

import java.awt.Color;
import java.util.Random;
import java.awt.Graphics;
import java.util.ArrayList;

public class CategorizedSocialAgent extends SocialAgent {
    //fields
    private int category;
    private ArrayList<Agent> n;

    //constructor
    //calls the parent constructor and set the category.
    public CategorizedSocialAgent(double x0, double y0, int cat) {
        super(x0, y0);
        this.category = cat;
    }

    //returns the category value.
    public int getCategory() {
        return this.category;
    }

    //returns a single character string indicating the category.
    public String toString() {
        String s = Integer.toString(this.category);
        return s;
    }

    //draws a circle of radius 5 (i.e. it fits in a 10x10 box) at the Agent's location.
    public void draw(Graphics g) {
        if (this.getCategory()==1){
            if (this.getMoved()){
                g.setColor(Color.red);
            }
            else{
                g.setColor(Color.pink);
            }
        }
        if (this.getCategory()==2){
            if (this.getMoved()){
                g.setColor(Color.darkGray);
            }
            else{
                g.setColor(Color.lightGray);
            }
        }
        g.fillOval((int)this.getX(), (int)this.getY(), 10, 10);
    }

    //Identify how many neighbors within a radius of 20 have the same category 
    //(use this.getCategory()) and how many have a different category.
    public void updateState(Landscape scape) {
        int x0 = (int)this.getX();
        int y0 = (int)this.getY();
        int numSame = -1;
        int numDiff = 0;
        Random rnd = new Random();
        int i = rnd.nextInt(100);
        double leftLimit = -10;
        double rightLimit = 10;
        double generatedDouble1 = leftLimit + rnd.nextDouble() * (rightLimit - leftLimit);
        double generatedDouble2 = leftLimit + rnd.nextDouble() * (rightLimit - leftLimit);
        n = scape.getNeighbors(x0, y0, 100);//radius=20/40/100
        for (Agent j: n){
            if (((CategorizedSocialAgent)j).getCategory()==this.getCategory()){
                numSame++;
            }
            else{
                numDiff++;
            }
        }
        if (n.size()>2 && numSame>numDiff){
            if(i < 1){
                this.setX(x0+generatedDouble1);
                this.setY(y0+generatedDouble2);
                this.setMoved(true);
            }
            else{
                this.setMoved(false);
            }
        }
        else{
            this.setX(x0+generatedDouble1);
            this.setY(y0+generatedDouble2);
            this.setMoved(true);
        }
    }
}