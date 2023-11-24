package net.game;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Mouse implements MouseListener,MouseMotionListener,MouseWheelListener{
	public static int x,y,b;

	public int getx(){
		
		return x;
		
	}
	public int gety(){
		
		return y;
		
	}
	public int getb(){
		
		return b;
		
	}
	
	public void mouseClicked(MouseEvent e) {
		//Main.mbutt();
		b=e.getButton();

		x=e.getX();
		y=e.getY();
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		b=e.getButton();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		b=e.getButton();
		x=e.getX();
		y=e.getY();
		//Build.clicked=false;
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		x=e.getX();
		y=e.getY();		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		x=e.getX();
		y=e.getY();		
		b=e.getButton();

	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(Main.scale>2&&Main.scale<40&&e.getWheelRotation()>0){
			//Main.scale--
			//;
			Screen.yOff+=32;
			//Screen.xOff-=Screen.xOff/Main.scale;
		}else if(Main.scale<39&&e.getWheelRotation()<0){
			//Main.scale++;
			Screen.yOff-=32;
			//Screen.xOff=Screen.xOff*Main.scale;
		}
	}



}