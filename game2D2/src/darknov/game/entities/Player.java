package darknov.game.entities;

import java.util.Random;

import darknov.game.gfx.Font;
import darknov.game.InputHandler;
import darknov.game.gfx.Colours;
import darknov.game.gfx.Screen;
import darknov.game.inventory.Axe;
import darknov.game.inventory.Bow;
import darknov.game.inventory.Inventory;
import darknov.game.inventory.Sword;
import darknov.game.level.Level;

public class Player extends Mob{

	private InputHandler input;
	private int colour = Colours.get(-1,000,411,543);
	private int scale = 1;
	private long hpMillis = System.currentTimeMillis();
	private long randomEqMillis = System.currentTimeMillis();
	private Inventory inventory;
	protected boolean isSwimming = false;
	private int tickCount = 0;
	private int randomIntNumber = 0;
	private String username;

	public Player(Level level, int x, int y, InputHandler input,Inventory inventory,String username) {
		super(level, "Player", x, y, 1, 15,10);
		this.input = input;
		this.inventory = inventory;
		this.username = username;
	}
	
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public void tick() {
		int xa = 0;
		int ya = 0;
		if(input != null){
			if (input.up.isPressed()) {
				ya--;
			}
			if (input.down.isPressed()) {
				ya++;
			}
			if (input.left.isPressed()) {
				xa--;
			}
			if (input.right.isPressed()) {
				xa++;
			}
		}
		if (input.x.isPressed() && System.currentTimeMillis() - hpMillis > 1000){
			hasBeenDamaged(1);
			hpMillis = System.currentTimeMillis();
		}
		
		if (input.x.isPressed() && System.currentTimeMillis() - randomEqMillis > 1000){
			randomItem();
			randomEqMillis = System.currentTimeMillis();
		}
		
	
		if(xa != 0 || ya != 0){
			move(xa,ya);
			isMoving = true;
		}
		else{
			isMoving = false;
		}

		if(level.getTile(this.x >> 3, this.y >> 3).getId() == 3){
			isSwimming = true;
		}
		
		if(isSwimming && level.getTile(this.x >> 3, this.y >> 3).getId() != 3){
			isSwimming = false;
		}
		tickCount++;
		
	}

	public void render(Screen screen) {
		int xTile = 0;
		int yTile = 28;
		int walkingSpeed = 3;
		int flipTop = (numSteps >> walkingSpeed) & 1;
		int flipBottom = (numSteps >> walkingSpeed) & 1;

		if(movingDir == 1){
			xTile +=2;
			
		}else if(movingDir > 1){
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
		}
		
		int modifier = 8 * scale;
		int xOffset = x - modifier/2;
		int yOffset = y - modifier/2 - 4;
		
		if(isSwimming){
			int waterColour = 0;
			yOffset += 4;
			if(tickCount % 60  < 15){
				
				waterColour = Colours.get(-1, -1, 225,-1);
			} else if(15 <= tickCount % 60 && tickCount % 60 < 30){
				yOffset -=1;
				waterColour = Colours.get(-1, 225, 115, -1);
			} else if(30 <= tickCount % 60 && tickCount % 60 < 45){
				waterColour = Colours.get(-1, 115, -1, 225);
			} else{
				yOffset -=1;
				waterColour = Colours.get(-1, 225, 115, -1);
			}
			
			screen.render(xOffset, yOffset + 3, 0 + 27 * 32, waterColour, 0x00, 1);
			screen.render(xOffset + 8, yOffset + 3, 0 + 27 * 32, waterColour, 0x01, 1);
		}
		
		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, colour,flipTop,scale);
		screen.render(xOffset+ modifier - (modifier * flipTop), yOffset, xTile + 1 + yTile * 32, colour,flipTop,scale);
		if(!isSwimming){
		screen.render(xOffset  + (modifier * flipBottom), yOffset + modifier, xTile + (yTile + 1) * 32, colour,flipBottom,scale);
		screen.render(xOffset+ modifier- (modifier * flipBottom), yOffset + modifier, xTile + 1 + (yTile + 1) * 32, colour,flipBottom,scale);
		}
		if(username != null){
			darknov.game.gfx.Font.render(username, screen, xOffset - ((username.length() - 1) / 2 * 8), yOffset - 10, Colours.get(-1, -1, -1, 555), 1);
		}
	}
	

	private void randomItem(){
		int numberX = 0;
		Random generator = new Random();
		int maxColour = 555;//----------------Mo¿e byæ max 555!colourX,colourY,colourZ------------------//
		int colourX = generator.nextInt(maxColour)+1;
		int colourY = generator.nextInt(maxColour)+1;
		int colourZ = generator.nextInt(maxColour)+1;
		if(colourX==333)colourX+=200;
		if(colourY==333)colourY+=200;
		if(colourZ==333)colourZ+=200;
		System.out.println(colourX + ", " + colourY + ", " + colourZ);
		numberX = numberX + colourX + colourY * 1000 + colourZ*1000000;
		inventory.addItem(new Bow(numberX, "Siekacz Zmys³ów", "Sieka³ zmys³y...", 1));
		inventory.addItem(new Axe(numberX, "Siekacz Zmys³ów", "Sieka³ zmys³y...", 1));
		inventory.addItem(new Sword(numberX, "Siekacz Zmys³ów", "Sieka³ zmys³y...", 1));
		randomIntNumber++;		
	}
	
	public boolean hasCollided(int xa, int ya) {
		int xMin = 0 ;//* scale;
		int xMax = 7 ;//* scale;
		int yMin = 3 ;//* scale;
		int yMax = 7 ;//* scale;
		
		for(int x = xMin; x < xMax;x++){
			if(isSolidTile(xa,ya,x,yMin)){
				return true;
			}
		}
		for(int x = xMin; x < xMax;x++){
			if(isSolidTile(xa,ya,x,yMax)){
				return true;
			}
		}
		for(int y = yMin; y < yMax;y++){
			if(isSolidTile(xa,ya,xMin,y)){
				return true;
			}
		}
		for(int y = yMin; y < yMax;y++){
			if(isSolidTile(xa,ya,xMax,y)){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasBeenSlowed(int xa, int ya) {
		int xMin = 0 ;//* scale;
		int xMax = 6 ;//* scale;
		int yMin = 3 ;//* scale;
		int yMax = 6 ;//* scale;
		
		for(int x = xMin; x < xMax;x++){
			if(isSlowTile(xa,ya,x,yMin)){
				return true;
			}
		}
		for(int x = xMin; x < xMax;x++){
			if(isSlowTile(xa,ya,x,yMax)){
				return true;
			}
		}
		for(int y = yMin; y < yMax;y++){
			if(isSlowTile(xa,ya,xMin,y)){
				return true;
			}
		}
		for(int y = yMin; y < yMax;y++){
			if(isSlowTile(xa,ya,xMax,y)){
				return true;
			}
		}
		
		
		return false;
	}
	
	public void hasBeenDamaged(int damage){
		hp = hp - damage;
	}

	public int actualHp() {
		return hp;
	}

}
