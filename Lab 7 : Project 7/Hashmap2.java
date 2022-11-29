/**
 * Di Luo
 * CS 231
 * Project 7
 * Hashmap2.java
 */


/**
 * Extension 4: open hash table 
 */


import java.io.*;
import java.util.Comparator;
import java.util.ArrayList;

public class Hashmap2<K,V> implements MapSet<K,V>{
    //fields
    private Object[] hashTable;
    private int size;
    private int numCollisions;
    private Comparator<K> comp;
    private int numKvps;
    private int capacity;

    //constructor
    public Hashmap2(Comparator<K> comp){
        hashTable = new Object[100];
        this.size = 0;
        this.comp = comp;
        this.numCollisions=0;
        this.numKvps = 0;
        this.capacity = 100;
        for (int i=0; i<100; i++){
            hashTable[i] = new BSTMap<K,V>(this.comp);
        }
    }

    public Hashmap2(Comparator<K> comp, int capacity){
        hashTable = new Object[capacity];
        this.size = 0;
        this.comp = comp;
        this.numCollisions=0;
        this.numKvps = 0;
        this.capacity = capacity;
        for (int i=0; i<capacity; i++){
            hashTable[i] = new BSTMap<K,V>(this.comp);
        }
    }

    //functions

    //Use Java's hash function
	private int hash(K key){
	    // make sure hash is positive and fits in valid array bounds
	    return Math.abs(key.hashCode()) % hashTable.length;
    }

    // adds or updates a key-value pair
    // If there is already a piar with new_key in the map, then update
    // the pair's value to new_value.
    // If there is not already a pair with new_key, then
    // add pair with new_key and new_value.
    // returns the old value or null if no old value existed
    public V put( K new_key, V new_value ){
        int index = hash(new_key);
        BSTMap<K,V> tree = (BSTMap<K,V>)hashTable[index];
        if (tree.size()==0){
            tree.put(new_key, new_value);
            size++;
            numKvps++;
            if (size>=hashTable.length/2){
                Object[] tempHashTable = new Object[hashTable.length*2];
                for (int i=0; i<(hashTable.length*2); i++){
                    tempHashTable[i] = new BSTMap<K,V>(this.comp);
                }
                this.size = 0;
                this.numCollisions=0;
                this.numKvps = 0;
                ArrayList<KeyValuePair<K,V>> pairs = this.entrySet();
                hashTable = tempHashTable;
                for (KeyValuePair<K,V> i: pairs){
                    this.put(i.getKey(), i.getValue());
                }   
            }
            return null;
        }
        else{
            if (tree.containsKey(new_key)){
                V oldValue = tree.get(new_key);
                tree.put(new_key, new_value);
                numCollisions++;
                return oldValue;
            }
            else{
                tree.put(new_key, new_value);
                numKvps++;
                return null;
            }
        }
    } 
    
    // Returns true if the map contains a key-value pair with the given key
    public boolean containsKey( K key ){
        for (Object i: hashTable){
            BSTMap<K,V> tree = (BSTMap<K,V>)i;
            if (tree.containsKey(key)){
                return true;
            }
        }
        return false;
    }
    
    // Returns the value associated with the given key.
    // If that key is not in the map, then it returns null.
    public V get( K key ){
        int index = hash(key);
        BSTMap<K,V> tree = (BSTMap<K,V>)hashTable[index];
        return tree.get(key);
    }
    
    // Returns an ArrayList of all the keys in the map. There is no
    // defined order for the keys.
    public ArrayList<K> keySet(){
        ArrayList<K> keyList = new ArrayList<K>();
        for (Object i: hashTable){
            BSTMap<K,V> tree = (BSTMap<K,V>)i;
            if (tree.size()==0){
                continue;
            }
            else{
                for(K j: tree.keySet()){
                    boolean a = keyList.add(j);
                }
            }
        }
        return keyList;
    }

    // Returns an ArrayList of all the values in the map. These should
    // be in the same order as the keySet.
    public ArrayList<V> values(){
        ArrayList<V> valueList = new ArrayList<V>();
        for (Object i: hashTable){
            BSTMap<K,V> tree = (BSTMap<K,V>)i;
            if (tree.size()==0){
                continue;
            }
            else{
                for(V j: tree.values()){
                    boolean b = valueList.add(j);
                }
            }
        }
        return valueList;
    }
    
    // return an ArrayList of pairs.
    // For the sake of the word-counting project, the pairs should
    // be added to the list by a pre-order traversal.
    public ArrayList<KeyValuePair<K,V>> entrySet(){
        ArrayList<KeyValuePair<K,V>> pairList = new ArrayList<KeyValuePair<K,V>>();
        for (Object i: hashTable){
            BSTMap<K,V> tree = (BSTMap<K,V>)i;
            if (tree.size()==0){
                continue;
            }
            else{
                for(KeyValuePair<K,V> j: tree.entrySet()){
                    boolean c = pairList.add(j);
                }
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
        hashTable = new Object[capacity];
        size=0;
        numCollisions=0;
        numKvps=0;
        for (int i=0; i<capacity; i++){
            hashTable[i] = new BSTMap<K,V>(this.comp);
        }
    }

    //toString method
    public String toString(){
        String s = "";
        int count = 0;
        for (Object i: hashTable){
            BSTMap<K,V> tree = (BSTMap<K,V>)i;
            s+=count+": "+tree.toString()+"\n";
            count++;
        }
        return s;
    }

    //get numCollisions
    public int getNumCollisions(){
        return this.numCollisions;
    }

    //for BSTMap, does nothing here
	public int depth(){
		return -1;
	}

    //test funciton
    public static void main(String[] argv){
        Hashmap2<String, Integer> hm = new Hashmap2<String, Integer>(new AscendingString(), 10);
        hm.put("a", 1);
	    hm.put("aa", 2);
	    hm.put("c", 3);
	    hm.put("d", 4);
        hm.put("e", 5);   
        
        System.out.println("Filling up hash table: ");
	    System.out.println("a : " + hm.get("a"));
	    System.out.println("aa : " + hm.get("aa"));
	    System.out.println("c : " + hm.get("c"));
	    System.out.println("d : " + hm.get("d"));
        System.out.println("e : " + hm.get("e"));
        System.out.println(hm.size());
        System.out.println(hm.containsKey("a"));
        System.out.println(hm.toString());
    }
}