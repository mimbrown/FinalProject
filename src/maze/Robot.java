package maze;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Robot {
	private int number;
	private Map<String,Route> knownRoutes;
	private Route currentRoute;
	private Cell currentLocation;
	private Set<Cell> visited;
	private Color color;
	private String goalCavern;
	private Boolean foundCavern;

	public Robot(int number, Cell startingLocation) {
		this.number = number;
		this.currentLocation = startingLocation;
		visited = new HashSet<Cell>();
		knownRoutes = new HashMap<String,Route>();
		goalCavern = "";
	}

	// returns a boolean indicating whether the cavern was able to be found
	public boolean findRoute(Maze maze) {

		currentRoute = new Route(goalCavern);
		visited.clear();
		foundCavern = false;
		recursion(maze, maze.getStartingLocationRow(), maze.getStartingLocationCol());
		if(foundCavern) {
			knownRoutes.put(goalCavern,currentRoute);
			System.out.println("Found Cavern!");
			return true;
		}
		return false;

	}

	private void recursion(Maze maze, int row, int col) {
		currentLocation = maze.getCellAt(row, col);
		visited.add(currentLocation);
		
		if(currentLocation.getName().equals(goalCavern) || foundCavern) {
			foundCavern = true;
			return;
		}

		if (row < maze.getNumRows() - 1 && !foundCavern)
		{
			if(!maze.getCellAt(row+1, col).isWall() && !visited.contains(maze.getCellAt(row+1, col)) && !foundCavern) {
				currentRoute.stepForward(Direction.DOWN);
				recursion(maze, row+1, col);
				if (!foundCavern)
				{
					currentRoute.stepBack();
				}
			}
		}
		if (col < maze.getNumColumns() - 1 && !foundCavern)
		{
			if(!maze.getCellAt(row, col+1).isWall() && !visited.contains(maze.getCellAt(row, col+1))) {
				currentRoute.stepForward(Direction.RIGHT);
				recursion(maze, row, col+1);
				if (!foundCavern)
				{
					currentRoute.stepBack();
				}
			}
		}
		if (row > 0 && !foundCavern)
		{
			if(!maze.getCellAt(row-1, col).isWall() && !visited.contains(maze.getCellAt(row-1, col)) && !foundCavern) {
				currentRoute.stepForward(Direction.UP);
				recursion(maze, row-1, col);
				if (!foundCavern)
				{
					currentRoute.stepBack();
				}
			}
		}
		if (col > 0 && !foundCavern)
		{
			if(!maze.getCellAt(row, col-1).isWall() && !visited.contains(maze.getCellAt(row, col-1)) && !foundCavern) {
				currentRoute.stepForward(Direction.LEFT);
				recursion(maze, row, col-1);
				if (!foundCavern)
				{
					currentRoute.stepBack();
				}
			}
		}
		visited.remove(currentLocation);
	}

	public void followRoute() {

	}

	public Route checkForRoute() {
		return null;
	}

	public Route askForRoute(Robot robot) {
		return null;
	}

	public void updateGoal(String newCavern) {
		goalCavern = newCavern;
	}

	public void goHome() {
	}

	public void draw(Graphics g) {

	}

	public Map<String,Route> getKnownRoutes() {
		return knownRoutes;
	}

}
