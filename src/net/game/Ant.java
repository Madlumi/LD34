package net.game;

import java.util.Random;


public class Ant {
//cT=constructionTime
	public int x,y,type,mx,my,cT,mcT,pathFol,gx,gy,id,energy=400,mmx;
	private String action="";
	public boolean alive;
	private Random R = new Random();

	public void spawn(String typee,int i) {
		x=queen.x;
		y=queen.y;
		type=1;
		alive=true;
		id=i;
		Main.antN++;
		queen.spawnT=300*Main.antN;
		queen.cspwnT=0;
		
		energy=550;
	}
	 int pathx[]=new int [0];
	 int pathy[]=new int [0];

	private int p; 
	public void tick(){
		if(alive){
			
			
			if(action!=""&&Main.t%Main.gamespeed==1){
				energy--;
				
			}
			
			
		if((action=="constructH"||action=="constructS")&&Main.t%Main.gamespeed==1){
			if(mx>0){
				mx--;
				x+=1;
				
				
				if(Map.map[(x/8-1)+(y/8)*Map.mapSize]==2){
					Map.dig(x/8-1, y/8);
					Map.map[(x/8-1)+(y/8)*Map.mapSize]=4;
				}
			}else if(mx<0){
				mx++;
				x-=1;
					
					if(Map.map[(x/8-1)+(y/8)*Map.mapSize]==2){
						Map.dig(x/8-1, y/8);
						Map.map[(x/8-1)+(y/8)*Map.mapSize]=4;
					}
			}else if(my>0){
				my--;
				y+=1;
				
				if(Map.map[(x/8-1)+(y/8)*Map.mapSize]==2){
					Map.dig(x/8-1, y/8);
					Map.map[(x/8-1)+(y/8)*Map.mapSize]=4;
				}
			}else if(R.nextInt(3)==1){
				mx=(R.nextInt(5)-2)*8;
				my=(R.nextInt(3))*8;
			}else if(cT<=0){
				if(action=="constructS"){
					Map.map[(x/8)+(y/8)*Map.mapSize]=4;
					Map.map[(x/8-1)+(y/8-1)*Map.mapSize]=4;
					Map.map[(x/8)+(y/8-1)*Map.mapSize]=4;
					Map.map[(x/8-1)+(y/8-1)*Map.mapSize]=5;
					
					
					
					queen.mfood+=5;
					if(queen.food>queen.mfood)queen.food=queen.mfood;
					action="return";
				}else if(action=="constructH"){
					Map.map[(x/8)+(y/8)*Map.mapSize]=4;
					Map.map[(x/8-1)+(y/8-1)*Map.mapSize]=4;
					Map.map[(x/8)+(y/8-1)*Map.mapSize]=4;
					Map.map[(x/8-1)+(y/8-1)*Map.mapSize]=6;
					queen.meggs+=1;
					action="return";
				}
				
			}else{
				cT--;
			}
		}
		if(action=="return2"&&mx==0&&my==0){	
			if(pathx[pathFol]*8<x-8){mx=-8;}
			if(pathx[pathFol]*8>x-8){mx=8;}
			if(pathy[pathFol]*8<y){my=-8;}
			if(pathy[pathFol]*8>y){my=8;}
			
			
			pathFol--;
			if(pathFol<=0){
				action="";
			}
		}
		if(action=="gather"&&mx==0&&my==0&&p==-1){	
			
				if(pathx[pathFol]*8<x-8){mx=-8;}
				if(pathx[pathFol]*8>x-8){mx=8;}
				if(pathy[pathFol]*8<y){my=-8;}
				if(pathy[pathFol]*8>y){my=8;}
				
				
				pathFol--;
				if(pathFol<=0){
					action="gather2";
					p=1;
				}
		}
		if(action=="gather2"&&Main.t%Main.gamespeed==1){	
		
			
			if(mx>0){
				mx--;
				x+=1;
			}else if(mx<0){
				mx++;
				x-=1;
			}else if(my>0){
				my--;
				y+=1;
			}else if(my<0){
				my++;
				y-=1;
			}else if(mx==0&&my==0){
				if(p==1){my-=16;p=2;}
				else if(p==2){mmx=R.nextInt(200)+100;mx+=mmx;p=3;}
				else if(p==3){mx-=mmx;p=4;}
				else if(p==4){my+=16;p=5;}
				else if(p==5){action="";queen.food+=5;}
			}
					
					
					
					
		}
		if(action=="feed"&&mx==0&&my==0){	
				if(pathx[pathFol]*8<x-8){mx=-8;}
				if(pathx[pathFol]*8>x-8){mx=8;}
				if(pathy[pathFol]*8<y){my=-8;}
				if(pathy[pathFol]*8>y){my=8;}
				
				
				pathFol--;
				if(pathFol<=0){
					action="return";
					energy=500;
					queen.food-=2;
				}
		}else if(action=="feed"&&Main.t%Main.gamespeed==1){
			
				
				
				if(mx>0){
					mx--;
					x+=1;
				}else if(mx<0){
					mx++;
					x-=1;
				}else if(my>0){
					my--;
					y+=1;
				}else if(my<0){
					my++;
					y-=1;
				}
						
						
						
						
			
		}
		} if(action=="goCS"&&mx==0&&my==0){	
			if(pathx[pathFol]*8<x-8){mx=-8;}
			if(pathx[pathFol]*8>x-8){mx=8;}
			if(pathy[pathFol]*8<y){my=-8;}
			if(pathy[pathFol]*8>y){my=8;}
			pathFol--;
			if(pathFol<=0){
				goCS();
				
			}
		}
	if(action=="goCH"&&mx==0&&my==0){	
		if(pathx[pathFol]*8<x-8){mx=-8;}
		if(pathx[pathFol]*8>x-8){mx=8;}
		if(pathy[pathFol]*8<y){my=-8;}
		if(pathy[pathFol]*8>y){my=8;}
		pathFol--;
		if(pathFol<=0){
			goCH();
			
		}
	}
		if((action=="return2"||action=="goCS"||action=="goCH")&&Main.t%Main.gamespeed==1){
			if(mx>0){
				mx--;
				x+=1;
			}else if(mx<0){
				mx++;
				x-=1;
			}
			if(my>0){
				my--;
				y+=1;
			}else if(my<0){
				my++;
				y-=1;
			}
		}
		if(queen.food<=0){
			Main.gameover();
		}
		
		
		if(action==""&&!Main.choice&&type==1){
			
			
			if(energy<=10){
				action="feed";
				if(queen.mfood>5){
				while(true){
					
					
				gx=R.nextInt(Map.mapSize);
				gy=R.nextInt(Map.mapSize);
				if(Map.map[gx+gy*Map.mapSize]==5){break;}
				}
				}else{
					gx=queen.x/8;
					gy=queen.y/8;
				}
				path(gx, gy);
			}else{
			
			
			Main.aId=id;
			Main.choiceR="hatchery";
			if(queen.food<queen.mfood)Main.choiceL="gather";else Main.choiceL="storage";
			
			Main.choice=true;
			}
		}
		
		if(action=="return"){
			path(queen.x/8,queen.y/8);
			action="return2";
		}
		
	}
	
	
	
	
	private void goCS() {
		mcT=20;
		cT=20;
		//mx=(R.nextInt(9)-4)*8;
		
		//my=R.nextInt(5)*8+8;
		action="constructS";
		
	}
	private void goCH() {
		mcT=20;
		cT=20;
		//mx=(R.nextInt(9)-4)*8;
		
		//my=R.nextInt(5)*8+8;
		action="constructH";
		
	}

