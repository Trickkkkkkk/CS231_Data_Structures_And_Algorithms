/**
 * Di Luo
 * CS 231
 * Project 8
 * FindCommonWords.java
 */

import java.util.ArrayList;
import java.util.Comparator;

public class FindCommonWords{
	public static void main(String[] args){
        //For input command line variables: if index is even, it's the filename to be analyze; if is odd, it's the output filename
		for (int i=0; i<args.length; i=i+2){
			WordCounter wc = new WordCounter(0);
			PQHeap heap = new PQHeap<KeyValuePair<String,Integer>>(new KVComparator());
			wc.analyze(args[i]);
			wc.writeWordCountFile(args[i+1]);
			ArrayList<KeyValuePair<String, Integer>> pairList = wc.getMap().entrySet();
            long a = System.currentTimeMillis();
            
            /**
             * original heap method
             */
			for (KeyValuePair<String, Integer> pair : pairList){
				heap.add(pair);
			}
			System.out.println("For " + args[i] + ": " + "\n");
			for (int j = 0; j < 10; j++){
				KeyValuePair<String, Integer> commonWord = (KeyValuePair<String, Integer>)heap.remove();
				System.out.println(commonWord + " Frequency: " + wc.getFrequency(commonWord.getKey()));
            }
            
			/**
			* extenison 3: Arraylist
			*/
			// pairList.sort(new KVComparatorArrayList());
			// for (int j = 0; j < 10; j++){
			//     System.out.println(pairList.get(j) + " Frequency: " + wc.getFrequency(pairList.get(j).getKey()));
			// }
            
            long b = System.currentTimeMillis();
            
			System.out.println("---------------------------- done with " + args[i]);
			System.out.println("Time used: " + (b-a));
		}
	}
}

//check the value associated with the string and put the string with the bigger number on the top part of the heap
class KVComparator implements Comparator<KeyValuePair<String,Integer>>{
    public int compare( KeyValuePair<String,Integer> kvp1, KeyValuePair<String,Integer> kvp2 ){
        int diff=kvp1.getValue()-kvp2.getValue();
        if (diff==0){
            return 0;
        }
        if (diff>0){
            return 1;
        }
        else{
            return -1;
        }
    }
}

// extension 3
//check the value associated with the stirng for the arraylist sort
class KVComparatorArrayList implements Comparator<KeyValuePair<String,Integer>> {
    public int compare( KeyValuePair<String,Integer> kvp1, KeyValuePair<String,Integer> kvp2 ){
       return kvp2.getValue().compareTo(kvp1.getValue());
    }
}