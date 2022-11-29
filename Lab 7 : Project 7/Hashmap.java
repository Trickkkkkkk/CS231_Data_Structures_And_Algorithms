/**
 * Di Luo
 * CS 231
 * Project 7
 * Hashmap.java
 */

import java.util.ArrayList;

public class Hashmap<K,V> implements MapSet<K,V>{
    //fields
    private Object[] hashTable;
    private boolean[] hasBeenUsed;
    private int numCollisions;
    private int numKvps;
    private int capacity;

    //constructor
    public Hashmap(){
        hashTable = new Object[100];
        this.numCollisions=0;
        this.numKvps = 0;
        hasBeenUsed = new boolean[100];
        this.capacity = 100;
    }

    public Hashmap(int capacity){
        hashTable = new Object[capacity];
        this.numCollisions=0;
        this.numKvps = 0;
        hasBeenUsed = new boolean[capacity];
        this.capacity = capacity;
    }

    //functions

    //Use Java's hash function
	private int hash(K key){
	    // make sure hash is positive and fits in valid array bounds
	    return Math.abs(key.hashCode()) % hashTable.length;
    }
    
    /*
    // Extension 3: another hash function
	private int hash(K key){
        return Math.floorMod(key.hashCode(), hashTable.length);
    }
    */

    // adds or updates a key-value pair
    // If there is already a piar with new_key in the map, then update
    // the pair's value to new_value.
    // If there is not already a pair with new_key, then
    // add pair with new_key and new_value.
    // returns the old value or null if no old value existed
    public V put(K key, V value){
		int index = getIndex(key);

		if (index >= 0){
			KeyValuePair<K, V> curPair = (KeyValuePair<K, V>) hashTable[index];
			V oldValue = curPair.getValue();
			curPair.setValue(value);
			return oldValue;
		}
		else{
			ensureCapacity();
			index = hash(key);

			if (hashTable[index] != null){
				index = nextIndex(index);
				numCollisions++;
			}
			while (hashTable[index] != null){
				index = nextIndex(index);
			}

			hashTable[index] = new KeyValuePair<K, V>(key, value);
			hasBeenUsed[index] = true;
			numKvps++;
			return null;
		}
	}
     
    //original ensureCapacity function
    //Checks to see if the hash table is large enough to hold one more item
	//If not, we rehash: double the size of the hash table and re-add all existing elements to it
	private void ensureCapacity(){
        if (numKvps < hashTable.length){
            return;
        }

        System.out.println("...Increasing capacity to " + 2*numKvps);

        // copy values in current hash table
        Object[] tempHashTable = new Object[numKvps];
        for (int i = 0; i < tempHashTable.length; i++){
            tempHashTable[i] = hashTable[i];
        }

        // reset numKvps and hasBeenUsed
        hashTable = new Object[2*numKvps];
        hasBeenUsed = new boolean[2*numKvps];
        numKvps = 0;
        
        for (int i = 0; i < tempHashTable.length; i++){
            KeyValuePair<K,V> curKvps = (KeyValuePair<K,V>) tempHashTable[i];
        
            if (curKvps != null){
                put(curKvps.getKey(), curKvps.getValue());
            }
        }
    }

    /*
    // Extension 2: more efficient data structure
    // new ensureCapacity function
    // the hashmap doule the size when the hash table is half full
    private void ensureCapacity(){
        if (numKvps < hashTable.length/2){
            return;
        }

        System.out.println("...Increasing capacity to " + 2*hashTable.length);

        // copy values in current hash table
        Object[] tempHashTable = new Object[numKvps];
        for (int i = 0; i < tempHashTable.length; i++){
            tempHashTable[i] = hashTable[i];
        }

        // reset numKvps and hasBeenUsed
        hashTable = new Object[2*hashTable.length];
        hasBeenUsed = new boolean[2*hashTable.length];
        numKvps = 0;
        
        for (int i = 0; i < tempHashTable.length; i++){
            KeyValuePair<K,V> curKvps = (KeyValuePair<K,V>) tempHashTable[i];
        
            if (curKvps != null){
                put(curKvps.getKey(), curKvps.getValue());
            }
        }
    }
    */

    //Returns true if the map contains a key-value pair with the given key
    public boolean containsKey( K key ){
    	return this.get(key) != null;
    }
    
