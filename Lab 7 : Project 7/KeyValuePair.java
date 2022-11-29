/**
 * Di Luo
 * CS 231
 * Lab 7
 * KeyValuePair.java
 */

public class KeyValuePair<Key,Value>{
    //fields
    private Key key;
    private Value value;

    //constructor
    //initializing the key and value fields
    public KeyValuePair( Key k, Value v ){
        this.key = k;
        this.value = v;
    }

    //functions
    //return the key
    public Key getKey(){
        return this.key;
    }

    //return the value
    public Value getValue(){
        return this.value;
    }

    //set the value
    public void setValue( Value v ){
        this.value = v;
    }

    //return a String containing both the key and value
    public String toString(){
        String s = "";
        s += key + ":" + value;
        return s;
    }
}