package net.game;

public class queen {

	public static int x=Map.mapSize*4,y=0,act=-10000,food=5,mfood=5,eggs,meggs=1,spawnT=300,cspwnT;
	
	public static void tick(){
		if(Main.t%Main.gamespeed==1&&Main.antN!=0){
			cspwnT+=meggs;
		}
		if(cspwnT>=spawnT){
			Main.choice=true;
			Main.choiceL="worker";
			Main.choiceR="";
		}
		
		if(act<=0&&act>-64){
			//Screen.yOff+=4;
		}
		if(act<=8&&act>0){
			x++;
		}
		if(act==9)Map.map[x/8+1*Map.mapSize]=3;
		if(act<=24&&act>16){
			y++;
		}
		if(act<=36&&act>24){
			Main.scale--;
			Screen.xOff-=Screen.xOff/Main.scale;
		}
		if(act==32)Map.map[x/8+2*Map.mapSize]=4;
		if(act<=44&&act>36){
			y++;
		}
		if(act==54)Map.map[(x+8)/8+2*Map.mapSize]=4;
		if(act<=62&&act>54){
			x++;
		}
		if(act<=84&&act>62){
			Main.scale--;
			Screen.xOff-=Screen.xOff/Main.scale;
			
		}
		if(act>84){
			act=-10000;


			//ant being born
			Main.choice=true;
			Main.choiceL="worker";
			Main.choiceR="";

		}
		
		if(act>-10000){act++;}
		
	}
	public static void render(){
		Screen.renderTile(x*Main.scale, y*Main.scale, Sprite.queen, 300, false, Main.scale);
		Screen.renderTile(100, 0, Sprite.eggF, 500, true, 3);
		Screen.renderBar(100, 0, Sprite.eggE, 501, true, 3,100-100*cspwnT/spawnT);
		
		Screen.renderTile(164, 0, Sprite.foodF, 500, true, 3);
		Screen.renderBar(164, 0, Sprite.foodE, 501, true, 3,100-100*food/mfood);
		
		Screen.renderTile(228, 0, Sprite.rainF, 500, true, 3);
		Screen.renderBar(228, 0, Sprite.rainE, 501, true, 3,(int)(100*((double)Main.t)/(300*60)));
		
	}
	
}
