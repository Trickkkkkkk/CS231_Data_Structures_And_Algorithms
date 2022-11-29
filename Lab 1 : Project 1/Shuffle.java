/**
 * Shuffle.java
 * Di Luo
 * 2/12/2019
 */

import java.util.ArrayList;
import java.util.Random;

public class Shuffle {

    public static void main (String[] args) {
        
        ArrayList<Integer> aList = new ArrayList<Integer>();

        Random r = new Random();
        
        for (int i=0; i<10; i++) {
            aList.add(i+1);
        }

        /*for (int i=0; i<aList.size(); i++) {
            System.out.println("The " + (i+1) + "th element is " + aList.get(i));
        }
        
        for (int i=0; i<10; i++) {
            System.out.print(aList.remove(r.nextInt(10-i)) + " is removed and the remained ones are");
            for (int j=0; j<aList.size(); j++) {
                System.out.print(" " + aList.get(j));
            }
            System.out.println();
        }
        */

        for (Integer i: aList) {
            System.out.print(i + " ");
        }
        
        ArrayList<Integer> aList2 = new ArrayList<Integer>();
        for (int j=0; j<10; j++) {
            aList2.add(aList.remove(r.nextInt(aList.size())));
        }

        for (int k=0; k<aList2.size(); k++) {
            System.out.print(aList2.get(k) + " ");
        }
        System.out.println();
    }
}