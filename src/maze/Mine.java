package maze;

import java.util.ArrayList;

public class Mine {
	private ArrayList<Robot> robots;
	private Maze maze;
	
	public Mine(String fileName) {
		robots = new ArrayList<Robot>();
		maze = new Maze(fileName);
		for(int i=0; i<4; i++) {
			robots.add(new Robot(i, maze.getStartingCell()));
		}
		
	}
	
	public void loadMine() throws BadConfigFormatException {
		maze.loadMaze();
	}
	
	public Maze getMaze() {
		return maze;
	}
	// for testing purposes
	public ArrayList<Robot> getRobots() {
		return robots;
	}
	
	public static void main(String[] args) {
		Mine ourMine = new Mine("MazeLayout.csv");
		try {
			ourMine.loadMine();
		} catch(BadConfigFormatException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		ourMine.getRobots().get(0).updateGoal("f");
		//ourMine.getRobots().get(0).findRoute(ourMine.getMaze());
		Route test1 = new Route("f");
		test1.stepForward(Direction.UP);
		Route test2 = test1.copy();
		if(test1.equals(test2)) {
			System.out.println("Good...");
		}
		test1.stepForward(Direction.UP);
		if(!test1.equals(test2)) {
			System.out.println("Very Good!");
		}
	}

}
