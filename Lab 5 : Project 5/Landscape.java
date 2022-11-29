/**
 * Di Luo
 * CS 231
 * Project 5
 * Landscape.java
 */

import java.util.ArrayList;
import java.awt.Graphics;

public class Landscape{
    //fields
    private int w;
    private int h;
    private ArrayList<CheckoutAgent> agentList;
    private LinkedList<Customer> finishedCustomers;

    //constructor
    public Landscape(int w, int h, ArrayList<CheckoutAgent> checkouts ){
        this.w = w;
        this.h = h;
        agentList = checkouts;
        finishedCustomers = new LinkedList<Customer>();
    }

    //funcitons
    //return the height of the Landscape
    public int getHeight() {
        return h;
    }

    //return the width of the Landscape
    public int getWidth() {
        return w;
    }

    //return a string indicating how many checkouts and finished customers are in the landscape
    public String toString() {
        String s = "";
        s+="Here are " + agentList.size() + " checkouts and " + finishedCustomers.size() + " finished customers.";
        return s;
    }

    //add the Customer to the list of finished customers
    public void addFinishedCustomer(Customer c ){
        finishedCustomers.addLast(c);
    }

    //loop through the CheckoutAgents, calling the draw method on each
    public void draw( Graphics g ) {
        for (CheckoutAgent i: agentList){
            i.draw(g);
        }
    }

    //loop through all of the CheckoutAgents, and call updateState
    public void updateCheckouts(){
        for (CheckoutAgent i: agentList){
            i.updateState(this);
        }
    }

    //compute and print the average and standard deviation of the time-to-leave for all of the Customers in the finished customer list
    public void printFinishedCustomerStatistics(){
        int totalTime = 0;
        for (Customer i: finishedCustomers){
            totalTime += i.getTime();
        }
        double average = totalTime/finishedCustomers.size();
        System.out.println("The average time to leave is " + (int)(average) + ".");

        double standardDeviation = 0;
        for (Customer i: finishedCustomers){
            standardDeviation += Math.pow(i.getTime()-average, 2);
        }
        System.out.println("The standard deviation of time to leave is " + (int)(Math.sqrt(standardDeviation/finishedCustomers.size())) + ".");

    }
}