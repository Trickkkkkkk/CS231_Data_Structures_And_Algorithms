/**
 * Di Luo
 * CS 231
 * Project 3: Sudoku
 * CellStack.java
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


public class CellStack{

    //fields
    private Cell[] stack;
    private int max;
    private int N;

    //constructors
    public CellStack(){
        max = 10;
        stack = new Cell[10];
        N = 0;
    }

    public CellStack(int size){
        max = size;
        stack = new Cell[size];
        N = 0;
    }

    //functions
    public void push(Cell c){
        // if there is space for another item on the stack, push c onto the stack and return. 
        // If there is not space, allocate a new Cell array that is twice as big as the prior array, copy all of the elements from the old array to the new array, replace the old array, then push c onto the stack and return
        if (this.N>=stack.length){
            Cell tmp[] = new Cell[stack.length*2];
            for (int i=0; i<this.N; i++){
                tmp[i] = stack[i];
            }
            this.stack = tmp;
        }
        this.stack[this.N++] = c;
    }

    public Cell pop(){
        //if the stack is not empty, pop the top item off the stack and return it
        if (this.N>0){
            Cell top = stack[this.N-1];
            N--;
            return top;
        }
        else {
            return null;
        }
    }

    public int size(){
        //return the size of the stack
        return this.N;
    }

    public boolean empty(){
        //return true if the stack is empty, otherwise false
        if (this.N==0){
            return true;
        }
        else {
            return false;
        }
    }
}