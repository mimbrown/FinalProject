package maze;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javafx.scene.control.Button;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.GlobalVariables;

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
		onDeck = NUM_OF_ROBOTS - 1;
		
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
	
	public void getButtons(ButtonPanel panels) {
		panel = panels;
	}
	
	public void configureButtons() {
		Map<String, java.awt.Button> buttons = panel.getButtons();
		for(Map.Entry<String,java.awt.Button> a : buttons.entrySet())
		{
			a.getValue().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					
				}
			});
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
