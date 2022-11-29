/**
 * Di Luo
 * CS 231
 * Project 5
 * CheckoutAgent.java
 */

import java.awt.Graphics;

public class CheckoutAgent{
    //fields
    private MyQueue<Customer> queue;
    private int x;
    private int y;

    //constructor
    //The queue should be initialized to an empty MyQueue<Customer>
    public CheckoutAgent(int x, int y){
        queue = new MyQueue<Customer>();
        this.x = x;
        this.y = y;
    }

    //functions
    //add a Customer to its queue
    public void addCustomerToQueue( Customer c ){
        queue.offer(c);
    }

    //returns the number of Customers in its queue
    public int getNumInQueue(){
        return queue.size();
    }

    //draws the CheckoutAgent as a rectangle near
    //the bottom of the window with a height proportional 
    //to the number of Customers in the queue
    public void draw(Graphics g){
        g.drawRect(this.x, this.y-10*this.getNumInQueue(), 10, 10*this.getNumInQueue());
    }

    //update the state of checkout agent
    public void updateState(Landscape scape){
        for (Customer i: queue){
            i.incrementTime();
        }
        if (queue.peek()!=null) {
            queue.peek().giveUpItem();
            if (queue.peek().getNumItems()==0){
                scape.addFinishedCustomer(queue.poll());
            }
        }
    }
}