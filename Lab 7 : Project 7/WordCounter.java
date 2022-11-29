/**
 * Di Luo
 * CS 231
 * Project 7
 * WordCounter.java
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;

public class WordCounter{
    //fields
    private MapSet<String, Integer> ms;
    private int twc;
    private int type;

    //constructor
    //When type = 0, use Hashmap
    //When type = 1, use BSTMap 
    //When type = 2, use Hashmap2
    public WordCounter(int type){
        this.type = type;
        if (this.type==0){
            ms = new Hashmap<String, Integer>(10000);
        }
        else if (this.type==1){
            ms = new BSTMap<String, Integer>(new AscendingString());
        }
        else if (this.type==2){
            ms = new Hashmap2<String, Integer>(new AscendingString(), 10000);
        }
        twc = 0;
    }

    //functions

    //get the type
	public int getType(){
		return this.type;
    }

    //generates the word counts from a file of words
    public void analyze(String filename){
        try {
            FileReader a = new FileReader(filename);
            BufferedReader b = new BufferedReader(a);
            while(true){
                String line = b.readLine();
                if (line==null){
                    break;
                }
                String[] words = line.split("[^a-zA-Z0-9']");
                for (int i = 0; i < words.length; i++) {
                    String word = words[i].trim().toLowerCase();
                    if(word.equals("")){
                        continue;
                    }
                    else{
                        if(ms.containsKey(word)){
                            ms.put(word, ms.get(word)+1);
                            twc++;
                        }
                        else{
                            ms.put(word, 1);
                            twc++;
                        }
                    }
                }
            }
            b.close();
        }
        catch(FileNotFoundException ex) {
          System.out.println("WordCounter.analyze():: unable to open file " + filename );
        }
        catch(IOException ex) {
          System.out.println("WordCounter.analyze():: error reading file " + filename);
        }
    }

    //returns the total word count
    public int getTotalWordCount(){
        return twc;
    }

    //returns the number of unique words, which should be the size of the MapSet
    public int getUniqueWordCount(){
        return ms.size();
    }

    //returns the frequency value associated with this word
    public int getCount( String word ){
        if (ms.get(word)==null){
            return 0;
        }
        else{
            return ms.get(word);
        }
    }

    //return the number of collisions
	public int getNumColl(){
		return ms.getNumCollisions();
	}

    //returns the value associated with this word divided by the total word count
    public double getFrequency( String word ){
        double result = (double)(this.getCount(word)/this.getTotalWordCount());
        return result;
    }

    //writes the contents of the MapSet to a word count file
    public void writeWordCountFile( String filename ){
        try {
            FileWriter a = new FileWriter(filename);
            a.write("totalWordCount : "+this.getTotalWordCount()+"\n");
            for (KeyValuePair<String,Integer> i: ms.entrySet()){
                a.write(i.toString()+"\n");
            }
            a.close();
        } 
        catch(IOException ex) {
            System.out.println("WordCounter.analyze():: error reading file " + filename);
        }
    }

    //reads the contents of a word count file and reconstructs the fields of the WordCount object
    public void readWordCountFile( String filename ){
        try {
            FileReader a = new FileReader(filename);
            BufferedReader b = new BufferedReader(a);
            String line = b.readLine();
            while(true){
                line = b.readLine();
                if (line==null){
                    break;
                }
                String[] words = line.split(":");
                ms.put(words[0], Integer.parseInt(words[1]));
            }
            b.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("WordCounter.readWordCountFile():: unable to open file " + filename );
        }
        catch(IOException ex) {
            System.out.println("WordCounter.readWordCountFile():: error reading file " + filename);
        }
    }

    //return the MapSet
	public MapSet<String, Integer> getMap(){
		return this.ms;
	}

    
	//Using command line to control the input and output filename run each file five times,
    //then get the average time for processing each file
    // in args[], the entry with even index should be the filename that is going to be analysis
    //            the entry with odd index should be the output filename
    public static void main (String[] args){
		for (int i = 0; i < args.length; i = i+2){
			//use the scanner to make the analysis interactive
			Scanner kbd = new Scanner(System.in);
			System.out.println("Which analysis method do you want to use for " + args[i] + " ? (0 for Hashmap, 1 for BSTMap, 2 for Hashmap2)");
			int ty = kbd.nextInt();
			System.out.println("Start to analyze...");
			WordCounter test = new WordCounter(ty);
			ArrayList fiveTimes = new ArrayList();
			for (int j = 0; j < 5; j++){
                test.ms.clear();
                test.twc=0;
				long a = System.currentTimeMillis();
				test.analyze(args[i]);
				long b = System.currentTimeMillis();
				test.writeWordCountFile(args[i+1]);
                fiveTimes.add((b-a)/1000);
            }
            
			Collections.sort(fiveTimes);
			long sum = (long) fiveTimes.get(1) + (long) fiveTimes.get(2) + (long) fiveTimes.get(3);
            long mean = sum / 3;
            
            System.out.println("Average run time for processing: " + mean);
            
			System.out.println("The total word count for " + args[i] + " is: " + test.getTotalWordCount());
			System.out.println("The unique word count for " + args[i] + " is: " + test.getUniqueWordCount());
			if (test.getType() == 0){
				System.out.println("The number of collisions is: " + test.getNumColl());
			}
			else if (test.getType() == 1){
	    		System.out.println("The depth for the tree of " + args[i] + " is: " + test.getMap().depth());
            }
            test.ms.clear();
            test.twc=0;
		}
    }
    
    
    /*
    // Extension 5
    //use the scanner to make the analysis interactive
    public static void main (String[] args){
        Scanner kbd = new Scanner(System.in);
        System.out.println("What file do you want to analyze?");
        String name = kbd.nextLine();
        System.out.println("Which analysis method do you want to use for " + name + " ? (0 for Hashmap, 1 for BSTMap, 2 for Hashmap2)");
		int ty = kbd.nextInt();
		System.out.println("Start to analyze...");
		WordCounter test = new WordCounter(ty);
        long a = System.currentTimeMillis();
        test.analyze(name);
        long b = System.currentTimeMillis();
        System.out.println("Time for processing " + name + ": " + (b-a));
        System.out.println("The total word count for " + name + " is: " + test.getTotalWordCount());
        System.out.println("The unique word count for " + name + " is: " + test.getUniqueWordCount());
    }
	*/
}