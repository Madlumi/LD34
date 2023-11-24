package net.game;



public class Screen {

	public static int width = Main.w, height =Main.h;
	
	public static int[] pixels,layer;
	public static final int MAP_SIZE = 64;
	public static final int MAP_SIZE_MASK  = MAP_SIZE -1;

	

	public static int xOff=Main.scale*4*Map.mapSize, yOff=0;
		
	public static void start(){
		layer  = new int[width * height];
		pixels  = new int[width * height];
		
	}
	
	public static void clear(int c){
		for (int i = 0;i<pixels.length;i++){
			pixels[i]=c;
		}
	}
	public static void renderTile(int xp, int yp, Sprite sprite,int z, boolean stat,int scale){
		if(!stat){
			xp-=xOff;
			yp-=yOff;
			
		}
		
		for (int y = 0; y < sprite.SIZE*scale; y++) {
			for (int x = 0; x < sprite.SIZE*scale; x++) {

				if((xp+x)+(y+yp)*width>=0&&x+xp+(y+yp)*width<pixels.length&&x+xp<width&&x+xp>=0){
					
					if(sprite.pixels[x/scale + y/scale * sprite.SIZE]!=0xffff00ff){		
						
						if(layer[x+xp+(y+yp)*width]<z){	pixels[x+xp+(y+yp)*width] = sprite.pixels[x/scale + y/scale * sprite.SIZE];
							layer[x+xp+(y+yp)*width]=z;
						}
						
					}
				}
			}
		}
	}
	public static void renderBar(int xp, int yp, Sprite sprite,int z, boolean stat,int scale,int perc){
		if(!stat){
			xp-=xOff;
			yp-=yOff;
			
		}
		if(perc<0)perc=0;
		if(perc>100)perc=100;
		int h= (int)((double)perc/100*sprite.SIZE*scale);
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < sprite.SIZE*scale; x++) {

				if((xp+x)+(y+yp)*width>=0&&x+xp+(y+yp)*width<pixels.length&&x+xp<width&&x+xp>=0){
					
					if(sprite.pixels[x/scale + y/scale * sprite.SIZE]!=0xffff00ff){		
						
						if(layer[x+xp+(y+yp)*width]<z){	pixels[x+xp+(y+yp)*width] = sprite.pixels[x/scale + y/scale * sprite.SIZE];
							layer[x+xp+(y+yp)*width]=z;
						}
						
					}
				}
			}
		}
	}
}









