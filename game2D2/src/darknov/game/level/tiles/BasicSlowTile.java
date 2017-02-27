package darknov.game.level.tiles;

public class BasicSlowTile extends BasicTile{

	public BasicSlowTile(int id, int x, int y, int tileColour, int levelColour) {
		super(id, x, y, tileColour,levelColour);
		this.slow = true;
	}

}