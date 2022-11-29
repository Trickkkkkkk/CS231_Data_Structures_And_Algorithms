/**
 * Di Luo
 * CS 231
 * Project 5
 * Pick2Customer.java
 */

import java.util.ArrayList;
import java.util.Random;

public class Pick2Customer extends Customer{
    //fields

    //constructor
    // call the super class's constructor with the given numbers of items and the time steps
    public Pick2Customer( int num_items ){
        super(num_items, 2);
    }

    //functions
    //returns the index of the shorter of two randomly chosen queues
    public int chooseLine(ArrayList<CheckoutAgent> checkouts){
        Random rnd = new Random();
        int line1 = rnd.nextInt(checkouts.size());
        int line2 = rnd.nextInt(checkouts.size());
        while (line1 == line2){
            line2 = rnd.nextInt(checkouts.size());
        }
        if (checkouts.get(line1).getNumInQueue() > checkouts.get(line2).getNumInQueue()){
            return line2;
        }
        else{
            return line1;
        }
    }
}