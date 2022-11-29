/**
 * Di Luo
 * CS 231
 * Project 3: Sudoku
 * Sudoku.java
 */

import java.util.Random;
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

public class Sudoku{

    private Board b;
    private LandscapeDisplay l;
    private int count=0;

    public Sudoku(){
        b = new Board();
        l = new LandscapeDisplay(b,30);
    }

    public Sudoku(int N, boolean visual){
        b=new Board();
        Random rand= new Random();
        int count=0;
        //randomlize the value 
        while(count<N){
            int row= rand.nextInt(9);
            int col= rand.nextInt(9);
            int value= rand.nextInt(8)+1;
            if (b.value(row,col)!=0){
                continue;
            }
            if (b.validValue(row,col,value)){
                b.set(row,col,value,true);
                count++;
            }
        }
        l= new LandscapeDisplay(b,30);
        b.updateSave();
    }

    public Sudoku(String Filename,boolean visual){
        b = new Board(Filename);
    }
    
    public Board getBoard(){
        return b;
    }
    
    public void resetCount(){
        count=0;
    }
    
    public int runTime(){
        return count;
    }

    public boolean Solve(LandscapeDisplay display, int delay){
        CellStack cs= new CellStack(100);
        int curRow, curCol, curValue, time;
        curRow=0;
        curCol=0;
        time=0;
        curValue=1;
        while(cs.size()<(b.Size*b.Size)){
            time++;
            if( delay > 0 ) {
                try {
                    Thread.sleep(delay);
                }
                catch(InterruptedException ex) {
                    System.out.println("Interrupted");
                }
                display.repaint();
            }
            if (b.get(curRow, curCol).isLocked()){
                cs.push(b.get(curRow, curCol));
                curCol++;
                if(curCol==b.Size){
                    curCol=0;
                    if(curRow<9)
                    curRow++;
                }
                continue;
            }   
            for (; curValue<10; curValue++){
                if(b.validValue(curRow,curCol,curValue)){
                    break;
                }
            }
            if(curValue<10){
                Cell temp= new Cell(curRow,curCol,curValue);
                b.set(curRow, curCol, curValue);
                cs.push(temp);
                curCol++;
                if(curCol==b.Size){
                    curCol=0;
                    if(curRow<9)
                    curRow++;
                }
                curValue=1;
            }
            else{
                if(cs.size()>0){
                    Cell tempCell= cs.pop();
                    while(tempCell.isLocked()){
                        if(cs.size()>0){
                            tempCell=cs.pop();
                        }
                        else{
                            System.out.println(time);
                            return false;
                        }
                    }
                    curRow=tempCell.getRow();
                    curCol=tempCell.getCol();
                    curValue=tempCell.getValue()+1;
                    b.get(curRow, curCol).setValue(0);
                }
                else{
                    System.out.println(time);
                    return false;
                }
            }  
        }
        System.out.println(time);
        return true;
    }

    public static void main(String[] argv){
        Sudoku a = new Sudoku(40, true);
        System.out.println(a.b);
        boolean whetherSolve = a.Solve(a.l, 0);
        System.out.println(a.b);

    }
}