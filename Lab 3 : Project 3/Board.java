/**
 * Di Luo
 * CS 231
 * Project 3: Sudoku
 * Board.java
 */

import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.applet.Applet;
import java.awt.*;          
import java.awt.event.*;    
import javax.swing.*;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board{

    //fields
    private Cell[][] array;
    public static int Size = 9;
    private int count;
    private ArrayList<Integer> list;
    private Cell[][] saveArray;

    //constructors
    public Board(){
        array = new Cell[Size][Size];
        saveArray=new Cell[Size][Size];
        for (int i=0; i<array.length; i++){
            for (int j=0; j<array[0].length; j++){
                array[i][j] = new Cell(i, j, 0);
                saveArray[i][j]=new Cell(i,j,0);
            }
        }
    }

    public Board(String filename){
        array = new Cell[Size][Size];
        saveArray=new Cell[Size][Size];
        boolean whetherRead=read(filename);
        count = 0;
        for (int i=0;i<array.length; i++){
            for (int j=0; j<array[0].length; j++){
                array[i][j] = new Cell(i, j, list.get(count));
                saveArray[i][j]= new Cell(i,j,list.get(count));
                count++;
            }
        }
    }

    public Board(int N){
        Board b=new Board();
        Random rand= new Random();
        int count=0;
        //randomize the locked cell location and value.
        while(count<N){
            int row= rand.nextInt(9);
            int col= rand.nextInt(9);
            int value= rand.nextInt(8)+1;
            if (b.value(row,col)!=0){
                continue;
            }
            //check if the value is valid.
            if (b.validValue(row,col,value)){
                b.set(row,col,value,true);
                count++;
            }
        }
        b.updateSave();
    }

    public void updateSave(){
        //keep a copy of the initial board, in order to reset the board
        for (int i=0;i<array.length;i++){
            for (int j=0; j<array[0].length;j++){
                saveArray[i][j]=new Cell(i,j,array[i][j].getValue());
            }
        }
    }
    
    public void reset(){
        //assign the original board back to the current board, in order to reset the board
        for (int i=0;i<array.length;i++){
            for (int j=0; j<array[0].length;j++){
                array[i][j]=new Cell(i,j,saveArray[i][j].getValue());
            }
        }
    }

    //functions
    public boolean read(String filename) {
        try {
            FileReader a = new FileReader(filename);
            BufferedReader b = new BufferedReader(a);
            list = new ArrayList<Integer>();
            String temp;
            while(true){
                String line = b.readLine();
                if (line==null){
                    break;
                }
                for(String word:line.split(" ")){
                    temp = word.trim();
                    word = temp;
                    if(word.equals("")){
                        continue;
                    }
                    else{
                        list.add(Integer.parseInt(word));
                    }
                }
            }
            b.close();
            return true;
        }
        catch(FileNotFoundException ex) {
          System.out.println("Board.read():: unable to open file " + filename );
        }
        catch(IOException ex) {
          System.out.println("Board.read():: error reading file " + filename);
        }
    
        return false;
    }

    public String toString(){
        String s = "";
        for (int i=0; i<3; i++){
            s+=array[i][0].getValue()+" "+array[i][1].getValue()+" "+array[i][2].getValue()+"  ";
            s+=array[i][3].getValue()+" "+array[i][4].getValue()+" "+array[i][5].getValue()+"  ";
            s+=array[i][6].getValue()+" "+array[i][7].getValue()+" "+array[i][8].getValue();
            s+="\n";
        }
        s+="\n";
        for (int i=3; i<6; i++){
            s+=array[i][0].getValue()+" "+array[i][1].getValue()+" "+array[i][2].getValue()+"  ";
            s+=array[i][3].getValue()+" "+array[i][4].getValue()+" "+array[i][5].getValue()+"  ";
            s+=array[i][6].getValue()+" "+array[i][7].getValue()+" "+array[i][8].getValue();
            s+="\n";
        }
        s+="\n";
        for (int i=6; i<9; i++){
            s+=array[i][0].getValue()+" "+array[i][1].getValue()+" "+array[i][2].getValue()+"  ";
            s+=array[i][3].getValue()+" "+array[i][4].getValue()+" "+array[i][5].getValue()+"  ";
            s+=array[i][6].getValue()+" "+array[i][7].getValue()+" "+array[i][8].getValue();
            s+="\n";
        }
        return s;
    }

    public int getCols(){
        //returns the number of columns
        return Size;
    }

    public int getRows(){
        //returns the number of rows
        return Size;
    }

    public Cell get(int r, int c){
        //returns the Cell at location r, c
        return array[r][c];
    }

    public boolean isLocked(int r, int c){
        //returns whether the Cell at r, c, is locked
        return array[r][c].isLocked();
    }

    public int value(int r, int c){
        //returns the value at Cell r, c
        return array[r][c].getValue();
    }

    public void set(int r,int c, int value){
        //sets the value of the Cell at r, c
        array[r][c].setValue(value);
    }

    public void set(int r, int c, int value, boolean locked){
        //sets the value and locked fields of the Cell at r, c
        array[r][c].setValue(value);
        array[r][c].setLocked(locked);
    }

    public boolean validValue(int row, int col, int value){
        //tests if the given value is a valid value at the given row and column of the board
        for (int i=0; i<array[0].length; i++){
            if((array[row][i].getValue()==value) && (i!=col)){
                return false;
            }
            else if((array[i][col].getValue()==value) && (i!=row)){
                return false;
            }
        }
        for (int j=(row/3)*3; j<(row/3)*3+3;j++){
            for (int k=(col/3)*3; k<(col/3)*3+3;k++){
                if((array[j][k].getValue()==value) && (j!=row) && (k!=col)){
                    return false;
                }
            }
        }        
       return true; 
    }

    public boolean validSolution(){
        //returns true if the board is solved
        for (int i=0; i<array.length;i++){
            for (int j=0; j<array[0].length;j++){
                if (!validValue(i,j,array[i][j].getValue())){
                    return false;
                }
            }
        }
        return true;
    }

    public void draw(Graphics g, int scale){
        int xLocation=scale;
        int yLocation=scale;
        int offDis=10;
        //draw cols
        g.drawLine(xLocation+scale*3, yLocation, xLocation+scale*3,yLocation+scale*array.length);
        g.drawLine(xLocation+scale*6, yLocation, xLocation+scale*6,yLocation+scale*array.length);
        g.drawLine(xLocation, yLocation+scale*3, xLocation+scale*array.length,yLocation+scale*3);
        g.drawLine(xLocation, yLocation+scale*6, xLocation+scale*array.length,yLocation+scale*6);
        for (int i=0;i<array.length;i++){
            for (int j=0; j<array[0].length;j++){
                //in order to prevent drawing a lot of 0 on the grid, I skip them to make the grid looks better.   
                array[j][i].draw(g,scale+scale*i+offDis,scale*2+scale*j-offDis,scale);
            }
        }
    }

    public static void main(String[] argv){
        Board d = new Board(argv[0]);
        System.out.println(d.toString());
        System.out.println(d.validValue(1, 1, 4));
        System.out.println(d.validValue(1, 1, 3));
        System.out.println(d.validValue(1, 1, 2));
        System.out.println(d.validValue(1, 8, 2));
        System.out.println(d.validValue(1, 8, 1));
        System.out.println(d.validValue(8, 5, 4));
        System.out.println(d.validValue(8, 5, 3));

        System.out.println(d.validSolution());
    }
}