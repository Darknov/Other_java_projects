package darknov.game.entities;

import darknov.game.gfx.Font;
import darknov.game.level.Level;
import darknov.game.level.tiles.Tile;

public abstract class Mob extends Entity{

	protected String name;
	protected int speed;
	protected int numSteps = 0;
	protected boolean isMoving;
	protected int movingDir = 1;
	protected int scale = 1;
	protected boolean isSlowed;
	protected int slowNumber = 0;

	
	protected int hp;
	protected int shield;
	protected Font fontHp;
	
	
	public Mob(Level level, String name, int x, int y, int speed,int hp,int shield) {
		super(level);
		this.name = name;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.hp = hp;
		this.shield = shield;
	}
	
	public void move(int xa,int ya){
		
		ifIsSlowed(xa,ya);
		
		if(xa != 0 && ya != 0 && isSlowed == false){

			move(xa,0);
			move(0,ya);
			numSteps--;
			return;
		}
		numSteps++;
		

		if(!hasCollided(xa,ya)){
			if(ya < 0)
				movingDir = 0;
			if(ya > 0)
				movingDir = 1;
			if(xa < 0)
				movingDir = 2;
			if(xa > 0)
				movingDir = 3;
			
			if(!isSlowed){
				x += xa*speed;
				y += ya*speed;
			}
			else{
				movingSlow(xa,ya);
			}
		}
	}
	
	public abstract boolean hasCollided(int xa, int ya);
	
	public abstract boolean hasBeenSlowed(int xa, int ya);
	
	protected boolean isSolidTile(int xa,int ya,int x,int y){
		if(level == null){ 
			return false;
		}
		Tile lastTile = level.getTile((this.x + x) >> 3, (this.y + y) >> 3);
		Tile newTile = level.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
		if(!lastTile.equals(newTile) && newTile.isSolid()){
			return true;
		}
		return false;
	}
	
	protected void ifIsSlowed(int xa,int ya){
		isSlowed = false;
		if(hasBeenSlowed(xa,ya)){
			isSlowed = true;
		}
	}
	
	protected boolean isSlowTile(int xa,int ya,int x,int y){
		Tile newTile = level.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
		if(newTile.isSlow()){
			return true;
		}
		return false;
	}
	
	protected void movingSlow(int xa,int ya){
		if(slowNumber > 0){
			slowNumber = 0;
			x += xa*speed;
			y += ya*speed;
		}
		else{
			slowNumber++;
		}
	}
	
	public String getName(){
		return name;
	}
}
