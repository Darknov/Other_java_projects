package darknov.game.inventory;

public abstract class Item {
	protected String name;
	protected String description;
	protected String type;
	protected int id;
	protected int sheetPlace;
	
	public Item(int id,String name,String type,int sheetPlace){
		this.id = id;
		this.name = name;
		this.type = type;
		this.sheetPlace = sheetPlace;
	}
	
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getType() {
		return type;
	}
	
	public int getId() {
		return id;
	}
	
	public int getSheetPlace() {
		return sheetPlace;
	}
	
	
	
}
