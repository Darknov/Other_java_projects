package darknov.game.inventory;

public abstract class Weapon extends Item{
	private int damage;

	public Weapon(int id, String name, String type,int damage,int sheetPlace) {
		super(id, name, type,sheetPlace);
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}
}
