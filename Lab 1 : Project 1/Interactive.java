/**
 * Di Luo
 * CS 231
 * Project 1
 * Interactive.java
 * extension
 */

public class Interactive{
    public static void main(String[] args){
        Blackjack one = new Blackjack();
        System.out.println(one.toString(one.playerTurnInteractive(),one.dealerTurn()));
    }
}