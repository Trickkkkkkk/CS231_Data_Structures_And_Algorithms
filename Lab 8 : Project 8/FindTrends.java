/**
 * Di Luo
 * CS 231
 * Project 8
 * FindTrends.java
 */

import java.util.Scanner;

public class FindTrends{
	public static void main(String[] args){
        /**
		* Extension 1: scanner
		*/
        // Scanner kbd = new Scanner(System.in);
        // System.out.println("What's the filename of the first year?");
        // String f = kbd.nextLine();
		// System.out.println("What's the last year you want to analyze?");
        // String y = kbd.nextLine();
        // System.out.println("How many words do you want to analyze?");
        // int n = kbd.nextInt();
        // args = new String[n+3];
        // args[0] = f;
        // args[1] = y;
        // for (int i=0; i<n+1; i++){
        //     System.out.println("What's word "+i+" you want to analyze?");
        //     String w = kbd.nextLine();
        //     args[i+2] = w;
        // }
        // System.out.println("Running...");

		int end = Integer.parseInt(args[1]);
		String a = "";
		for ( int i = 16; i < 20; i++){
			a = a + args[0].charAt(i);
		}
		int start = Integer.parseInt(a);
		for (int j = 2; j < args.length; j++){
			String frequency = "";
			for (int k = start; k < end + 1; k++){
				WordCounter word = new WordCounter(0);
				String name = "reddit_comments_" + k + ".txt";
				word.analyze(name);
				frequency += word.getFrequency(args[j]) + " ";
			}
			System.out.println(args[j] + " " + frequency);
		}
    }
}	