    // Returns the value associated with the given key.
    // If that key is not in the map, then it returns null.
    public V get(K key){
		int index = getIndex(key);

		if(index < 0){
			return null;
		}
		else{
			KeyValuePair<K, V> pair = (KeyValuePair<K, V>) hashTable[index];
			return pair.getValue();
		}
	}
    
    // Returns an ArrayList of all the keys in the map. There is no
    // defined order for the keys.
    public ArrayList<K> keySet(){
    	ArrayList<K> keyList = new ArrayList<K>();
    	for (int i = 0; i < hashTable.length; i++){
    		KeyValuePair<K, V> k = (KeyValuePair<K, V>) hashTable[i];
    		if (k != null){
    			keyList.add(k.getKey());
    		}
    	}
    	return keyList;
    }

    // Returns an ArrayList of all the values in the map. These should
    // be in the same order as the keySet.
    public ArrayList<V> values(){
    	ArrayList<V> valueList = new ArrayList<V>();
    	for (int i = 0; i < hashTable.length; i++){
    		KeyValuePair<K, V> v = (KeyValuePair<K, V>) hashTable[i];
    		if (v != null){
    			valueList.add(v.getValue());
    		}
    	}
    	return valueList;
    }
    
    // return an ArrayList of pairs.
    // For the sake of the word-counting project, the pairs should
    // be added to the list by a pre-order traversal.
    public ArrayList<KeyValuePair<K,V>> entrySet(){
    	ArrayList<KeyValuePair<K, V>> pairList = new ArrayList<KeyValuePair<K, V>>();
    	for (int i = 0; i < hashTable.length; i++){
    		KeyValuePair<K, V> p = (KeyValuePair<K, V>) hashTable[i];
    		if (p != null){
    			pairList.add(p);
    		}
    	}
    	return pairList;
    }

    // Returns the number of key-value pairs in the map.
    public int size(){
        return numKvps;
    }
        
    // removes all mappings from this MapSet
    public void clear(){
        this.numKvps = 0;
        this.numCollisions = 0;
    	hashTable = new Object[capacity];
    	hasBeenUsed = new boolean[capacity];
    }

    //get numCollisions
    public int getNumCollisions(){
        return this.numCollisions;
    }

    //only for BSTMap, does nothing here
	public int depth(){
		return -1;
    }

    //get the index of a certain key
	public int getIndex(K key){
		int numTraversal = 0;
		int index = hash(key);

		while (numTraversal<hashTable.length && hasBeenUsed[index]){
			KeyValuePair<K, V> currPair = (KeyValuePair<K, V>) hashTable[index];
			if (currPair != null && key.equals(currPair.getKey())){
				return index;
			}
			else{
				index = nextIndex(index);
				numTraversal++;
			}
		}
		return -1;
    }
    
    //get the next index based on the current index
	public int nextIndex(int i){
		return (i+1 == hashTable.length) ? 0 : i+1;
	}

    //test funciton
    public static void main(String[] argv){
        Hashmap<String, Integer> hashTable = new Hashmap<String, Integer>(5);
        hashTable.put("a", 1);
        hashTable.put("aa", 2);
        hashTable.put("c", 3);
        hashTable.put("d", 4);
        hashTable.put("e", 5);
    
        System.out.println("Filling up hash table: ");
        System.out.println("a : " + hashTable.get("a"));
        System.out.println("aa : " + hashTable.get("aa"));
        System.out.println("c : " + hashTable.get("c"));
        System.out.println("d : " + hashTable.get("d"));
        System.out.println("e : " + hashTable.get("e"));
        System.out.println("Number of collisions: " + hashTable.getNumCollisions());
        
        System.out.println("Adding more...");
        hashTable.put("f", 6);
        hashTable.put("g", 7);
        hashTable.put("h", 8);
        System.out.println("Size now: " + hashTable.size());
        
        System.out.println("a : " + hashTable.get("a"));
        System.out.println("aa : " + hashTable.get("aa"));
        System.out.println("c : " + hashTable.get("c"));
        System.out.println("d : " + hashTable.get("d"));
        System.out.println("e : " + hashTable.get("e"));
        System.out.println("f : " + hashTable.get("f"));
        System.out.println("g : " + hashTable.get("g"));
        System.out.println("h : " + hashTable.get("h"));
        System.out.println("Number of collisions: " + hashTable.getNumCollisions());
    
        System.out.println("---------------------------------------");
        System.out.println(hashTable.values());
    }
}