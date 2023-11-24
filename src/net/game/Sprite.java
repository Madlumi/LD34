package net.game;

public class Sprite {
	
	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	public static Sprite grass0 = new Sprite(8, 0, 0, SpriteSheet.tiles);	
	public static Sprite grass1 = new Sprite(8, 1, 0, SpriteSheet.tiles);	
	public static Sprite grass2 = new Sprite(8, 2, 0, SpriteSheet.tiles);	
	public static Sprite grass02 = new Sprite(8, 0, 1, SpriteSheet.tiles);	
	public static Sprite grass12 = new Sprite(8, 1, 1, SpriteSheet.tiles);	
	public static Sprite grass22 = new Sprite(8, 2, 1, SpriteSheet.tiles);	
	public static Sprite opening = new Sprite(8, 3, 0, SpriteSheet.tiles);	
	public static Sprite tunnel = new Sprite(8, 3, 1, SpriteSheet.tiles);	
	public static Sprite storageroom = new Sprite(16, 0, 1, SpriteSheet.tiles);	
	public static Sprite hatcheryroom = new Sprite(16, 1, 1, SpriteSheet.tiles);	

	public static Sprite dirt = new Sprite(8, 4, 0, SpriteSheet.tiles);	
	public static Sprite dirt2 = new Sprite(8, 5, 0, SpriteSheet.tiles);	

	public static Sprite box1 = new Sprite(32, 0, 3, SpriteSheet.ui);	
	public static Sprite box2 = new Sprite(32, 0, 4, SpriteSheet.ui);	
	public static Sprite box3 = new Sprite(32, 1, 4, SpriteSheet.ui);	

	public static Sprite Rbox = new Sprite(32, 0, 0, SpriteSheet.ui);	
	public static Sprite Lbox = new Sprite(32, 0, 1, SpriteSheet.ui);	
	public static Sprite RLbox = new Sprite(32, 0, 2, SpriteSheet.ui);	

	public static Sprite start = new Sprite(32, 1, 0, SpriteSheet.ui);	
	public static Sprite warrior = new Sprite(32, 2, 0, SpriteSheet.ui);	
	public static Sprite worker = new Sprite(32, 3, 0, SpriteSheet.ui);	

	public static Sprite gather = new Sprite(32, 1, 1, SpriteSheet.ui);	
	public static Sprite storage = new Sprite(32, 1, 2, SpriteSheet.ui);	

	public static Sprite egg = new Sprite(32, 2, 1, SpriteSheet.ui);	
	public static Sprite hatchery = new Sprite(32, 2, 2, SpriteSheet.ui);

	public static Sprite queen = new Sprite(8, 1, 0, SpriteSheet.ants);	
	public static Sprite ant = new Sprite(8, 0, 0, SpriteSheet.ants);	

	public static Sprite Lbar = new Sprite(4, 0, 0, SpriteSheet.part);	
	
	public static Sprite Ebar = new Sprite(4, 2, 0, SpriteSheet.part);	
	public static Sprite Fbar = new Sprite(4, 1, 0, SpriteSheet.part);	

	public static Sprite Rbar = new Sprite(4, 3, 0, SpriteSheet.part);	
	
	public static Sprite rain = new Sprite(4, 0, 1, SpriteSheet.part);	

	public static Sprite eggF = new Sprite(24, 3, 4, SpriteSheet.ui);	
	public static Sprite eggE = new Sprite(24, 4, 4, SpriteSheet.ui);
	public static Sprite rainE = new Sprite(24, 5, 4, SpriteSheet.ui);	
	public static Sprite rainF = new Sprite(24, 5, 5, SpriteSheet.ui);	

	public static Sprite foodF = new Sprite(24, 3, 5, SpriteSheet.ui);	
	public static Sprite foodE = new Sprite(24, 4, 5, SpriteSheet.ui);	
	public static Sprite skull = new Sprite(32, 1, 3, SpriteSheet.ui);	

	public Sprite (int size, int x, int y, SpriteSheet sheet){
	    SIZE = size;
	    pixels = new int[SIZE * SIZE];
	    this.x = x * size;
	    this.y = y * size;
	    this.sheet = sheet;
	    load();
	}
	
	public Sprite (int size, int colour){
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColour(colour);
	}
	
	private void setColour(int colour) {
		for (int i = 0; i < SIZE * SIZE; i++){
			pixels[i] = colour;
		}
		
	}

	private void load(){
		for (int y = 0;y < SIZE; y++ ) {
			for (int x = 0;x < SIZE; x++ ) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

}