	private void path(int goalx,int goaly) {
		int path[] = new int[Map.map.length];
		int ider;
		 pathx=new int [0];
		 pathy=new int [0];
		ider=1;
		int qx=goalx;
		int qy=goaly;
		for(int i = 0; i< path.length;i++){
			path[i]=0;
		}
		path[x/8+(y/8)*Map.mapSize]=1;
		while (true){
			ider++;
			
			for(int i = Map.mapSize; i< path.length-Map.mapSize;i++){
				
				if(path[i]==ider-1){
					if(Map.map[i+1]>3&&path[i+1]==0){path[i+1]=ider;}
					if(Map.map[i-1]>3&&path[i-1]==0){path[i-1]=ider;}
					if(Map.map[i+Map.mapSize]>3&&path[i+Map.mapSize]==0){path[i+Map.mapSize]=ider;}
					if(Map.map[i-Map.mapSize]>3&&path[i-Map.mapSize]==0){path[i-Map.mapSize]=ider;}
					
					
					
					
				}
				if(path[qx+qy*Map.mapSize]>0){
					break;
				}
			}
			if(path[qx+qy*Map.mapSize]>0){
				break;
			}
		}
		pathx=new int[ider];
		pathy=new int[ider];
		pathx[0]=qx;
		pathy[0]=qy;
		int max;
		max=path[pathx[0]+pathy[0]*Map.mapSize];
	
		for(int i = 1; i< ider;i++){
			
			//if(pathx[i-1]==0)break;
			if(path[(pathx[i-1]+1)+(pathy[i-1])*Map.mapSize]<max&&path[(pathx[i-1]+1)+(pathy[i-1])*Map.mapSize]>0){
				max=path[(pathx[i-1]+1)+(pathy[i-1])*Map.mapSize];
				

				
			}
			if(path[(pathx[i-1]-1)+(pathy[i-1])*Map.mapSize]<max&&path[(pathx[i-1]-1)+(pathy[i-1])*Map.mapSize]>0){
				max=path[(pathx[i-1]-1)+(pathy[i-1])*Map.mapSize];
				
		
			}
			if(path[(pathx[i-1])+(pathy[i-1]+1)*Map.mapSize]<max&&path[(pathx[i-1])+(pathy[i-1]+1)*Map.mapSize]>0){
				max=path[(pathx[i-1])+(pathy[i-1]+1)*Map.mapSize];
				
				
			}
			if(path[(pathx[i-1])+(pathy[i-1]-1)*Map.mapSize]<max&&path[(pathx[i-1])+(pathy[i-1]-1)*Map.mapSize]>0){
				max=path[(pathx[i-1])+(pathy[i-1]-1)*Map.mapSize];

			}
			
			
			if(path[(pathx[i-1]+1)+(pathy[i-1])*Map.mapSize]==max){
				
				pathx[i]=pathx[i-1]+1;		pathy[i]=pathy[i-1];
			}else if(path[(pathx[i-1]-1)+(pathy[i-1])*Map.mapSize]==max){
				pathx[i]=pathx[i-1]-1;		pathy[i]=pathy[i-1];
			}else if(path[(pathx[i-1])+(pathy[i-1]+1)*Map.mapSize]==max){
				pathx[i]=pathx[i-1];		pathy[i]=pathy[i-1]+1;
			}else if(path[(pathx[i-1])+(pathy[i-1]-1)*Map.mapSize]==max){
				pathx[i]=pathx[i-1];		pathy[i]=pathy[i-1]-1;
			}else{
				pathx[i]=pathx[i-1];		pathy[i]=pathy[i-1];
				
			}
			pathFol=ider-1;
		}
		
		
	}

	
	
