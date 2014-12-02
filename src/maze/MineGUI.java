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
	private SpeedControl speedControl;
	private RobotRouteScreen routesDialog;
	
	
	public MineGUI() {
		mine = new Mine("MazeLayout.csv");
		try {
			mine.loadMine();
		} catch (BadConfigFormatException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		buttonPanel= new ButtonPanel(mine);
		speedControl = new SpeedControl();
		routesDialog = new RobotRouteScreen(mine.getRobots(), mine.getMaze().getCaverns());
		setSize(800, 700);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(mine, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.LINE_END);
		add(speedControl, BorderLayout.SOUTH);
		mine.giveButtons(buttonPanel);
		mine.configureButtons();
		menuBar();
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
				routesDialog.setVisible(true);
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
