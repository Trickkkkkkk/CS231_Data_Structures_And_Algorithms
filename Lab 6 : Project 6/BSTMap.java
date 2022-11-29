/**
 * Di Luo
 * CS 231
 * Lab 6
 * BSTMap.java
 */

import java.util.ArrayList;
import java.util.Comparator;

//the main tree class
public class BSTMap<K, V> implements MapSet<K, V>{
    //fields
    private TNode root;
    private Comparator<K> comp;
    private int size;
    private ArrayList<K> keyList;
    private ArrayList<V> valueList;
    private ArrayList<KeyValuePair<K,V>> pairList;

    //constructor: takes in a Comparator object
    public BSTMap( Comparator<K> comp) {
        this.root = null;
        this.comp = comp;
        this.size = 0;
    }

    //TNode class
    public class TNode{
        //fields
        private TNode left;
        private TNode right;
        private KeyValuePair<K, V> kvp;
        private String s;

        //constructor
        public TNode( K k, V v ){
            left = null;
            right = null;
            kvp = new KeyValuePair<K,V>(k, v);
        }

        // functions
        // Takes in a key, a value, and a comparator and inserts the TNode
        // Returns the old value of the node, if replaced, or null if inserted
        public V put( K key, V value, Comparator<K> comp ) {
            // implement the binary search tree put
            if (comp.compare(key, this.kvp.getKey())==0){
                V oldValue = this.kvp.getValue();
                this.kvp.setValue(value);
                return oldValue;
            }
            else if(comp.compare(key, this.kvp.getKey())<0){
                if (this.left == null){
                    this.left = new TNode(key, value);
                    return null;
                }
                else{
                    return this.left.put(key, value, comp);
                }
            }
            else{
                if (this.right == null){
                    this.right = new TNode(key, value);
                    return null;
                }
                else{
                    return this.right.put(key, value, comp);
                }
            }
        }

        // Takes in a key and a comparator
        // Returns the value associated with the key or null
        public V get( K key, Comparator<K> comp ) {
            if (comp.compare(key, this.kvp.getKey())==0){
                return this.kvp.getValue();
            }
            else if(comp.compare(key, this.kvp.getKey())<0){
                if (this.left == null){
                    return null;
                }
                else{
                    return this.left.get(key, comp);
                }
            }
            else{
                if (this.right == null){
                    return null;
                }
                else{
                    return this.right.get(key, comp);
                }
            }
        }

        //return left
        public TNode getLeft() { return this.left; }

        //return right
        public TNode getRight() { return this.right; }
        
        //return the key of kvp
        public K getKey() { return this.kvp.getKey(); }

        //return the value of kvp
        public V getValue() { return this.kvp.getValue(); }

        //return kvp
        public KeyValuePair<K,V> getKvp(){ return this.kvp; }

        //set left node
        public void setLeft( TNode l ) { this.left = l; }

        //set right node
	    public void setRight( TNode r ) { this.right = r; }
    }

    //functions
    //adds or updates a key-value pair
    // If there is already a piar with new_key in the map, then update
	// the pair's value to new_value.
	// If there is not already a pair with new_key, then
	// add pair with new_key and new_value.
	// returns the old value or null if no old value existed
    public V put( K key, V value) {
        // check for and handle the special case
        if (root == null){
            root = new TNode(key, value);
            return null;
        }

		// call the root node's put method
        else{
            return root.put(key, value, this.comp);
        }
    }

    // gets the value at the specified key or null
    public V get( K key ) {
        // check for and handle the special case
        if (root == null){
            return null;
        }

        // call the root node's get method
        else{
            return root.get(key, this.comp);
        }
    }

    // recursive function of containsKey
    public boolean traversePreForContainsKey( TNode root, K key) {
        if( root == null ) {return false;}
        else {
            if (comp.compare(key, root.kvp.getKey())==0){
                return true;
            }
            else if (comp.compare(key, root.kvp.getKey())<0){
                return traversePreForContainsKey( root.getLeft(), key );
            }
            else{
                return traversePreForContainsKey( root.getRight(), key );
            }
        }
    }

    // Returns true if the map contains the given key
    public boolean containsKey( K key ){
        return traversePreForContainsKey(this.root, key);
    }

    // recursive function of keySet.
    public void traverseOrderForKey( TNode root ) {
        if( root == null ) {return;}
        traverseOrderForKey( root.getLeft() );
        boolean a = keyList.add(root.getKey());
        traverseOrderForKey( root.getRight() );
    }

    // Returns an ArrayList of all the keys in the map in order.
    public ArrayList<K> keySet(){
        keyList = new ArrayList<K>();
        this.traverseOrderForKey(this.root);
        return keyList;
    }

    // recursive function of values
    public void traverseOrderForValue( TNode root ) {
        if( root == null ) {return;}
        traverseOrderForValue( root.getLeft() );
        boolean b = valueList.add(root.getValue());
        traverseOrderForValue( root.getRight() );
    }

    // Returns an ArrayList of all the values in the map in the order of keys.
    public ArrayList<V> values(){
        valueList = new ArrayList<V>();
        this.traverseOrderForValue(this.root);
        return valueList;
    }

