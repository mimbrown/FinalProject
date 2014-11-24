package maze;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mine extends JPanel {
	public static final int NUM_OF_ROBOTS = 4;
	private ArrayList<Robot> robots;
	private Maze maze;
	private JLabel aLabel;
	private JLabel bLabel;
	private JLabel cLabel;
	private JLabel dLabel;
	private JLabel eLabel;
	private JLabel fLabel;
	private JLabel gLabel;
	private JLabel hLabel;
	private JLabel iLabel;
	
	
	public Mine(String fileName) {
		robots = new ArrayList<Robot>();
		maze = new Maze(fileName);
		setLayout(null);
		
	}
	
	public void loadMine() throws BadConfigFormatException {
		maze.loadMaze();
		aLabel = new JLabel("Cavern A");
		bLabel = new JLabel("Cavern B");
		cLabel = new JLabel("Cavern C");
		dLabel = new JLabel("Cavern D");
		eLabel = new JLabel("Cavern E");
		fLabel = new JLabel("Cavern F");
		gLabel = new JLabel("Cavern G");
		hLabel = new JLabel("Cavern H");
		iLabel = new JLabel("Cavern I");
		
		aLabel.setBounds(110, 60, 100,100);
		bLabel.setBounds(15, 300, 100, 100);
		cLabel.setBounds(15, 410, 100, 100);
		dLabel.setBounds(140, 355, 100, 100);
		eLabel.setBounds(380, 500, 100, 100);
		fLabel.setBounds(255, 325, 100, 100);
		gLabel.setBounds(375, 325, 100, 100);
		hLabel.setBounds(500, 500, 100, 100);
		iLabel.setBounds(567, 20, 100, 100);
		
		for(int i=0; i<NUM_OF_ROBOTS; i++) {
			robots.add(new Robot(i, maze.getStartingCell()));
		}
		add(aLabel);
		add(bLabel);
		add(cLabel);
		add(dLabel);
		add(eLabel);
		add(fLabel);
		add(gLabel);
		add(hLabel);
		add(iLabel);
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
	
	/*public static void main(String[] args) {
		Mine ourMine = new Mine("MazeLayout.csv");
		try {
			ourMine.loadMine();
		} catch(BadConfigFormatException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		ourMine.getRobots().get(0).updateGoal("f");
		ourMine.getRobots().get(0).findRoute(ourMine.getMaze());
	}*/

}
