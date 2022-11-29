/**
 * Di Luo
 * CS 231
 * Project 3: Sudoku
 * Cell.java
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

public class Cell{
    //fields
    private int row;
    private int col;
    private int value;
    private boolean isLocked;

    //constructors
    public Cell(){
        //initialize all valus to 0 or false
        row = 0;
        col = 0;
        value = 0;
    }

    public Cell(int row, int col, int value){
        //initialize the row, column, and value fields to the given parameter values. Set the locked field to false
        this.row = row;
        this.col = col;
        this.value = value;
        this.isLocked = false;
    }

    public Cell(int row, int col, int value, boolean locked){
        //initialize all of the Cell fields given the parameters
        this.row = row;
        this.col = col;
        this.value = value;
        this.isLocked = locked;
    }
    
    //functions
    public int getRow(){
        //return the Cell's row index
        return this.row;
    }

    public int getCol(){
        //return the Cell's column index
        return this.col;
    }

    public int getValue(){
        //return the Cell's value
        return this.value;
    }

    public void setValue(int newval){
        //set the Cell's value
        this.value = newval;
    }

    public boolean isLocked(){
        //return if the Cell is locked
        return isLocked;
    }

    public void setLocked(boolean lock){
        //set the Cell's locked value
        this.isLocked = lock;
    }

    public Cell clone(){
        //returns a new Cell with the same values as the existing Cell
        Cell a = new Cell(this.row, this.col, this.value, this.isLocked);
        return a;
    }

    public String toString(){
        //generates a string that describes the Cell's fields
        String a = "position: " + this.row + ", " + this.col + " value: " + this.value + " locked " + this.isLocked;
        return a;
    }

    public void draw(Graphics g, int x0, int y0, int scale){
        //draw the char
        char[] c = new char[1];
        c[0] = (char)('0' + this.value);
        g.drawChars(c, 0, 1, x0, y0);
    }
}