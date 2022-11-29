/**
 * Di Luo
 * CS 231
 * Project 1
 * Card.java
 */

// Card class create cards as objects
public class Card {
    //fields
    private int value;

    //constructors
    public Card(){
        System.out.println("Doesn't work, please input the value of the card as the parameter.");
    }

    public Card(int v){
        //create a card object
        //range checking
        if (v>0 && v<12){
            value = v;
        }
        else{
            System.out.println("The value of the card should be between 1 and 11.");
        }     
    }

    //accessors
    public int getValue(){
        //get the value of the card
        return value;
    }
    
    // Main function that prints the value of card A
    public static void main(String[] args){
        Card A = new Card(11);
        int aValue = A.getValue();
        System.out.println("The value of the card A is " + aValue + ".");
    }
}