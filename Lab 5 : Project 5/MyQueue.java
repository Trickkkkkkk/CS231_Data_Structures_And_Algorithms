/**
 * Di Luo
 * CS 231
 * Lab 5
 * MyQueue.java
 */

import java.util.Iterator;

public class MyQueue<T> implements Iterable<T>{

    //fields
    private Node head;
    private Node tail;
    private int size;

    //constructor
    //initializes the fields so it is a empty queue
    public MyQueue(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //functions
    //empties the queue (resets the fields so it is an empty queue)
    public void clear(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //returns the size of the queue
    public int size(){
        return size;
    }

    //put the new node on the end (tail) of the queue
    public void offer(T value) {
        Node n = new Node(value);
        if (size == 0){
            tail = n;
            head = tail;
        }
        else{
            tail.setNext(n);
            n.setPrev(tail);
            this.tail = n;
        }
        size++;
    }

    //remove and return the data in the node at the front (head) of the queue
    public T poll() {
        if (head == null){
            return null;
        }

        T retual = head.getValue();

        head = head.getNext();

        if (size == 1){
            tail = null;
        }
        else{
            head.setPrev(null);
        }
        size--;
        return retual;
    }

    //return the value at the front without removing it from the queue
    public T peek() {
        if (head == null){
            return null;
        }
        else{
            T retual = head.getValue();
            return retual;
        }
    }

    //get access to the first node
    public Node getFirst(){
        if (head == null){
            return null;
        }
        else{
            return head;
        }
    }

    private class Node{
        //fields
        private Node next;
        private Node prev;
        private T value;

        //constructor
        //a constructor that intializes next to node n and the container field to item
        public Node(T value){
            this.value = value;
            this.next = null;
            this.prev = null;
        }

        //functions
        //returns the value of the container field
        public T getValue() { return this.value; }

        //sets next to the given node
        public void setNext(Node next) { this.next = next; }

        //sets previous to the given node
        public void setPrev(Node prev) { this.prev = prev; }

        //returns the next field
        public Node getNext() { return this.next;}
        
        //returns the previous field
        public Node getPrev() { return this.prev;}
    }

    public Iterator<T> iterator() {
		return new LLIterator( this.head );
    }
    
    private class LLIterator implements Iterator<T> {
		Node curPtr;
	
		public LLIterator( Node initial ) {
			this.curPtr = initial;
		}
		
		public boolean hasNext() {
			return this.curPtr != null;
		}
		
		public T next() {
			T tmp = this.curPtr.getValue();
			this.curPtr = this.curPtr.getNext();
			return tmp;
		}		
	}

    /*//print out the things in the queue
    public String toString(){
        return list.toString();
    }*/

    //test functions
    public static void main(String[] args){
        MyQueue<Integer> q = new MyQueue<Integer>();
        q.offer(2);
        q.offer(3);
        q.offer(4);
        q.offer(5);
        Iterator<Integer> it = q.iterator();
		while(it.hasNext()) {
			Integer i = it.next();
			System.out.println(i + "; ");
		}
		System.out.println("done");
        System.out.println(q.peek());
        System.out.println(q.poll());
        System.out.println(q.peek());
        System.out.println(q.poll());
    }
}