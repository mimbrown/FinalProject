package maze;
import java.awt.Color;
import java.awt.Graphics;


public class Cell {
	public static final int CELL_SIZE = 30;
	
	private String name;
	private CellType cellType;
	private int row;
	private int col;
	
	public Cell(CellType cellType) {
		this.cellType = cellType;
		name = "";
	}
	
	public Cell(CellType cellType, String name) {
		this.cellType = cellType;
		this.name = name;
	}
	
	public Cell(CellType cellType, String name, int row, int col) {
		this.cellType = cellType;
		this.name = name;
		this.row = row;
		this.col = col;
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
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
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
		
		if(isPathway() || isEntrance()) {
			g.setColor(Color.lightGray);
		}
		else if(isWall()) {
			g.setColor(Color.darkGray);
		}
		else if(isCavern()) {
			g.setColor(Color.white);
		}
		g.fillRect(col*CELL_SIZE, row*CELL_SIZE, CELL_SIZE, CELL_SIZE);
	}

}
