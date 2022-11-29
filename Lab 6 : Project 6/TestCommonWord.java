/**
 * Di Luo
 * CS 231
 * Project 6
 * TestCommonWord.java
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.ArrayList;

public class TestCommonWord{
    //fields
    private BSTMap<String, Integer> bst;
    private int twc;
    private CommonWord cw;

    //constructor
    public TestCommonWord(CommonWord cw){
        bst = new BSTMap<String, Integer>(new AscendingString());
        twc = 0;
        this.cw = cw;
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

                    //extension 3: no common word
                    else if(cw.check(word)){
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
          System.out.println("TestCommonWord.analyze():: unable to open file " + filename );
        }
        catch(IOException ex) {
          System.out.println("TestCommonWord.analyze():: error reading file " + filename);
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
            System.out.println("TestCommonWord.analyze():: error reading file " + filename);
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
          System.out.println("TestCommonWord.readWordCountFile():: unable to open file " + filename );
        }
        catch(IOException ex) {
          System.out.println("TestCommonWord.readWordCountFile():: error reading file " + filename);
        }
    }

    //test function
    public static void main( String[] argv ) {
        TestCommonWord tcw = new TestCommonWord(new CommonWord());
        WordCounter wc = new WordCounter();
        
        tcw.analyze("reddit_comments_2008_snippet.txt");
        wc.analyze("reddit_comments_2008_snippet.txt");

        tcw.writeWordCountFile("wcFile_reddit_comments_2008_snippet.txt_NoCommonWord.txt");
        wc.writeWordCountFile("wcFile_reddit_comments_2008_snippet.txt.txt");
    }
}

//extension 3
//check if the word is common word
class CommonWord {
    public static boolean check(String word) {
        try {
            BufferedReader in = new BufferedReader(new FileReader("common.txt"));
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