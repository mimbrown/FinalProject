package maze;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Maze {
	private Cell[][] maze;
	private Set<String> cavernNames;
	private String mazeFile;
	private int startingRow, startingCol;
	private Cell startingCell;
	private int numRows, numColumns;

	public Maze(String fileName) {
		mazeFile = fileName;
		numRows = 0;
		numColumns = 0;
		maze = new Cell[100][100];
		startingCell = null;
		cavernNames = new HashSet<String>();
	}

	public void loadMaze() throws BadConfigFormatException {
		Scanner scan = null;
		FileReader reader = null;
		try {
			reader = new FileReader(mazeFile);
			scan = new Scanner(reader);
		} catch(FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
			System.exit(0);
		}
		while(scan.hasNextLine()) {
			// read in row
			String layoutLine = scan.nextLine();
			String temp[];
			if (layoutLine.indexOf(',') > -1) {
				temp = layoutLine.split(",");
				// get the numColumns from the first row
				if(numRows == 0) {
					numColumns = temp.length;
				}
				if (temp.length == numColumns) {
					// read each column of row
					for (int column = 0; column < numColumns; column++) {
						try {
							if(temp[column].length() == 1) {
								if(temp[column].equalsIgnoreCase("w")) {
									maze[numRows][column] = new Cell(CellType.PATHWAY);
								}
								else if(temp[column].equalsIgnoreCase("x")) {
									maze[numRows][column] = new Cell(CellType.WALL);
								}
								else if(temp[column].equalsIgnoreCase("s")) {
									maze[numRows][column] = new Cell(CellType.ENTRANCE);
								}
								else {
									maze[numRows][column] = new Cell(CellType.CAVERN, temp[column]);
									cavernNames.add(temp[column]);
								}
							}
							else throw new BadConfigFormatException("Unable to read in a cell.");
						}
						catch(NullPointerException e) {
							throw new BadConfigFormatException("Bad Layout file");
						}
					}
					numRows++;
				}
				else {
					throw new BadConfigFormatException("Maze rows are of unequal lengths.");
				}
			}
			else {
				throw new BadConfigFormatException("Bad Layout file");
			}
		}
		// now check to make sure we have what we need in the file
		boolean hasStart = false;
		for(int rows=0; rows<numRows; rows++) {
			for(int cols=0; cols<numColumns; cols++) {
				if(maze[rows][cols].isEntrance()) {
					hasStart = true;
					startingCell = maze[rows][cols];
					startingRow = rows;
					startingCol = cols;
				}
			}
		}
		if(!hasStart) {
			throw new BadConfigFormatException("Mine doesn't have an entrance.");
		}
	}

	public Cell getCellAt(int row, int col) {
		return maze[row][col];
	}

	public Set<String> getCaverns() {
		return cavernNames;
	}
	
	public int getStartingLocationRow() {
		return startingRow;
	}
	
	public int getStartingLocationCol() {
		return startingCol;
	}
	
	public Cell getStartingCell() {
		return startingCell;
	}

	public void draw(Graphics g) {

	}

}
