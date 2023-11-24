package net.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

public class Main extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	public static int w = 1024, h = 640, fps, ups,scale=40,t,coolD,aId,antN=0;
	public static Thread thread;
	private JFrame frame;
	public boolean running = false;
	public static boolean choice = true;
	public static String choiceL="start", choiceR="";
	public boolean deBug = false;
	public static Random R=  new Random();
	private BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	public synchronized void start (){running = true;thread = new Thread(this, "Main");thread.start();}
	public synchronized void stop(){try {thread.join();} catch (InterruptedException e) {e.printStackTrace();}}
	
	public static 	Key k = new Key();
	public static 	Mouse m = new Mouse();

	public static int gamespeed=2, mants=1000;
	
	public static Ant[] a=new Ant[mants];
	private static boolean gameover;
	
	public void run(){
		long lastTime = System.nanoTime();
		long timer =  System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		requestFocus();	
		addKeyListener(k);
		addMouseListener(m);
		addMouseMotionListener(m);
		addMouseWheelListener(m);
		Map.make();
		Screen.start();
		for(int i = 0;i<mants;i++){
			a[i]=new Ant();
		}
		while (running){
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while (delta >= 1){
				if(!gameover)tick();
				if(gameover)gamespeed=0;
				ticks ++;
				delta --;
				t++;
}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000){
				if(t>3600*5){gameover();}
				timer += 1000;
				ups = ticks;
				fps = frames;
				ticks = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	public void tick(){
		
		k.tick();
		queen.tick();
		for(int i = 0;i<mants;i++){
			a[i].tick();
		}
		
		if(choice&&coolD<=0){
			if(choiceR==""){
				if(choiceL=="start"&&t>100){t=0;}
				if(Main.k.left||Main.k.right){
					if(choiceL=="start"){queen.act=-16;choice=false;choiceL="";
					}else if(choiceL=="worker"){
					for(int i = 0;i<mants;i++){
						if(!a[i].alive){
							a[i].spawn("worker",i);
							choice=false;
							coolD=20;
							break;
						}
						
					}
					
					}
				}
				
			}else{
				if(k.right){
					if(choiceL=="worker"){
						System.err.println("SPAWN");
						for(int i = 0;i<mants;i++){
							if(!a[i].alive){
								a[i].spawn("worker",i);
								break;
							}
						}
					}
					if(choiceR=="hatchery"){
						a[aId].construct("goCH");
					}
					if(choiceR=="storage"){
						a[aId].construct("goCS");
					}else if(choiceR=="gather"){
						a[aId].gather("goCS");
					}
					choice=false;
					coolD=20;
				}else if(k.left){
					if(choiceL=="worker"){
						for(int i = 0;i<mants;i++){
							if(!a[i].alive){
								a[i].spawn("worker",i);
								break;
							}
							
						}
					}
					if(choiceL=="storage"){
						a[aId].construct("goCS");
					}
					if(choiceL=="hatchery"){
						a[aId].construct("goCH");
					}else if(choiceL=="gather"){
						a[aId].gather("goCS");
					}
					choice=false;
					coolD=20;
				}
			}
		}
		if(coolD>0){coolD--;}
		
	}
	
	public void render(){
		
		BufferStrategy bs = getBufferStrategy();
		if (bs == null){createBufferStrategy(1);return;}	
		//rendera
		for (int i = 0;i < pixels.length; i++){
			Screen.pixels[i]=0xff00ffff;
		}
		//Screen.xOff=Main.scale*4*Map.mapSize;
		Map.render();
		queen.render();
		if(gameover){
			Screen.renderTile(w/2-128, h/2-128, Sprite.skull, 1000, true, 8);
		}
		for(int i = 0;i<mants;i++){
			a[i].render();
		}
		Map.minimap();
		if(choice){
			 if(choiceR==""){
				 Screen.renderTile(w/2-32, h/2, Sprite.RLbox, 200, true,4);
				 
				 if(t%24< 8){
					 Screen.renderTile(w/2-32, h/2, Sprite.box1, 201, true,4);
				 }else if(t%24< 16){
					 Screen.renderTile(w/2-32, h/2, Sprite.box2, 201, true,4);
				 }else{
					 Screen.renderTile(w/2-32, h/2, Sprite.box3, 201, true,4);
				 }
				 
				 if(choiceL=="start"){Screen.renderTile(w/2-32, h/2, Sprite.start, 200, true,4);}
				 if(choiceL=="worker"){Screen.renderTile(w/2-32, h/2, Sprite.worker, 200, true,4);}
			 }else{
				 Screen.renderTile(w/2-130, h/2, Sprite.Lbox, 200, true,4);
				 Screen.renderTile(w/2+2, h/2, Sprite.Rbox, 200, true,4);
				 
				 if(t%24< 8){
					 Screen.renderTile(w/2-130, h/2, Sprite.box1, 201, true,4);
					 Screen.renderTile(w/2+2, h/2, Sprite.box1, 201, true,4);
				 }else if(t%24< 16){
					 Screen.renderTile(w/2-130, h/2, Sprite.box2, 201, true,4);
					 Screen.renderTile(w/2+2, h/2, Sprite.box2, 201, true,4);
				 }else{
					 Screen.renderTile(w/2-130, h/2, Sprite.box3, 201, true,4);
					 Screen.renderTile(w/2+2, h/2, Sprite.box3, 201, true,4);
				 }
				 
				 
				 if(choiceL=="gather"){Screen.renderTile(w/2-130, h/2, Sprite.gather, 201, true,4);}
				 if(choiceL=="egg"){Screen.renderTile(w/2-130, h/2, Sprite.egg, 201, true,4);}
				 
				 if(choiceL=="hatchery"){Screen.renderTile(w/2-130, h/2, Sprite.hatchery, 200, true,4);}
				 if(choiceL=="storage"){Screen.renderTile(w/2-130, h/2, Sprite.storage, 200, true,4);}

				
				 
				 
				 if(choiceR=="gather"){Screen.renderTile(w/2+2, h/2, Sprite.gather, 200, true,4);}
				 if(choiceR=="egg"){Screen.renderTile(w/2+2, h/2, Sprite.egg, 200, true,4);}
				 
				 if(choiceR=="hatchery"){Screen.renderTile(w/2+2, h/2, Sprite.hatchery, 200, true,4);}
				 if(choiceR=="storage"){Screen.renderTile(w/2+2, h/2, Sprite.storage, 200, true,4);}
			 }
			 

		}
		
		for (int i = 0;i < pixels.length; i++){
			pixels[i] = Screen.pixels[i];
			Screen.layer[i]=0;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(),null);
		//draw
		
		if(gameover){
			g.setColor(Color.black);
			g.setFont(new Font("Serif", Font.BOLD, 25));
			g.drawString("Food: "+queen.food, w/2-64, h/2-16);
			
		}
		g.dispose();
		bs.show();
		if(gameover){stop();}
	}
	
	public Main(){Dimension size = new Dimension(w,h);setPreferredSize(size);frame = new JFrame();}
	public static void main(String[] args){
		Main m = new Main();
		m.frame.setResizable(false);
		m.frame.setTitle("Ld-34");
		m.frame.add(m);
		m.frame.pack();
		m.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.frame.setLocationRelativeTo(null);
		m.frame.setVisible(true);
		m.start();
	}
	public static void gameover() {
		gameover=true;
		
	}


	

	
}