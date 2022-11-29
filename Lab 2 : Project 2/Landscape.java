/**
 * Di Luo
 * CS 231
 * Project 2: Conway's Game of Life
 * Landscape.java
 */

import java.util.ArrayList;
import java.awt.Graphics;

public class Landscape{
    //hold a 2D grid of Cell object reference
    
    //fields
    private int rows = 0;
    private int cols = 0;
    private Cell[][] grid;
    private Cell[][] grid2;
    private ArrayList<Cell> list;
    private String landscape;

    //constructors
    public Landscape(int rows, int cols){
        //sets the number of rows and columns to the specified values and allocates the grid of Cell references
        this.rows = rows;
        this.cols = cols;
        grid = new Cell[this.rows][this.cols];
        for (int i=0; i<this.rows; i++){
            for (int j=0; j<this.cols; j++){
                grid[i][j] = new Cell();
            }
        }
    }

    //funtions
    public void reset(){
        //calls the reset method for each cell
        for (int i=0; i<this.rows; i++){
            for (int j=0; j<this.cols; j++){
                grid[i][j].reset();
            }
        }
    }

    public int getRows(){
        //returns the number of rows in the Landscape
        return rows;
    }

    public int getCols(){
        //returns the number of columns in the Landscape
        return cols;
    }

    public Cell getCell( int row, int col ){
        //returns a reference to the Cell located at position (row, col)
        return grid[row][col];
    }

    public String toString(){
        //converts the Landscape into a text-based string representation
        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++){
                landscape += (grid[i][j].toString() + " ");
            }
            landscape += "\n";
        }
        return landscape;
    }

    public ArrayList<Cell> getNeighbors(int row, int col){
        //returns a list of references to the neighbors of the Cell at location (row, col)
        ArrayList<Cell> list = new ArrayList<Cell>();
        if (row>0 && row<this.rows-1 && col>0 && col<this.cols-1){
            list.add(getCell(row-1, col-1));
            list.add(getCell(row-1, col));
            list.add(getCell(row-1, col+1));
            list.add(getCell(row, col-1));
            list.add(getCell(row, col+1));
            list.add(getCell(row+1, col-1));
            list.add(getCell(row+1, col));
            list.add(getCell(row+1, col+1));
        }
        else if (row == 0 && col == 0){
            list.add(getCell(0, 1));
            list.add(getCell(1, 0));
            list.add(getCell(1, 1));
        }
        else if (row == 0 && col == this.cols-1){
            list.add(grid[row][col-1]);
            list.add(grid[row+1][col-1]);
            list.add(grid[row+1][col]);
        }
        else if (row == 0 && col>0 && col<this.cols-1){
            list.add(grid[row][col-1]);
            list.add(grid[row][col+1]);
            list.add(grid[row+1][col-1]);
            list.add(grid[row+1][col]);
            list.add(grid[row+1][col+1]);
        }
        else if (col == 0 && row == this.cols-1){
            list.add(grid[row-1][0]);
            list.add(grid[row-1][1]);
            list.add(grid[row][1]);
        }
        else if (col == 0 && row>0 && row<this.rows-1){
            list.add(grid[row-1][0]);
            list.add(grid[row+1][0]);
            list.add(grid[row-1][1]);
            list.add(grid[row][1]);
            list.add(grid[row+1][1]);
        }
        else if (col == this.cols-1 && row == this.rows-1){
            list.add(grid[row-1][col-1]);
            list.add(grid[row-1][col]);
            list.add(grid[row][col-1]);
        }
        else if (col == this.cols-1 && row>0 && row<this.rows-1){
            list.add(grid[row-1][col-1]);
            list.add(grid[row-1][col]);
            list.add(grid[row][col-1]);
            list.add(grid[row+1][col-1]);
            list.add(grid[row+1][col]);
        }
        else if (row == this.rows-1 && col>0 && col<this.cols-1){
            list.add(grid[row-1][col-1]);
            list.add(grid[row-1][col]);
            list.add(grid[row-1][col+1]);
            list.add(grid[row][col-1]);
            list.add(grid[row][col+1]);
        }
        return list;
    }

    public void draw( Graphics g, int gridScale ) {
		// draw all the cells
		for (int i = 0; i < this.getRows(); i++) {
			for (int j = 0; j < this.getCols(); j++) {
				this.grid[i][j].draw(g, i*gridScale, j*gridScale, gridScale);
			}
		}
    }
    
    public void advance(){
        //move all Cells forward one generation
        grid2 = new Cell[this.getRows()][this.getCols()];
        for (int i = 0; i < this.getRows(); i++) {
			for (int j = 0; j < this.getCols(); j++) {
				this.grid2[i][j] = new Cell(this.grid[i][j].getAlive());
			}
        }
        for (int i = 0; i < this.getRows(); i++) {
			for (int j = 0; j < this.getCols(); j++) {
                this.grid2[i][j].updateState(this.getNeighbors(i, j));
            }
        }
        this.grid = this.grid2;
    }

    public static void main(String[] argv){
        //test
        Landscape a = new Landscape(5,5);
        System.out.println("The landscape is " + a.getRows()+ " times "+ a.getRows()+".");
    }
}