	public void construct(String typ){
		//Main.scale-=20;
		action=typ;
		for(int i = 0;i<5;i++){
		while(true){
			gx=R.nextInt(Map.mapSize);
			gy=R.nextInt(Map.mapSize);
			if(Map.map[gx+gy*Map.mapSize]>3){
				if(	Map.map[gx+1+gy*Map.mapSize]<=3||
					Map.map[gx-1+gy*Map.mapSize]<=3||
					Map.map[gx+(gy+1)*Map.mapSize]<=3||
					Map.map[gx+(gy-1)*Map.mapSize]<=3){
					break;
				}
			}
			
		}
		path(gx, gy);
		}
		
		
	}
	public void render(){
		if(alive){
		
		Screen.renderTile((x-8)*Main.scale, y*Main.scale, Sprite.ant, 200, false, Main.scale);
		if(cT>0){
			Screen.renderTile(x*Main.scale-12*4, y*Main.scale, Sprite.Lbar, 201, false, 4);
			Screen.renderTile(x*Main.scale-8*4, y*Main.scale, Sprite.Ebar, 201, false, 4);
			Screen.renderTile(x*Main.scale-4*4, y*Main.scale, Sprite.Ebar, 201, false, 4);
			Screen.renderTile(x*Main.scale-0*4, y*Main.scale, Sprite.Ebar, 201, false, 4);
			Screen.renderTile(x*Main.scale+4*4, y*Main.scale, Sprite.Ebar, 201, false, 4);
			Screen.renderTile(x*Main.scale+8*4, y*Main.scale, Sprite.Rbar, 201, false, 4);
			
			for(int i = 16;i>16*((double)cT/(double)mcT);i--){
				Screen.renderTile(x*Main.scale-(8+i-16)*4, y*Main.scale, Sprite.Fbar, 202, false, 4);

			}

		}
		}
	}
	public void gather(String string) {
		
		p=1;
		action="gather2";
		if(x/8!=queen.x/8-1&&y/8!=queen.y/8){
			path(queen.x/8-1,queen.y/8);
			
			p=-1;
			action="gather";
		}
		
		
	}

}
