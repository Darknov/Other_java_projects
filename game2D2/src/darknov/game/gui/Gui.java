package darknov.game.gui;

import darknov.game.InputHandler;
import darknov.game.entities.Player;
import darknov.game.gfx.Colours;
import darknov.game.gfx.Screen;
import darknov.game.inventory.EachSpace;
import darknov.game.inventory.Inventory;
import darknov.game.level.Level;

public class Gui {
	private InputHandler input;
	public int x,y;
	protected Level level;
	private int tickCount = 0;
	private long eqMillis = System.currentTimeMillis();
	private int scale = 1;
	protected boolean isInventoryOpen = false;
	protected Player player;
	protected Inventory inventory;
	private Inventory equipment;
	protected boolean isCharacterOpen = false;
	
	public Gui(Level level,Player player,Inventory inventory,InputHandler input){
		init(level);
		this.player = player;
		this.inventory = player.getInventory();
		this.input = input;
	}
	
	public final void init(Level level){
		this.level = level;
	}

	public void tick() {
		
		if(input.i.isPressed() && isInventoryOpen == false &&  System.currentTimeMillis() - eqMillis > 1000){
			isInventoryOpen = true;
			eqMillis = eqMillis = System.currentTimeMillis();
		}
		if(input.i.isPressed() && isInventoryOpen == true && System.currentTimeMillis() - eqMillis > 1000){
			isInventoryOpen = false;
			eqMillis = eqMillis = System.currentTimeMillis();
		}
		
		
		tickCount++;
	}
	
	public void render(Screen screen) {
		showHp(screen);
		if(isInventoryOpen){
			showInventory(screen,inventory.getMaxSpace(),inventory.getAllSpace());
		}
		
		if(!isInventoryOpen){}
		
		if(isCharacterOpen){
			
		}
		
		if(!isCharacterOpen){}
	}
	
	private void showHp(Screen screen){
		for(int i = 0; i < player.actualHp();i++)
		screen.render(screen.xOffset + i*8, screen.yOffset, 0 + 2 * 32,Colours.get(-1,000,300,500), 0x00, scale);
		
	}
	
	private void showInventory(Screen screen,int max,EachSpace[] eachSpace) {
		int border = 0;
		int column = 0;
		for(int i = 0; i < 42;i++){
			if(i % 6 == 0) column = 0;
			if(i > 0 && i % 6 == 0) border++;
			if(column<5 && border < 6)
				screen.render(screen.xOffset + column * 8 + screen.width - 60, screen.yOffset + border*8,0 + 3*32,Colours.get(-1,0,0,0), 0x00, scale);
			else if(column == 5 && border < 6)
				screen.render(screen.xOffset + column * 8 + screen.width - 60, screen.yOffset + border*8,1 + 3*32,Colours.get(-1,0,0,0), 0x00, scale);
			else if(border == 6 && column != 5)
				screen.render(screen.xOffset + column * 8 + screen.width - 60, screen.yOffset + border*8,2 + 3*32,Colours.get(-1,0,0,0), 0x00, scale);
			else
				screen.render(screen.xOffset + column * 8 + screen.width - 60, screen.yOffset + border*8,3 + 3*32,Colours.get(-1,0,0,0), 0x00, scale);
			column++;
		}
		column = 0;
		border = 0;
		for(int i = 0; i < max;i++){
			if(i % 5 == 0) column = 0;
			if(i > 0 && i % 5 == 0) border++;
			screen.render(screen.xOffset + column * 8 + column + screen.width - 60 + 1, screen.yOffset + border*8 + border + 1,1 + 2*32,Colours.get(-1,678,678,678), 0x00, scale);
			//525, 292, 415
			
			if(eachSpace[i].getItem().getId() != 0){
				int colourNumber = inventory.getEachSpace(i).getItem().getId();
				int colourX = colourNumber % 1000;
				int colourY = (colourNumber % 1000000)/1000;
				int colourZ = colourNumber/1000000;
				//----------------Mo¿e byæ max 555!colourX,colourY,colourZ------------------//
				//System.out.println(i + ": " + colourNumber % 1000+", "+(colourNumber % 1000000)/1000+", "+colourNumber/1000000);
				screen.renderItem(screen.xOffset  + column * 8 + column + 100 + 1, screen.yOffset + border*8 + border + 1,eachSpace[i].getItem().getSheetPlace(),
						Colours.get(-1,colourX,colourY,colourZ), 0x00, scale);
			}
			column++;
			
			
			
		}
	}
	
	private void showCharacter(Screen screen,EachSpace[] eachSpace){
		int maxSlots = 6;
	}
	
	
	
	
}
