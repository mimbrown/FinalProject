package maze;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mine extends JPanel {
	public static final int NUM_OF_ROBOTS = 4;
	private ArrayList<Robot> robots;
	private Maze maze;
	private int current;
	private int onDeck;
	private ButtonPanel panel;

	public Mine(String fileName) {
		robots = new ArrayList<Robot>();
		maze = new Maze(fileName);
		setLayout(null);
		current = 0;
		onDeck = 1;

	}

	public void loadMine() throws BadConfigFormatException {
		maze.loadMaze();
		createLabels();
		ArrayList<Color> robotColors = new ArrayList<Color>();
		robotColors.add(Color.blue);
		robotColors.add(Color.red);
		robotColors.add(Color.yellow);
		robotColors.add(Color.green);
		for(int i=0; i<NUM_OF_ROBOTS; i++) {
			Robot temp = new Robot(i,maze.getStartingCell(), robotColors.get(i));
			temp.giveMine(this);
			robots.add(temp);
		}


	}

	public void giveButtons(ButtonPanel panels) {
		this.panel = panels;
	}


	public void configureButtons() {
		Map<String, java.awt.Button> buttons = panel.getButtons();
		for(final Map.Entry<String,java.awt.Button> a : buttons.entrySet())
		{
			a.getValue().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					int count = 0;
					while (robots.get(current).canGO() != true && count < NUM_OF_ROBOTS)
					{
						count ++;
						cycleRobots();
					}
					if (robots.get(current).canGO())
					{
						robots.get(current).updateGoal(a.getKey());
						if (robots.get(onDeck).canGO())
						{
							robots.get(current).askForRoute(robots.get(onDeck));
						}
						if(robots.get(current).checkForRoute(a.getKey()) != null)
						{
							robots.get(current).followRoute(maze);
							robots.get(current).goHome(maze);
						}
						else
						{
							robots.get(current).findRoute(maze);
							robots.get(current).goHome(maze);
						}
						robots.get(current).showCurrentRoute();
					}
					cycleRobots();
				}
			});
		}
	}

	public void cycleRobots() {
		current++;
		onDeck++;
		if (current == NUM_OF_ROBOTS)
		{
			current = 0;
		}
		if (onDeck == NUM_OF_ROBOTS)
		{
			onDeck = 0;
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

	public ArrayList<Robot> getRobots() {
		return robots;
	}

}
