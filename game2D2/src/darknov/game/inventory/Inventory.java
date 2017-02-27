package darknov.game.inventory;

public class Inventory {
	private int maxSpace;
	private EachSpace eachSpace[];
	public Inventory(int maxSpace){
		this.maxSpace = maxSpace;
		setMaxEachSpace(getMaxSpace());
		for(int i = 0; i < maxSpace;i++){
			eachSpace[i] = new EachSpace(new EmptySpace());
		}
	}

	public int getMaxSpace() {
		return maxSpace;
	}
	
	public void setMaxEachSpace(int maxEachSpace) {
		eachSpace = new EachSpace[maxEachSpace];
	}
	
	public EachSpace getEachSpace(int id) {
		return eachSpace[id];
	}
	
	public EachSpace[] getAllSpace() {
		return eachSpace;
	}
	
	public void addItem(Item type) {
		int numberOfItems = 0;
		EmptySpace emptySpace = new EmptySpace();
		while(numberOfItems < 30 && eachSpace[numberOfItems].item.type != "")	{
			System.out.println(numberOfItems);
			numberOfItems++;
		}
		if(numberOfItems < 30){
			eachSpace[numberOfItems] = new EachSpace(type);
		}
		else{
			System.out.println("Za ma³o miejsca w gospodzie!");
			
		}
			
	}
}
