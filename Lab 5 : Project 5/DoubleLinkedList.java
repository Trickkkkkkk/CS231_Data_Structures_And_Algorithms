/**
 * Di Luo
 * CS 231
 * Lab 5
 * DoubleLinkedList.java
 */

public class DoubleLinkedList<T>{

    //fields
    private Node head;
    private Node tail;
    private int size;

    //constructor
    //initializes the fields so it is a empty list
    public DoubleLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //functions
    //empties the list (resets the fields so it is an empty list)
    public void clear(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //returns the size of the list
    public int size(){
        return size;
    }

    //inserts the item at the beginning of the list
    public void addFirst(T item){
        if (head == null){
            Node n = new Node(item);
            n.setNext(null);
            n.setPrev(null);
            this.head = n;
            this.tail = n;
        }
        else{
            Node n = new Node(item);
            n.setNext(this.head);
            n.setPrev(null);
            this.head = n;
        }
        size++;
    }

    //appends the item at the end of the list
    public void addLast(T item){
        if (head == null){
            Node n = new Node(item);
            n.setNext(null);
            n.setPrev(null);
            this.head = n;
            this.tail = n;
        }
        else{
            Node n = new Node(item);
            n.setNext(null);
            n.setPrev(this.tail);
            this.tail = n;
        }
        size++;
    }

    //remove the item at the beginning of the list
    public T removeFirst(){
        if (head == null){
            System.out.println("Nothing in the list.");
            return null;
        }
        /*else if (size == 1){
            T retual = head.getValue();
            head = null;
            tail = null;
            size--;
            return retual;
        }
        else{
            T retual = head.getValue();
            head = head.getNext();
            head.setPrev(null);
            size--;
            return retual;
        }*/
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

    //return the value of the first item without removing it
    public T peekFirst(){
        if (head == null){
            System.out.println("Nothing in the list.");
            return null;
        }
        else{
            T retual = head.getValue();
            return retual;
        }
    }

    /*//print out the things in the queue
    public String toString(){
        String s = "";
        for (int i=0; i; i++){
            T item = head.getValue();
            s += item;
        }
    }*/

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
        public T getValue() { return value; }

        //sets next to the given node
        public void setNext(Node next) { this.next = next; }

        //sets previous to the given node
        public void setPrev(Node prev) { this.prev = prev; }

        //returns the next field
        public Node getNext() { return this.next;}
        
        //returns the previous field
        public Node getPrev() { return this.prev;}
    }
}