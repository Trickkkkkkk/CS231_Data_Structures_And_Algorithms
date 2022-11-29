/**
 * Di Luo
 * CS 231
 * Project 6
 * WordCounter.java
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.ArrayList;

public class WordCounter{
    //fields
    private BSTMap<String, Integer> bst;
    private int twc;

    //constructor
    public WordCounter(){
        bst = new BSTMap<String, Integer>(new AscendingString());
        twc = 0;
    }

    //functions
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
                        if(bst.containsKey(word)){
                            bst.put(word, bst.get(word)+1);
                            twc++;
                        }
                        else{
                            bst.put(word, 1);
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

    //returns the number of unique words, which should be the size of the BSTMap
    public int getUniqueWordCount(){
        return bst.size();
    }

    //returns the frequency value associated with this word
    public int getCount( String word ){
        if (bst.get(word)==null){
            return 0;
        }
        else{
            return bst.get(word);
        }
    }

    //returns the value associated with this word divided by the total word count
    public double getFrequency( String word ){
        double result = (double)(this.getCount(word)/this.getTotalWordCount());
        return result;
    }

    //writes the contents of the BSTMap to a word count file
    public void writeWordCountFile( String filename ){
        try {
            FileWriter a = new FileWriter(filename);
            a.write("totalWordCount : "+this.getTotalWordCount()+"\n");
            for (KeyValuePair<String,Integer> i: bst.entrySet()){
                a.write(i.toString()+"\n");
            }
            a.close();
        } 
        catch(IOException ex) {
            System.out.println("WordCounter.analyze():: error reading file " + filename);
        }
    }

    //extension 6
    //writes the contents of the BSTMap with value 1 to a word count file
    public void writeWordCountFileOnly1( String filename ){
        try {
            FileWriter a = new FileWriter(filename);
            a.write("totalWordCount : "+this.getTotalWordCount()+"\n");
            for (KeyValuePair<String,Integer> i: bst.entrySet()){
                if (i.getValue()==1){
                    a.write(i.toString()+"\n");
                }
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
                bst.put(words[0], Integer.parseInt(words[1]));
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

    //extension 5
    //return a string showing the most frequent word in the BSTMap
    public String returnTop( String filename ){
        String s = "The most frequent word is "; 
        String key ="";
        int maxValue = 0;
        for (KeyValuePair<String,Integer> i: bst.entrySet()){
            if (i.getValue()>maxValue){
                maxValue = i.getValue();
                key=i.getKey();
            }
        }
        s+=key+", and the count is "+maxValue+".";
        return s;
    }

    //test function
    public static void main( String[] argv ) {
        if (argv.length<1){
            System.out.println("Please input the filename.");
        }
        WordCounter wc = new WordCounter();
        int count = 0;
        long startTime = 0;
        long endTime = 0;
        long time = 0;
        for (String i: argv){
            startTime=System.currentTimeMillis();
            wc.analyze(i);
            endTime=System.currentTimeMillis();
            time = endTime-startTime;
            System.out.println("Time to process "+argv[count]+" is "+time+" milliseconds.");
            System.out.println("The file "+argv[count]+" has "+wc.getUniqueWordCount()+" unique words.");
            wc.writeWordCountFile("wcFile_"+argv[count]+".txt");

            //extension 5: most frequent value
            //System.out.println(wc.returnTop("wcFile_"+argv[count]+".txt"));

            //extension 6
            //wc.writeWordCountFileOnly1("wcFile_"+argv[count]+"_one"+".txt");
            //wc.bst.clear();
            //wc.readWordCountFile("wcFile_"+argv[count]+"_one"+".txt");
            //System.out.println("The file "+argv[count]+" has "+wc.getUniqueWordCount()+" unique words that only appear once.");

            //System.out.println(wc.bst.toString());// to test counttest.test and the toString in BTSMap
            wc.bst.clear();
            wc.twc=0;
            count++;
        }
    }
}