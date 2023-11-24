package net.game;


public class Map {

	public static int mapSize=100,map[] = new int[mapSize*mapSize]; 
	public static void make(){
		/*
		0= -
		1=grass
		2=dirt
		3=hole
		4=tunnel
		5=storage
		6=hatchery
		*/
		for(int x = 0;x<mapSize;x++){
			for(int y = 0;y<mapSize;y++){
				if(y>=2){
					map[(x)+y*mapSize]=2;
				}
				
			}
			map[mapSize+x]=1;
			
		}
	}
	public static void render(){
		int xx=Screen.xOff/(Main.scale*8);
		int yy=Screen.yOff/(Main.scale*8);
		for(int y = 0;y-2<		Main.h/(Main.scale*8);y++){
			for(int x = 0;x-2< 	Main.w/(Main.scale*8);x++){
				if(((x+xx)+(y+yy)*mapSize)>=0&&((x+xx)+(y+yy)*mapSize)<mapSize*mapSize){
					if(map[((x+xx))+(y+yy)*mapSize]==1&&Main.t<3600*3)	{
					if((x+xx)%3==1){		Screen.renderTile((x+xx)*8*Main.scale, (y+yy)*8*Main.scale, Sprite.grass0, 100, false, Main.scale);}
					else if((x+xx)%3==2){		Screen.renderTile((x+xx)*8*Main.scale, (y+yy)*8*Main.scale, Sprite.grass1, 100, false, Main.scale);}
					else if((x+xx)%3==0){		Screen.renderTile((x+xx)*8*Main.scale, (y+yy)*8*Main.scale, Sprite.grass2, 100, false, Main.scale);}}
					else if(map[((x+xx))+(y+yy)*mapSize]==1)	{
						if((x+xx)%3==1){		Screen.renderTile((x+xx)*8*Main.scale, (y+yy)*8*Main.scale, Sprite.grass02, 100, false, Main.scale);}
						else if((x+xx)%3==2){		Screen.renderTile((x+xx)*8*Main.scale, (y+yy)*8*Main.scale, Sprite.grass12, 100, false, Main.scale);}
						else if((x+xx)%3==0){		Screen.renderTile((x+xx)*8*Main.scale, (y+yy)*8*Main.scale, Sprite.grass22, 100, false, Main.scale);}
					}
					
					else if(map[(x+xx)+(y+yy)*mapSize]==2&&(x+y)%2==1){	Screen.renderTile((x+xx)*8*Main.scale, (y+yy)*8*Main.scale, Sprite.dirt, 100, false, Main.scale);}
					else if(map[(x+xx)+(y+yy)*mapSize]==2){	Screen.renderTile((x+xx)*8*Main.scale, (y+yy)*8*Main.scale, Sprite.dirt2, 100, false, Main.scale);}

					
					
					else if(map[(x+xx)+(y+yy)*mapSize]==3){	Screen.renderTile((x+xx)*8*Main.scale, (y+yy)*8*Main.scale, Sprite.opening, 100, false, Main.scale);}
					else if(map[(x+xx)+(y+yy)*mapSize]==4){	Screen.renderTile((x+xx)*8*Main.scale, (y+yy)*8*Main.scale, Sprite.tunnel, 100, false, Main.scale);}
					else if(map[(x+xx)+(y+yy)*mapSize]==5){	Screen.renderTile((x+xx)*8*Main.scale, (y+yy)*8*Main.scale, Sprite.storageroom, 100, false, Main.scale);}
					else if(map[(x+xx)+(y+yy)*mapSize]==6){	Screen.renderTile((x+xx)*8*Main.scale, (y+yy)*8*Main.scale, Sprite.hatcheryroom, 100, false, Main.scale);}


				}
			}
		}
	}
	public static void minimap(){
		for(int y = 0;y<100;y++){
			for(int x = 0;x<100;x++){
				if(map[x+y*mapSize]==2){Screen.pixels[x+y*Main.w]=0xff804626;Screen.layer[x+y*Main.w]=200;}
				if(map[x+y*mapSize]==4){Screen.pixels[x+y*Main.w]=0xffffffff;Screen.layer[x+y*Main.w]=200;}
			}
		}
	}
	public static void dig(int x, int y){
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
