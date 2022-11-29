/**
 * Di Luo
 * CS 231
 * Project 8
 * PQHeap.java
 */

import java.util.Comparator;
import java.util.*;

public class PQHeap<T>{
	//fields
	private Object[] heap;
	private int numItems;
	private Comparator<T> comp;
	private int parent;
	private int child;
	private int leftChild;
	private int rightChild;

	//constructor 
	//initializes the empty heap
	public PQHeap(Comparator<T> comparator){
		heap = new Object[100];
		numItems = 0;
		comp = comparator;
	}

	//functions
	//returns the number of elements in the heap
	public int size(){
		return numItems;
	}

	//adds the value to the heap and increments the size
	public void add(T obj){
		ensureCapacity();
		heap[numItems] = obj;
		numItems++;
		reheapUp();
	}

	//removes and returns the highest priority element from the heap
	public T remove(){
		if (numItems == 0){
			throw new IllegalStateException("PQ is empty.");
		}
	  	T h = (T)heap[0];
		heap[0] = heap[--numItems];
		reheapDown();
		return h;
	}

	//if the array is half full, double the size of the heap
	private void ensureCapacity(){
		if (numItems < heap.length/2){
			return;
		}
		Object[] tempHeap = new Object[2*heap.length];
		for (int i = 0; i < heap.length; i++){
			tempHeap[i] = (T) heap[i];
		}
		heap = tempHeap;
	}

	//swap two positions
	private void swap(int i, int j){
		T temp = (T)heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}

	//swap the child with the parent if the child has a higher priority
	private void reheapUp(){
		child = numItems-1;
		while (child>0){
			int parent = (child-1)/2;
			if (comp.compare((T)heap[child], (T)heap[parent]) > 0){
				swap(child, parent);
				child = (child-1)/2;
			}
			else{
				return;
			}
		}
	}

	//if the parent has lower priority than either child, swap with the bigger child
	private void reheapDown(){    
		parent = 0;
		leftChild = 2*parent+1;
		rightChild = 2*parent+2;

		while (leftChild<=numItems-1){
			int biggerChild = leftChild;
			if ((rightChild<numItems) && comp.compare((T) heap[rightChild], (T) heap[leftChild])>0){
				biggerChild = rightChild;
			}
			if (comp.compare((T)heap[parent], (T)heap[biggerChild]) < 0){
				swap(parent, biggerChild);
				parent = biggerChild;
				leftChild = 2*parent+1;
				rightChild = 2*parent+2;
			}
			else{
				return;
			}
		}
	}

	//create a string showing the heap
	public String toString(){
		String s = "";
		int level = 0;
		int leftn = numItems;
		while (leftn>0) {
			int count = 1;
			int pow = (int)Math.pow(2, level);
			while (count <= pow){
				if (leftn == 0){
					break;
				}
				s += heap[numItems-leftn] + " ";
				count++;
				leftn--;
			}
			if (leftn != 0){
				s += "\n";
			}
			level++;
		}
		return s;
	}

	//returns the heap
	public Object[] getHeap(){
		return heap;
	}
}