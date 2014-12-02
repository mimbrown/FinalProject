package maze;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;


public class Robot {
	private int number;
	private Map<String,Route> knownRoutes;
	private Route currentRoute;
	private Cell currentLocation;
	private ArrayList<Cell> path;
	private Set<Cell> visited;
	private Color color;
	private String goalCavern;
	private Boolean foundCavern;
	private Mine mine;
	public static int pause = 100;
	private int where;
	private Timer timer;
	private boolean canGo;

	public Robot(int number, Cell startingLocation, Color color) {
		this.number = number;
		this.currentLocation = startingLocation;
		this.color = color;
		visited = new HashSet<Cell>();
		knownRoutes = new HashMap<String,Route>();
		goalCavern = "";
		path = new ArrayList<Cell>();
		canGo = true;
	}

	// returns a boolean indicating whether the cavern was able to be found
	public boolean findRoute(Maze maze){

		currentRoute = new Route(goalCavern);
		visited.clear();
		path.clear();
		foundCavern = false;
		recursion(maze, maze.getStartingLocationRow(), maze.getStartingLocationCol());
		if(foundCavern) {
			knownRoutes.put(goalCavern,currentRoute);
			return true;
		}
		return false;

	}

	private void recursion(Maze maze, int row, int col) {
		currentLocation = maze.getCellAt(row, col);
		path.add(currentLocation);
		visited.add(currentLocation);

		if(currentLocation.getName().equals(goalCavern) || foundCavern) {
			foundCavern = true;
			return;
		}
		else if(currentLocation.isCavern() && !knownRoutes.containsKey(currentLocation.getName())) {
			knownRoutes.put(currentLocation.getName(), currentRoute.copy());
		}

		if (row < maze.getNumRows() - 1 && !foundCavern)
		{
			if(!maze.getCellAt(row+1, col).isWall() && !visited.contains(maze.getCellAt(row+1, col)) && !foundCavern) {
				currentRoute.stepForward(Direction.DOWN);
				recursion(maze, row+1, col);
				if (!foundCavern)
				{
					currentRoute.stepBack();
					currentLocation = maze.getCellAt(row, col);
					path.add(currentLocation);
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
					currentLocation = maze.getCellAt(row, col);
					path.add(currentLocation);
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
					currentLocation = maze.getCellAt(row, col);
					path.add(currentLocation);
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
					currentLocation = maze.getCellAt(row, col);
					path.add(currentLocation);
				}
			}
		}
	}

	public void followRoute(Maze maze)
	{
		path.clear();
		ArrayList<Direction> temp = knownRoutes.get(goalCavern).getRoute();
		int row = maze.getStartingLocationRow();
		int col = maze.getStartingLocationCol();
		currentLocation = maze.getCellAt(row, col);
		path.add(currentLocation);
		for (int i = 0; i < temp.size(); i++)
		{
			if (temp.get(i) == Direction.DOWN)
			{
				row = row + 1;
				currentLocation = maze.getCellAt(row, col);
				path.add(currentLocation);
			}
			if (temp.get(i) == Direction.RIGHT)
			{
				col = col + 1;
				currentLocation = maze.getCellAt(row, col);
				path.add(currentLocation);
			}
			if (temp.get(i) == Direction.UP)
			{
				row = row - 1;
				currentLocation = maze.getCellAt(row, col);
				path.add(currentLocation);
			}
			if (temp.get(i) == Direction.LEFT)
			{
				col = col - 1;
				currentLocation = maze.getCellAt(row, col);
				path.add(currentLocation);
			}
		}
	}

	public Route checkForRoute(String name) {
		if (knownRoutes.containsKey(name))
		{
			return knownRoutes.get(name);
		}
		return null;
	}

	public void askForRoute(Robot robot) {
		Route temp = robot.checkForRoute(goalCavern);
		if (temp != null)
		{
			knownRoutes.put(goalCavern, temp);
		}
	}

	public void updateGoal(String newCavern) {
		goalCavern = newCavern;
	}


	public void goHome(Maze maze){
		int row = currentLocation.getRow();
		int col = currentLocation.getCol();
		ArrayList<Direction> wayBack = knownRoutes.get(goalCavern).getRoute();
		for(int i = wayBack.size()-1; i >= 0; i--)
		{
			currentLocation = maze.getCellAt(row, col);
			if (wayBack.get(i) == Direction.DOWN)
			{
				row = row - 1;
				currentLocation = maze.getCellAt(row, col);
				path.add(currentLocation);
			}
			if (wayBack.get(i) == Direction.RIGHT)
			{
				col = col - 1;
				currentLocation = maze.getCellAt(row, col);
				path.add(currentLocation);
			}
			if (wayBack.get(i) == Direction.UP)
			{
				row = row + 1;
				currentLocation = maze.getCellAt(row, col);
				path.add(currentLocation);
			}
			if (wayBack.get(i) == Direction.LEFT)
			{
				col = col + 1;
				currentLocation = maze.getCellAt(row, col);
				path.add(currentLocation);
			}

		}
	}

	public void giveMine(Mine mine)
	{
		this.mine = mine;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(currentLocation.getCol() * Cell.CELL_SIZE, Maze.MARGIN +
				currentLocation.getRow() * Cell.CELL_SIZE, Cell.CELL_SIZE, Cell.CELL_SIZE);
	}
	
	public void draw(Graphics g, int x, int y) {
		g.setColor(color);
		g.fillOval(x, y, Cell.CELL_SIZE, Cell.CELL_SIZE);
	}

	public Map<String,Route> getKnownRoutes() {
		return knownRoutes;
	}

	public void showCurrentRoute() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				if (where == path.size())
				{
					canGo = true;
					timer.cancel();
				}
				else
				{
					currentLocation = path.get(where);
					mine.repaint();
					where++;
				}
			}
		};
		if (canGo == true)
		{
			where = 0;
			canGo = false;
			timer = new Timer();
			timer.schedule(task, pause, pause);
		}
	}

}
