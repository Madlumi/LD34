package net.game;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener{


	public boolean[] keys = new boolean [600];
	public boolean left,right,up,down,space,enter,confirm,esc,shift,
	one,two,three,four,five,six,seven,eight,nine,zero,q,e,plus,minus,g,t,delete,qm;
	
	public void tick(){
		left = keys[KeyEvent.VK_A]||keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_D]||keys[KeyEvent.VK_RIGHT];
		
		for (int i =0; i < keys.length; i++){
			if (keys[i]);
		}
	}
	public void keyPressed(KeyEvent e) {

		keys[e.getKeyCode()] = true;
	}

	
	public void keyReleased(KeyEvent e) {
		
		keys[e.getKeyCode()] = false;
	}

	
	public void keyTyped(KeyEvent e) {
		
	}

}