package maze;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mine extends JPanel {
	public static final int NUM_OF_ROBOTS = 4;
	private ArrayList<Robot> robots;
	private Maze maze;
	
	
	public Mine(String fileName) {
		robots = new ArrayList<Robot>();
		maze = new Maze(fileName);
		setLayout(null);
		
	}
	
	public void loadMine() throws BadConfigFormatException {
		maze.loadMaze();
		createLabels();
		for(int i=0; i<NUM_OF_ROBOTS; i++) {
			robots.add(new Robot(i, maze.getStartingCell()));
		}
	}
	
	private void createLabels()
	{
		Set<String> caverns = maze.getCaverns();

		for(String a : caverns)
		{
			JLabel temp = new JLabel("Cavern " + a.toUpperCase());
			int xpos = 0;
			int ypos = 0;
			int count = 0;
			for(int i = 0; i < maze.getNumColumns(); i++)
			{
				for(int ii = 0; ii < maze.getNumRows(); ii++)
				{
					if(maze.getCellAt(ii, i).isCavern())
					{
						if(maze.getCellAt(ii,i).getName().equals(a))
						{
							
							xpos = xpos + i * Cell.CELL_SIZE;
							ypos = ypos + ii * Cell.CELL_SIZE;
							count++;
						}
					}
				}
			}
			xpos = xpos/count;
			ypos = ypos/count;
			temp.setBounds(xpos-8,ypos-38,100,100);
			add(temp);
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

}
