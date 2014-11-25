package maze;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MineGUI extends JFrame {
	private static Mine mine;
	private ButtonPanel buttonPanel;
	
	
	public MineGUI() {
		mine = new Mine("MazeLayout.csv");
		try {
			mine.loadMine();
		} catch (BadConfigFormatException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		buttonPanel= new ButtonPanel(mine);
		menuBar();
		setSize(800, 650);
		setLayout(new BorderLayout());
		add(mine, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.LINE_END);
	}
	
	public void menuBar() {
		JMenuBar menu = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenu robotRoutes = new JMenu("Robot routes");
		JMenuItem robot1 = new JMenuItem("Robot 1");
		JMenuItem robot2 = new JMenuItem("Robot 2");
		JMenuItem robot3 = new JMenuItem("Robot 3");
		JMenuItem robot4 = new JMenuItem("Robot 4");
		
		robotRoutes.add(robot1);
		robotRoutes.add(robot2);
		robotRoutes.add(robot3);
		robotRoutes.add(robot4);
		
		fileMenu.add(exitItem);
		fileMenu.add(robotRoutes);
		menu.add(fileMenu);
		setJMenuBar(menu);
	}
	
	public static void main(String[] args) throws InterruptedException {
		MineGUI ourMine = new MineGUI();
		ourMine.setVisible(true);
		mine.getRobots().get(0).updateGoal("d");
		mine.getRobots().get(0).findRoute(mine.getMaze());
		mine.getRobots().get(0).goHome(mine.getMaze());
		mine.getRobots().get(0).followRoute(mine.getMaze());
		mine.getRobots().get(0).goHome(mine.getMaze());
	}
}
