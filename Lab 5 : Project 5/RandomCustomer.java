/**
 * Di Luo
 * CS 231
 * Project 5
 * RandomCustomer.java
 */

import java.util.Random;
import java.util.ArrayList;

public class RandomCustomer extends Customer{
    //fields

    //constructor
    // call the super class's constructor with the given number of items and 1 as the initial value for the time steps
    public RandomCustomer( int num_items ){
        super(num_items, 1);
    }

    //functions
    //returns an integer randomly chosen from the range 0 (inclusive) to the lenght of the list (exclusive)
    public int chooseLine(ArrayList<CheckoutAgent> checkouts){
        Random rnd = new Random();
        return rnd.nextInt(checkouts.size());
    }
}