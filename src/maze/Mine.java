package maze;

import java.util.ArrayList;

public class Mine {
	private ArrayList<Robot> robots;
	private Maze maze;
	
	public Mine(String fileName) {
		robots = new ArrayList<Robot>();
		for(int i=0; i<4; i++) {
			robots.add(new Robot(i));
		}
		maze = new Maze(fileName);
		
	}
	
	public void loadMine() {
		maze.loadMaze();
	}
	
	// for testing purposes
	public ArrayList<Robot> getRobots() {
		return robots;
	}

}
