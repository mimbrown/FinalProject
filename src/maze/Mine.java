package maze;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Mine extends JPanel {
	private ArrayList<Robot> robots;
	private Maze maze;
	
	public Mine(String fileName) {
		robots = new ArrayList<Robot>();
		maze = new Maze(fileName);
	}
	
	public void loadMine() throws BadConfigFormatException {
		maze.loadMaze();
		for(int i=0; i<4; i++) {
			robots.add(new Robot(i, maze.getStartingCell()));
		}
	}
	
	public Maze getMaze() {
		return maze;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		maze.draw(g);
		for(Robot r : robots) {
			r.draw(g);
		}
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
		ourMine.getRobots().get(0).findRoute(ourMine.getMaze());
	}

}
