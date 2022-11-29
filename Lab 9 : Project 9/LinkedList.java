/**
 * Di Luo
 * CS 231
 * Project 9
 * LinkedList.java
 */

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;

public class LinkedList<T> implements Iterable<T> {
    //fields
    private Node head;
    private int size = 0;
    private ArrayList<T> alist;
    private ArrayList<T> alist2;
    private T value;

    //constructor
    //initializes the fields so it is a empty list
    public LinkedList(){
        this.head = null;
    }

    //functions
    //empties the list (resets the fields so it is an empty list)
    public void clear(){
        this.head = null;
        this.size = 0;
    }

    //returns the size of the list
    public int size(){
        return size;
    }

    //inserts the item at the beginning of the list
    public void addFirst(T item){
        Node n = new Node(item, this.head);
        this.head = n;
        size++;
    }

    //appends the item at the end of the list
    public void addLast(T item){
        Node p, q;

        if (size == 0){
            q = new Node(item, null);
            this.head = q;
        }
        else{
            p = this.head;
            for(int i=0;i<size-1;i++) {
				p = p.getNext(); // move p to the next node
			}
            q = new Node(item, null);
            p.setNext(q);
        }
        size++;
    }

    //inserts the item at the specified position in the list
    public void add(int index, T item){
        if( index < 0 || index > this.size ) {
			System.out.println("add: index out of bounds");
			return;
		}
		// index is valid
		
		// check special case of index == 0
		if( index == 0 ) { // add to the head of the list
			Node n = new Node( item, this.head );
			this.head = n;
		}
		else { // general case, index > 0
			Node p = this.head; // step 1
			
			for(int i=0;i<index-1;i++) {
				p = p.getNext(); // move p to the next node
			}
			// now p references the node prior to location index
			
			Node n = new Node( item, p.getNext() ); // assign to n next the node after p
			p.setNext( n ); // step 5, assign p next a reference to the new node

		}
		this.size++; // added something to the stack
    }

    // remove and return the top item from the stack
	public T remove(int index) {
		Node p, q;
		
		if( index < 0 || index >= this.size) {
			System.out.println("remove: out of bounds");
			return null;
		}
		
		p = this.head;
		if( index == 0 ) { // special case
            this.head = p.getNext(); // set head to the second node
            this.size--;
            return p.getThing();
		}
		
		// not a special case, so move to the prior node
		for(int i=0;i<index-1;i++) {
			p = p.getNext();
		}
		// p is referencing the node before the one we want to remove
		q = p.getNext(); // q is the node to remove
		
		p.setNext( q.getNext() ); // set p's node to the one after q
        
        this.size--;
		// return the value we just removed
		return q.getThing();
	}

    //return a new LLIterator with head passed to the constructor
    public Iterator<T> iterator(){
        return new LLIterator(this.head);
    }

    //returns an ArrayList of the list contents in order
    public ArrayList<T> toArrayList(){
        ArrayList<T> alist = new ArrayList<T>();
        for (T item: this){
            alist.add(item);
        }
        return alist;
    }

    //returns an ArrayList of the list contents in shuffled order
    public ArrayList<T> toShuffledList(){
        ArrayList<T> alist2 = this.toArrayList();
        Collections.shuffle(alist2);
        return alist2;
    }

    private class Node{
        //fields
        Node next;
        T item;

        //constructor
        //a constructor that intializes next to node n and the container field to item
        public Node(T item, Node n){
            this.next = n;
            this.item = item;
        }

        //functions
        //returns the value of the container field
        public T getThing() { return item; }

        //sets next to the given node
        public void setNext(Node n) { next = n; }

        //returns the next field
        public Node getNext() { return next;}

        //set the value of the node
        public void setValue(T item) { this.item = item; }
    }

    private class LLIterator implements Iterator<T>{
        //fields
        Node curPtr;

        //constructor
        //creates and LLIterator given the head of the list
        public LLIterator (Node initial){
            this.curPtr = initial; 
        }

        //returns true if there are still values to traverse (if the current node reference is not null)
        public boolean hasNext(){
            return this.curPtr != null;
        }

        //returns the next item in the list, which is the item contained in the current node
        public T next(){
            T tmp = this.curPtr.getThing();
			this.curPtr = this.curPtr.getNext();
			return tmp;
        }
    }
}