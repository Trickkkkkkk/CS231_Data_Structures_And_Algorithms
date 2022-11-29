/**
 * Di Luo
 * CS 231
 * Project 6
 * WCTester.java
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.ArrayList;

public class WCTester{
    public static void main( String[] argv ) {
        WordCounter wc = new WordCounter();
        wc.analyze("counttest.txt");
        wc.writeWordCountFile("counts_ct.txt");
        wc.readWordCountFile("counts_ct.txt");
        wc.writeWordCountFile("counts_ct_v2.txt");
    }
}