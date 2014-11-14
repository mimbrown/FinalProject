package maze;
import java.awt.Graphics;


public class Cell {
	private String name;
	private CellType cellType;
	
	public Cell() {
		
	}
	
	public boolean isPathway() {
		return cellType == CellType.PATHWAY;
	}
	
	public boolean isCavern() {
		return cellType == CellType.CAVERN;
	}
	
	public boolean isWall() {
		return cellType == CellType.WALL;
	}
	
	public void draw(Graphics g) {
		
	}

}
