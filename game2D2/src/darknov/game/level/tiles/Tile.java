package darknov.game.level.tiles;

import darknov.game.level.tiles.AnimatedTile;
import darknov.game.gfx.Colours;
import darknov.game.gfx.Screen;
import darknov.game.level.Level;

public abstract class Tile {
	
	public final static Tile[] tiles = new Tile[256];
	public static final Tile VOID = new BasicSolidTile(0,0,0,Colours.get(000, -1, -1, -1), 0xFF000000);
	public static final Tile STONE = new BasicSolidTile(1,1,0,Colours.get(-1, 333, -1, -1),0xFF555555);
	public static final Tile GRASS = new BasicTile(2,2,0,Colours.get(-1,90, 141, -1),0xFF00FF00);
	public static final Tile WATER = new AnimatedTile(3,new int[][] {{0,5},{1,5},{2,5},{1,5}},Colours.get(-1, 003, 004, -1),0xFF0000FF,900);
	public static final Tile SAND = new BasicSlowTile(4,2,0,Colours.get(-1,550, 440, -1),0xFFFFFF00);
	
	protected byte id;
	protected boolean solid;
	protected boolean emitter;
	protected boolean slow;
	private int levelColour;
	
	public Tile(int id,boolean isSolid,boolean isEmitter,boolean isSlow,int levelColour){
		this.id = (byte)id;
		if(tiles[id]!=null)throw new RuntimeException("Duplicate tile id on" + id);
		this.solid = isSolid;
		this.emitter = isEmitter;
		this.slow = isSlow;
		this.levelColour = levelColour;
		tiles[id] = this;
	}
	


	public byte getId(){
		return id;
	}
	
	public boolean isSolid(){
		return solid;
	}
	
	public boolean isEmitter(){
		return emitter;
	}
	
	public boolean isSlow(){
		return slow;
	}
	
	public int getLevelColour(){
		return levelColour;
	}
	
	public abstract void tick();
	
	public abstract void render(Screen screen,Level level,int x,int y);
}
