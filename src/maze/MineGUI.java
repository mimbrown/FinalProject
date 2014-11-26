package maze;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MineGUI extends JFrame {
	private static Mine mine;
	private ButtonPanel buttonPanel;
	private RobotRouteDialog routesDialog;
	
	
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
		mine.giveButtons(buttonPanel);
		mine.configureButtons();
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
		
		JMenuItem robotRoutes = new JMenuItem("Robot routes");
		robotRoutes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				routesDialog = new RobotRouteDialog();
				// set the text field for each robot
				for(int i = 0; i < mine.getRobots().size(); i++) 
					routesDialog.setRobotRoutes(i,mine.getRobots().get(i).getKnownRoutes());
			}
		});
		fileMenu.add(robotRoutes);
		fileMenu.add(exitItem);
		menu.add(fileMenu);
		setJMenuBar(menu);
	}
	
	public static void main(String[] args) throws InterruptedException {
		MineGUI ourMine = new MineGUI();
		ourMine.setVisible(true);
		
	}
}
