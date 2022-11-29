/**
 * Di Luo
 * CS 231
 * Project 6
 * TestWordChecker.java
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.ArrayList;

public class TestWordChecker{
    //fields
    private BSTMap<String, Integer> bst;
    private int twc;
    private WordChecker wc;

    //constructor
    public TestWordChecker(WordChecker wc){
        bst = new BSTMap<String, Integer>(new AscendingString());
        twc = 0;
        this.wc=wc;
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
                    
                    //extension 4: English word check
                    else if(wc.check(word)==false){
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
          System.out.println("TestWordChecker.analyze():: unable to open file " + filename );
        }
        catch(IOException ex) {
          System.out.println("TestWordChecker.analyze():: error reading file " + filename);
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
            System.out.println("TestWordChecker.analyze():: error reading file " + filename);
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
          System.out.println("TestWordChecker.readWordCountFile():: unable to open file " + filename );
        }
        catch(IOException ex) {
          System.out.println("TestWordChecker.readWordCountFile():: error reading file " + filename);
        }
    }

    //test function
    public static void main( String[] argv ) {
        TestWordChecker twc = new TestWordChecker(new WordChecker());
        WordCounter wc = new WordCounter();
        
        twc.analyze("reddit_comments_2008_snippet.txt");
        wc.analyze("reddit_comments_2008_snippet.txt");

        twc.writeWordCountFile("wcFile_reddit_comments_2008_snippet.txt_AllEnglishWord.txt");
        wc.writeWordCountFile("wcFile_reddit_comments_2008_snippet.txt.txt");
    }
}

//extension 4
//check if the word is in English
class WordChecker {
    public static boolean check(String word) {
        try {
            BufferedReader in = new BufferedReader(new FileReader("words.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                if (str.indexOf(word) != -1) {
                    return true;
                }
            }
            in.close();
        } 
        catch (IOException e) {}

        return false;
    }
}