/**
 * Di Luo
 * CS 231
 * Lab 7
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

        //get the depth of the tree
		public int depth() {
			int leftDepth = -1;
			int rightDepth = -1;
			if (left != null){
				leftDepth = left.depth();
			}
			if (right != null){
				rightDepth = right.depth();
			}
			if (rightDepth > leftDepth){
				return 1 + rightDepth;
			}
			else{
				return 1 + leftDepth;
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

    // do a recursive pre-order traversal to see if the key is in the tree.
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

    // Returns true if the map contains a key-value pair with the given key
    public boolean containsKey( K key ){
        return traversePreForContainsKey(this.root, key);
    }

    // do a recursive in-order traversal to get all keys.
    public void traverseOrderForKey( TNode root ) {
        if( root == null ) {return;}
        traverseOrderForKey( root.getLeft() );
        boolean a = keyList.add(root.getKey());
        traverseOrderForKey( root.getRight() );
    }

    // Returns an ArrayList of all the keys in the map in order of keys.
    public ArrayList<K> keySet(){
        keyList = new ArrayList<K>();
        this.traverseOrderForKey(this.root);
        return keyList;
    }

    // do a recursive in-order traversal to get all values.
    public void traverseOrderForValue( TNode root ) {
        if( root == null ) {return;}
        traverseOrderForValue( root.getLeft() );
        boolean b = valueList.add(root.getValue());
        traverseOrderForValue( root.getRight() );
    }

    // Returns an ArrayList of all the values in the map in order of keys.
    public ArrayList<V> values(){
        valueList = new ArrayList<V>();
        this.traverseOrderForValue(this.root);
        return valueList;
    }

    // do a recursive pre-order traversal to get all pairs.
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
    }

    // do a recursive pre-order traversal to build up a string describing the tree.
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

    //gives a string showing the structure of the tree
    public String toString(){
        String s = "root";
        String indentation = "\t";
        s += this.traversePreForToString(this.root, indentation);
        return s;
    }

    //get the depth of the tree
	public int depth() {
		if (root == null){
			return 0;
		}
		else{
			return root.depth();
		}
	}

    //for Hashmap, does nothing here
	public int getNumCollisions(){
		return -1;
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

        System.out.println( bst.get( "eleven" ) );
        System.out.println( bst.get( "zero" ) );


        // put more test code here
        System.out.println();
        System.out.println( bst.size() );
        if (bst.containsKey("six")){
            System.out.println("Have the key six");
        }
        if (bst.containsKey("one")){
            System.out.println("Have the key one");
        }

        ArrayList<String> keyList = bst.keySet();
        ArrayList<Integer> valueList = bst.values();
        ArrayList<KeyValuePair<String,Integer>> pairList = bst.entrySet();
        for (String i: keyList){
            System.out.println( i );
        }
        System.out.println();
        for (Integer j: valueList){
            System.out.println( String.valueOf(j) );
        }
        System.out.println();
        for (KeyValuePair<String, Integer> k: pairList){
            System.out.println(k.toString());
        }
        System.out.println();
        System.out.println(bst.toString());
    }
}

//comparator class
class AscendingString implements Comparator<String>{
    public int compare( String a, String b ){
        return a.compareTo(b);
    }

}