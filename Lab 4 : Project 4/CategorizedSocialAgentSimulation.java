/**
 * Di Luo
 * CS 231
 * Project 4
 * CategorizedSocialAgentSimulation.java
 */

import java.util.Random;

public class CategorizedSocialAgentSimulation{
    //simulate the agent-based simulation with categorized socialagents.

    public static void main(String[] args) throws InterruptedException {
        System.out.println("The first command line parameter is the size of the Landscape, which should be a positive int, and the default value is 500.");
        System.out.println("The second command line parameter is the number of agents. The default value is 200.");
        Landscape scape = new Landscape(500, 500);
        Random gen = new Random();
        int count = 0;

        if (args.length>0){
            scape = new Landscape(Integer.parseInt(args[0]),Integer.parseInt(args[0]));
        }

        if (args.length>1){
            for(int i=0;i<(Integer.parseInt(args[1])/2);i++) {
                scape.addAgent( new CategorizedSocialAgent( gen.nextDouble() * scape.getWidth(),
                                 gen.nextDouble() * scape.getHeight(), 1) );
            }
            for(int i=0;i<(Integer.parseInt(args[1])/2);i++) {
                scape.addAgent( new CategorizedSocialAgent( gen.nextDouble() * scape.getWidth(),
                                 gen.nextDouble() * scape.getHeight(), 2) );
            }
        }
        else{
            for(int i=0;i<100;i++) {
                scape.addAgent( new CategorizedSocialAgent( gen.nextDouble() * scape.getWidth(),
                                 gen.nextDouble() * scape.getHeight(), 1) );
            }
            for(int i=0;i<100;i++) {
                scape.addAgent( new CategorizedSocialAgent( gen.nextDouble() * scape.getWidth(),
                                 gen.nextDouble() * scape.getHeight(), 2) );
            }
        }

        LandscapeDisplay display = new LandscapeDisplay(scape);

        while(count<200){
            scape.updateAgents();
            display.repaint();
            Thread.sleep(250);
            count++;
        }
    }
}
 