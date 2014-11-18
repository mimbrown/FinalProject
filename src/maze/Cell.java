package maze;
import java.awt.Graphics;


public class Cell {
	private String name;
	private CellType cellType;
	
	public Cell(CellType cellType) {
		this.cellType = cellType;
		name = null;
	}
	
	public Cell(CellType cellType, String name) {
		this.cellType = cellType;
		this.name = name;
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
	
	public boolean isEntrance() {
		return cellType == CellType.ENTRANCE;
	}
	
	public String getName() {
		return name;
	}
	
	public void draw(Graphics g) {
		
	}

}
