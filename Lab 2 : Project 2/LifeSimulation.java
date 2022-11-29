/**
 * Di Luo
 * CS 231
 * Project 2: Conway's Game of Life
 * LifeSimulation.java
 */

import java.util.Random;

public class LifeSimulation{
    //simulate the Game of life

    public static void main(String[] args) throws InterruptedException {
        Landscape scape = new Landscape(100,100);
        Random gen = new Random();
        double density = 0.3;

	// initialize the grid to be 30% full
        for (int i = 0; i < scape.getRows(); i++) {
            for (int j = 0; j < scape.getCols(); j++ ) { 
                scape.getCell( i, j ).setAlive( gen.nextDouble() <= density );
            }
        }

        LandscapeDisplay display = new LandscapeDisplay(scape, 8);

        while(true){
            Thread.sleep(5000);
            scape.advance();
            display.repaint();
        }
    }
}