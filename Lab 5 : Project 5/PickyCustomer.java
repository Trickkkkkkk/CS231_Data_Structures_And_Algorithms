/**
 * Di Luo
 * CS 231
 * Project 5
 * PickyCustomer.java
 */

import java.util.ArrayList;

public class PickyCustomer extends Customer{
    //fields

    //constructor
    // call the super class's constructor with the given numbers of items and the time steps
    public PickyCustomer( int num_items, int num_lines){
        super(num_items, num_lines);
    }

    //functions
    //returns the index of the CheckoutAgent with the shortest line
    public int chooseLine(ArrayList<CheckoutAgent> checkouts){
        int index = 0;
        for (int i=0; i<checkouts.size(); i++){
            if (checkouts.get(i).getNumInQueue()<checkouts.get(index).getNumInQueue()){
                index = i;
            }
        }
        return index;
    }
}