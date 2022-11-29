/**
 * Di Luo
 * CS 231
 * Project 1
 * Deck.java
 */

import java.util.ArrayList;
import java.util.Random;

// Deck class creating deck objects
public class Deck {
    
    //fields
    ArrayList<Card> deck = new ArrayList<Card>();

    //constructors
    public Deck(){
        //call build() to build the arraylist as deck
        build();
    }

    //functions
    public void build(){
        /*builds a deck of 52 cards, 4 each of cards with 
        values 2-9 and 11, and 16 cards with the value 10*/
        for (int i=2; i<10; i++){
            for(int j=0; j<4; j++){
                Card card = new Card(i);
                deck.add(card);
            }
        }
        for(int i=0; i<4; i++){
            Card card = new Card(11);
            deck.add(card);
        }
        for (int i=0; i<16; i++){
            Card card = new Card(10);
            deck.add(card);
        }
    }

    public void reset()
    {
        deck.clear();
        build();
    }

    public int size(){
        //returns the number of cards in the deck
        return deck.size();
    }

    public Card deal(){
        //returns the top card and romoves it from deck
        return deck.remove(0);
    }

    public Card pick(int i){
        //returns the card at position i and removes it from the deck
        return deck.remove(i);
    }

    public void shuffle(){
        //shuffle the deck
        Random r = new Random();
        ArrayList<Card> deck2 = new ArrayList<Card>();
        for (int j=0; j<size(); j++) {
            deck2.add(deck.remove(r.nextInt(size())));
        }
        deck = deck2;
    }

    public String toString(){
        //returns a String that has the contents of the deck
        String s = "Deck has cards: ";
        int value = 0;
        for (Card i: deck){
            value = i.getValue();
            s += value + ",";
        }
        return s;
    }

    public void deck6(){
        //extension: using 6 decks together
        deck.clear();
        for (int i=0; i<6; i++){
            build();
        }
        shuffle();
    }
}