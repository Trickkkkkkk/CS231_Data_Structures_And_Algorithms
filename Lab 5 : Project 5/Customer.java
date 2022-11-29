/**
 * Di Luo
 * CS 231
 * Project 5
 * Customer.java
 */

import java.util.ArrayList;

public abstract class Customer{
    //fields
    private int time;
    private int item;

    //constructors
    //set number of items with given parameter, and set time step to 0
    public Customer(int num_items){
        this.item = num_items;
        this.time = 0;
    }

    //set number of item and time step with given parameter
    public Customer(int num_items, int time_steps){
        this.item = num_items;
        this.time = time_steps;
    }

    //functions
    //increments the number of time steps
    public void incrementTime(){
        time++;
    }

    //returns the number of time steps
    public int getTime(){
        return time;
    }

    //decrements the number of items (indicating another item has been paid for)
    public void giveUpItem(){
        item--;
    }

    //returns the number of items
    public int getNumItems(){
        return item;
    }

    //abstract method of line choosing strategy
    public abstract int chooseLine(ArrayList<CheckoutAgent> checkouts);
}