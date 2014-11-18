package maze;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;


public class Robot {
	private int number;
	private Set<Route> knownRoutes;
	private Route currentRoute;
	private Cell currentLocation;
	private Set<Cell> visited;
	private Color color;
	private String goalCavern;
	
	public Robot(int number, Cell startingLocation) {
		this.number = number;
		this.currentLocation = startingLocation;
		visited = new HashSet<Cell>();
		knownRoutes = new HashSet<Route>();
		goalCavern = "";
	}
	
	// returns a boolean indicating whether the cavern was able to be found
	public boolean findRoute(Maze maze) {
		currentRoute = new Route(goalCavern);
		recursion(maze, maze.getStartingLocationRow(), maze.getStartingLocationCol());
		if(currentLocation.getName().equals(goalCavern)) {
			knownRoutes.add(currentRoute);
			return true;
		}
		return false;
	}
	
	private void recursion(Maze maze, int row, int col) {
		if(currentLocation.getName().equals(goalCavern)) {
			return;
		}
		visited.add(currentLocation);
		if((maze.getCellAt(row, col+1).isPathway() || maze.getCellAt(row, col+1).isCavern())
				&& !visited.contains(maze.getCellAt(row, col+1))) {
			currentLocation = maze.getCellAt(row, col+1);
			currentRoute.stepForward(Direction.RIGHT);
			recursion(maze, row, col+1);
		}
		if((maze.getCellAt(row+1, col).isPathway() || maze.getCellAt(row+1, col).isCavern())
				&& !visited.contains(maze.getCellAt(row+1, col))) {
			currentLocation = maze.getCellAt(row+1, col);
			currentRoute.stepForward(Direction.DOWN);
			recursion(maze, row+1, col);
		}
		if((maze.getCellAt(row, col-1).isPathway() || maze.getCellAt(row, col-1).isCavern())
				&& !visited.contains(maze.getCellAt(row, col-1))) {
			currentLocation = maze.getCellAt(row, col-1);
			currentRoute.stepForward(Direction.LEFT);
			recursion(maze, row, col-1);
		}
		if((maze.getCellAt(row-1, col).isPathway() || maze.getCellAt(row-1, col).isCavern())
				&& !visited.contains(maze.getCellAt(row-1, col))) {
			currentLocation = maze.getCellAt(row-1, col);
			currentRoute.stepForward(Direction.UP);
			recursion(maze, row-1, col);
		}
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
	
	public Set<Route> getKnownRoutes() {
		return knownRoutes;
	}

}
