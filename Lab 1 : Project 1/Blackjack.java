/**
 * Di Luo
 * CS 231
 * Project 1
 * Blackjack.java
 */

import java.util.ArrayList;
import java.util.Scanner;

// Blackjack class that implements the card game
public class Blackjack {

    //fields
    private Deck deck = new Deck();
    private Hand player = new Hand();
    private Hand dealer = new Hand();
    private int result;
    private String string;
    private Scanner scan= new Scanner(System.in);

    //constructor
    public Blackjack(){
        reset(true);
    }

    public void reset (boolean newDeck){
        //set up and reset the game
        if (newDeck == true){
            //for extension 2: change the next two line to deck.deck6()
            deck.reset();
            deck.shuffle();
            //deck.deck6();
        }
        player.reset();
        dealer.reset();
    }

    public void deal(){
        //deal out two cards to both players
        for (int i=0; i<2; i++){
            player.add(deck.deal());
            dealer.add(deck.deal());
        }
    }

    public String toString(boolean playerCondition, boolean dealerCondition){
        //return string stating the result of the game
        string="The player now has " + player.getTotalValue() + " point,";
        string+="The dealer now has " + dealer.getTotalValue() + " point.";
        if (playerCondition == true && dealerCondition == false)
        string+=" The player won.";
        if (dealerCondition == true && playerCondition == false)
        string+=" The dealer won.";
        if ((dealerCondition == true && playerCondition == true) && (player.getTotalValue()>dealer.getTotalValue()))
        string+=" The player won.";
        if ((dealerCondition == true && playerCondition == true)&&(player.getTotalValue()<dealer.getTotalValue()))
        string+=" The dealer won.";
        if ((dealerCondition == true && playerCondition == true)&&(player.getTotalValue() == dealer.getTotalValue()))
        string+=" Draw game.";
        if (dealerCondition == false && playerCondition == false)
        string+=" The dealer win since the player busted first.";
        return string;
    }

    public boolean playerTurn(){
        /*have the player draw cards until the total value of 
        the player's hand is equal to or above 16. The function 
        should return false if the player goes over 21 (bust)*/
        while (player.getTotalValue()<16){
            player.add(deck.deal());
        }
        if (player.getTotalValue()>21){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean dealerTurn(){
        /*have the dealer draw cards until the total of the 
        dealer's hand is equal to or above 17. The function 
        should return false if the dealer goes over 21.*/
        while (dealer.getTotalValue()<17){
            dealer.add(deck.deal());
        }
        if (dealer.getTotalValue()>21){
            return false;
        }
        else{
            return true;
        }
    }

    public int gameResult(boolean playerCondition, boolean dealerCondition){
        //returns a -1 if the dealer wins, 0 in case of a push (tie), and a 1 if the player wins
        if (playerCondition == true && dealerCondition == false)
        result=1;
        if (dealerCondition == true && playerCondition == false)
        result=-1;
        if ((dealerCondition == true && playerCondition == true)&&(player.getTotalValue()>dealer.getTotalValue()))
        result=1;
        if ((dealerCondition == true && playerCondition == true)&&(player.getTotalValue()<dealer.getTotalValue()))
        result=-1;
        if ((dealerCondition == true && playerCondition == true)&&(player.getTotalValue()==dealer.getTotalValue()))
        result=0;
        if (dealerCondition == false && playerCondition == false)
        result=-1;
        return result;
    }

    public int game(boolean verbose){
        /*play a single game of Blackjack. Return a -1 if the dealer 
        wins, 0 in case of a push (tie), and a 1 if the player wins*/
        deal();
        if (verbose == true){
            System.out.println("The player " + player.toString() + " before.");
            System.out.println("The dealer " + dealer.toString() + " before.");
        }
        boolean playerResult = playerTurn();
        boolean dealerResult = dealerTurn();
        int a = gameResult(playerResult,dealerResult);
        if(verbose == true){
            System.out.println("The player " + player.toString() + " after.");
            System.out.println("The dealer " + dealer.toString() + " after.");
            System.out.println(toString(playerResult,dealerResult));
        }
        //for extension 2: change 26 to 52
        if(deck.size()<26){
            reset(true);
        }
        else{
            reset(false);
        }
        return a;        
    }

    //extension
    public boolean playerTurnInteractive(){
        boolean playerTurn=true;
        boolean result=false;
        while (playerTurn==true){
            if(player.getTotalValue()>21){
               System.out.println("You have busted, please wait for the dealer to complete his turn.");
               playerTurn=false;
               result=false;
            }
            else{
                System.out.println("Now you have total of "+player.getTotalValue()+" points");
                System.out.println("What do you want to do? If you want to draw another card please enter Y, if you want to stay please enter N." );
                String a = scan.next();
                if (a.equals("Y")){
                    player.add(deck.deal());}
                else if (a.equals("N")){
                    System.out.println("You've decided to stay. Please wait for dealer to complete his turn.");
                    playerTurn=false;
                    result=true;
                }
                else if (!a.equals("Y") && !a.equals("N"))
                System.out.println("Invalid Statement. Start over please.");
            }
        }
        return result;
    }
    
    //Main function that runs the game three times
    public static void main(String[] args){
        Blackjack one=new Blackjack();
        for (int i=0; i<3; i++){
            System.out.println(one.game(true));
        }
    }
}