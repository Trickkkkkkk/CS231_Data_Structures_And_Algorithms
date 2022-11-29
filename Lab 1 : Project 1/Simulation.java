/**
 * Di Luo
 * CS 231
 * Project 1
 * Simulation.java
 */

public class Simulation {
    public static void main(String[] args){
        Blackjack one = new Blackjack();
        int a=0;
        int playerWin=0;
        int dealerWin=0;
        int draw=0;
        while(a<1000){
            int value = one.game(false);
            if (value == -1){
            dealerWin++;}
            if (value == 1){
            playerWin++;}
            if (value == 0){
            draw++;} 
            a++;
        }
        System.out.println("The player won "+playerWin+" times, which is "+((playerWin*100)/1000)+" percent of the game,");
        System.out.println("The Dealer won "+dealerWin+" times, which is "+((dealerWin*100)/1000)+" percent of the game,");
        System.out.println("Draw happened "+draw+" times, which is "+ ((draw*100)/1000)+" percent of the game.");
    }
}