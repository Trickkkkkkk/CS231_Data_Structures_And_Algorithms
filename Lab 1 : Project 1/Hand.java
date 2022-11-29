/**
 * Di Luo
 * CS 231
 * Project 1
 * Hand.java
 */

import java.util.ArrayList;

//Hand class creating hand objects that represent player or dealer
public class Hand {
    //fields
    private ArrayList<Card> hand;

    //constructors
    public Hand(){
        //create a hand object
        hand = new ArrayList<Card>();
    }

    //functions
    public void reset(){
        //reset the hand
        hand = new ArrayList<Card>();
    }

    public void add(Card card){
        //add card to hand
        hand.add(card);
    }

    //accessors
    public int size(){
        //return the size of hand
        return hand.size();
    }

    public Card getCard( int i ){
        //get the card with index i in the hand
        return hand.get(i);
    }

    public int getTotalValue(){
        //get the total value of cards in hand
        int totalValue = 0;
        int value = 0;
        for (Card i: hand){
            value = i.getValue();
            totalValue += value;
        }
        return totalValue;
    }

    public String toString(){
        //return a string telling the situation of the hand
        String s = "has cards: ";
        int value = 0;
        for (Card i: hand){
            value = i.getValue();
            s += value + ",";
        }
        return s;
    }
}