    // recursive function of entrySet
    public void traversePreForPair( TNode root ) {
        if( root == null ) {return;}
        boolean c = pairList.add(root.getKvp());
        traversePreForPair( root.getLeft() );
        traversePreForPair( root.getRight() );
    }
    
    // return an ArrayList of pairs.
    // For the sake of the word-counting project, the pairs should
    // be added to the list by a pre-order traversal.
    public ArrayList<KeyValuePair<K,V>> entrySet(){
        pairList = new ArrayList<KeyValuePair<K,V>>();
        this.traversePreForPair(this.root);
        return pairList;
    }

    // Returns the number of key-value pairs in the map.
    public int size(){
        return this.keySet().size();
    }
        
    // removes all mappings from this MapSet
    public void clear(){
        this.root = null;
        this.size = 0;
    }

    //extension 1: toString
    // recursive function of toString
    public String traversePreForToString( TNode root, String indentation ) {
        if (root==null){return "";}
        String ss = "";
        ss += indentation + root.kvp.toString() + "\n";
        if (root.getLeft()!=null){
            ss += "left";
            ss += traversePreForToString( root.getLeft(), indentation+"\t");
        }
        if (root.getRight()!=null){
            ss += "right";
            ss += traversePreForToString( root.getRight(), indentation+"\t");
        }
        return ss;
    }

    //extension 1: toString
    //gives a string showing the structure of the tree
    public String toString(){
        String s = "root";
        String indentation = "\t";
        s += this.traversePreForToString(this.root, indentation);
        return s;
    }

    //extension 2: remove
    // returns the maximum node in a tree givena non-null root
    private TNode getMax(TNode root) {
    	if( root.getRight() == null ) {
	    return root;
    	}
    	return getMax( root.getRight() );
    }

    //extension 2: remove
    // return the tree with the maximum value node removed
    private TNode removeMax(TNode root) {
        if( root.getRight() == null ) {
            return root.getLeft(); // return left child of the node to remove
        }
        root.setRight( removeMax( root.getRight() ) );
        return root;
    }

    //extension 2: remove
    public void remove( K key ) {
        if( this.root == null ) { return; } // empty case
    
        // recursive remove
        this.root = this.remove( key, this.root );
        }
    
    // returns the root of the tree with the specified key removed
    public TNode remove( K key, TNode root ) {
        // if root is empty, we didn't find the value to remove
        if( root == null ) { return null; }

        // if root is the node we want to remove
        if( comp.compare(key, root.getKey())==0 ) { // remove the root
            // no children case, return null
            if( root.getLeft() == null && root.getRight() == null ) {
                return null;
            }
            else if( root.getLeft() == null ) { // one child case
                return( root.getRight() );
            }
            else if( root.getRight() == null ) { // one child case
                return( root.getLeft() );
            }
            else { // two child case
                // get the largest node in left subtree
                TNode leftmax = this.getMax( root.getLeft() );
                    
                // remove the largest node in the left subtree
                root.setLeft( this.removeMax( root.getLeft() ) );
                    
                leftmax.setLeft( root.getLeft() );
                leftmax.setRight( root.getRight() );
                    
                return( leftmax );
            }
        }

        // root was not the value we want to remove, so call remove from the proper subtree
        if( comp.compare(key, root.getKey())<0 ) { // remove from left subtree
            root.setLeft( this.remove( key, root.getLeft() ) );
        }
        else { // remove from the right subtree
            root.setRight( this.remove( key, root.getRight() ) );
        }
        return root;
        }

    // test function
    public static void main( String[] argv ) {
        // create a BSTMap
        BSTMap<String, Integer> bst = new BSTMap<String, Integer>( new AscendingString() );

        System.out.println( bst.size() );

        bst.put( "twenty", 20 );
        bst.put( "ten", 10 );
        bst.put( "eleven", 11 );
        bst.put( "five", 5 );
        bst.put( "six", 6 );

        System.out.println( "The value of key eleven is "+bst.get( "eleven" ) );
        System.out.println( "The value of key zero is "+bst.get( "zero" ) );


        // put more test code here
        System.out.println();
        System.out.println( "The size of the tree is "+bst.size() );
        if (bst.containsKey("six")){
            System.out.println("Have the key six");
        }
        if (bst.containsKey("one")){
            System.out.println("Have the key one");
        }

        ArrayList<String> keyList = bst.keySet();
        ArrayList<Integer> valueList = bst.values();
        ArrayList<KeyValuePair<String,Integer>> pairList = bst.entrySet();
        System.out.println("\nKey list");
        for (String i: keyList){
            System.out.println( i );
        }
        System.out.println("\nValue list");
        for (Integer j: valueList){
            System.out.println( String.valueOf(j) );
        }
        System.out.println("\nPair list");
        for (KeyValuePair<String, Integer> k: pairList){
            System.out.println(k.toString());
        }
        //extension 2: remove
        System.out.println("\n"+bst.toString());
        bst.remove("ten");
        System.out.println("\n"+"After remove ten: "+"\n"+bst.toString());
    }
}

//comparator class
class AscendingString implements Comparator<String>{
    public int compare( String a, String b ){
        return a.compareTo(b);
    }

}