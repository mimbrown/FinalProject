package maze;
import java.awt.Graphics;


public class Cell {
	private String name;
	private CellType cellType;
	
	public Cell(CellType cellType) {
		this.cellType = cellType;
		name = "";
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cellType == null) ? 0 : cellType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return false;
	}

	public void draw(Graphics g) {
		
	}

}
