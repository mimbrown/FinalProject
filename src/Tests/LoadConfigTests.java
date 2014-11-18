package Tests;

import static org.junit.Assert.*;

import java.util.Set;

import junit.framework.Assert;
import maze.Maze;
import maze.Mine;

import org.junit.Test;

import maze.BadConfigFormatException;

public class LoadConfigTests {

	private Mine mine;
	
	//Tests to see if improper layout (uneven rows) throw an exception.
	@Test(expected = BadConfigFormatException.class)
	public void improperBoardLayout() throws BadConfigFormatException {
		mine = new Mine("BadMazeLayout.csv");
		mine.loadMine();
	}
	
	//Tests to see if improper layout (no start) throws an exception.
	@Test(expected = BadConfigFormatException.class)
	public void noStartMazeLayout() throws BadConfigFormatException {
		mine = new Mine("noStartMazeLayout.csv");
		mine.loadMine();
	}
	
	//Tests multiple points to see if the board loaded correctly. Checks cavern names, wall names, and points for caverns and walls.
	@Test
	public void testingPointsOnTheBoard() {
		mine = new Mine("MazeLayout.csv");
		try {
			mine.loadMine();
		} catch(BadConfigFormatException e) {}
		Maze maze = mine.getMaze();
		
		Assert.assertTrue(maze.getCellAt(11,1).isCavern());
		Assert.assertTrue(maze.getCellAt(1,11).isPathway());
		Assert.assertTrue(maze.getCellAt(8,6).isWall());
		Assert.assertTrue(maze.getCellAt(6,8).isPathway());
		Assert.assertTrue(maze.getCellAt(4,5).getName().equals("a"));
		Assert.assertTrue(maze.getCellAt(19,20).getName() == null);
		Assert.assertTrue(maze.getCellAt(16,1).getName().equals("c"));
	}
	
	//Tests number of caverns on the board, and the names of the caverns.
	@Test
	public void testingCaverns() {
		mine = new Mine("mazeLayout.csv");
		try {
			mine.loadMine();
		} catch(BadConfigFormatException e) {}
		Maze maze = mine.getMaze();
		Set<String> caverns = maze.getCaverns();
		
		Assert.assertTrue(caverns.size()==9);
		Assert.assertTrue(caverns.contains("a"));
		Assert.assertTrue(caverns.contains("b"));
		Assert.assertTrue(caverns.contains("c"));
		Assert.assertTrue(caverns.contains("d"));
		Assert.assertTrue(caverns.contains("e"));
		Assert.assertTrue(caverns.contains("f"));
		Assert.assertTrue(caverns.contains("g"));
		Assert.assertTrue(caverns.contains("h"));
		Assert.assertTrue(caverns.contains("i"));
	}
}
