package darknov.game.inventory;

public class EachSpace {
	/*
	private String name;
	private String description;
	private Item type;
	private int id;
	private int sheetPlace;
	*/
	Item item;
	
	public EachSpace(Item item){
		this.item = item;
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
