/**
 * Di Luo
 * CS 231
 * Project 9
 * HuntTheWumpus.java
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Point;

import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;
import java.util.*;

public class HuntTheWumpus {
	//fields
   	private Landscape scape;
   	private LandscapeDisplay display;

    // controls whether the simulation is playing or exiting
    private enum PlayState { PLAY, STOP }
    private PlayState state;

    //constructor 
    public HuntTheWumpus() {
		this.scape = new Landscape(5,5);
		this.display = new LandscapeDisplay(this.scape, 70);

		// Extension 2: The restart button
		JButton restart = new JButton("Restart");
		this.state = PlayState.PLAY;

		JPanel panel = new JPanel( new FlowLayout(FlowLayout.RIGHT));
		panel.add( restart );

		display.add( panel, BorderLayout.SOUTH);
		display.pack();

		Control control = new Control();
		restart.addActionListener( control );
		display.addKeyListener(control);
		display.setFocusable(true);
		display.requestFocus();

		MouseControl mc = new MouseControl();
		display.addMouseListener( mc );
		display.addMouseMotionListener( mc );
    }


    private class BasicPanel extends JPanel {
		
	/**
	 * Creates the drawing canvas
	 * @param height the height of the panel in pixels
	 * @param width the width of the panel in pixels
	 **/
	public BasicPanel(int height, int width) {
	    super();
	    this.setPreferredSize( new Dimension( width, height ) );
	    this.setBackground(Color.white);
		}	
    }

    private class MouseControl extends MouseInputAdapter {        
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}
    }

    private class Control extends KeyAdapter implements ActionListener {
    	// different keys control different actions
        public void keyTyped(KeyEvent e) {
            if (scape.getState() == false){
            	if( ("" + e.getKeyChar()).equalsIgnoreCase("w") ) {
            	    scape.update(Direction.North);
            	}
            	else if( ("" + e.getKeyChar()).equalsIgnoreCase("a") ) {
            	    scape.update(Direction.West);
            	}
            	else if( ("" + e.getKeyChar()).equalsIgnoreCase("d") ) {
            	    scape.update(Direction.East);
            	}
            	else if( ("" + e.getKeyChar()).equalsIgnoreCase("s") ) {
            	    scape.update(Direction.South);
            	}
            	else if( ("" + e.getKeyChar()).equalsIgnoreCase(" ") ) {
            	    scape.setLoaded(true);
            	}
            }
            // control the shooting action
            else if (scape.getState() == true){
            	if( ("" + e.getKeyChar()).equalsIgnoreCase("a") ) {
               		scape.update(Direction.West);
               		if(scape.shoot(Direction.West) == false){
               			// the for loop makes the wumpus move if it kills the hunter
                		for (int i = 0; i < 20; i++){
                			Random rr = new Random();
                			scape.getW().setX(scape.getW().getX()-rr.nextInt(5)+rr.nextInt(5));
                			scape.getW().setY(scape.getW().getY()-rr.nextInt(5)+rr.nextInt(5));
                			display.repaint();
	    					try{
								Thread.sleep( 150 ); 
							}
							catch(InterruptedException a){}
                		}
              		}
	            }
	            else if( ("" + e.getKeyChar()).equalsIgnoreCase("w") ) {
	                scape.update(Direction.North);
	                if(scape.shoot(Direction.North) == false){
	                	// the for loop makes the wumpus move if it kills the hunter
                		for (int i = 0; i < 20; i++){
                			Random rr = new Random();
                			scape.getW().setX(scape.getW().getX()-rr.nextInt(5)+rr.nextInt(5));
                			scape.getW().setY(scape.getW().getY()-rr.nextInt(5)+rr.nextInt(5));
                			display.repaint();
	    					try{
								Thread.sleep( 150 ); 
							}
							catch(InterruptedException a){}
                		}
              		}
	            }
	            else if( ("" + e.getKeyChar()).equalsIgnoreCase("s") ) {
	                scape.update(Direction.South);
	                if(scape.shoot(Direction.South) == false){
	                	// the for loop makes the wumpus move if it kills the hunter
                		for (int i = 0; i < 20; i++){
                			Random rr = new Random();
                			scape.getW().setX(scape.getW().getX()-rr.nextInt(5)+rr.nextInt(5));
                			scape.getW().setY(scape.getW().getY()-rr.nextInt(5)+rr.nextInt(5));
                			display.repaint();
	    					try{
								Thread.sleep( 150 ); 
							}
							catch(InterruptedException a){}
                		}
              		}
	            }
	            else if( ("" + e.getKeyChar()).equalsIgnoreCase("d") ) {
	                scape.update(Direction.East);
	                if(scape.shoot(Direction.East) == false){
	                	// the for loop makes the wumpus move if it kills the hunter
                		for (int i = 0; i < 20; i++){
                			Random rr = new Random();
                			scape.getW().setX(scape.getW().getX()-rr.nextInt(5)+rr.nextInt(5));
                			scape.getW().setY(scape.getW().getY()-rr.nextInt(5)+rr.nextInt(5));
                			display.repaint();
	    					try{
								Thread.sleep( 150 ); 
							}
							catch(InterruptedException a){}
                		}
              		}
            	}
            	//press space again to unload the hunter
            	else if( ("" + e.getKeyChar()).equalsIgnoreCase(" ") ) {
	                scape.setLoaded(false);
            	}
            }
        }

        public void actionPerformed(ActionEvent event) {
        	// extension 2: restart the game
            if( event.getActionCommand().equalsIgnoreCase("Restart") ) {
		        scape = new Landscape (5,5);
    			display = new LandscapeDisplay(scape, 70);
				
				JButton restart = new JButton("Restart");
				state = PlayState.PLAY;

				JPanel panel = new JPanel( new FlowLayout(FlowLayout.RIGHT));
				panel.add( restart );

				display.add( panel, BorderLayout.SOUTH);
				display.pack();

				Control control = new Control();
				restart.addActionListener( control );
				display.addKeyListener(control);
				display.setFocusable(true);
				display.requestFocus();

				MouseControl mc = new MouseControl();
				display.addMouseListener( mc );
				display.addMouseMotionListener( mc );
            }
        }
    }

    public void repaint() {
		this.display.repaint();
    }

    public void dispose() {
		this.display.repaint();
    }

    public static void main(String[] argv) throws InterruptedException {
		HuntTheWumpus w = new HuntTheWumpus( );
		while(w.state == PlayState.PLAY) {
			w.repaint();
			Thread.sleep(23);
		}
		w.dispose();
    }
	
}