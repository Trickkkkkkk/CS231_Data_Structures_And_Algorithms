/**
 * Di Luo
 * CS 231
 * Lab 2
 * Grid.java
 */

public class Grid{
    public static void main(String[] args) {
        int yogi;
        int booboo;

        if (args.length<1){
            System.out.println("Nothing is input. Please input at least one arguments");
            return;
        }

        for (int i=0; i<args.length; i++){
            System.out.println(args[i]);
        }

        yogi = Integer.parseInt(args[0]);
        booboo = Integer.parseInt(args[1]);

        String[][] ranger = new String[yogi][booboo];

        //String[][] ranger = new String[yogi][];
        //ranger = 

        for (int i=0; i<yogi; i++){
            for (int j=0; j<booboo; j++){
                ranger[i][j] = ""+1; 
            }
        }

        for (int i=0; i<yogi; i++){
            for (int j=0; j<booboo; j++){
                System.out.print(ranger[i][j]+ " ");
            }
            System.out.println();
        }

        /*if () {
            System.exit(1);
        }*/
    }
}