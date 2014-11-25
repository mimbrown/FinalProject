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
	private JDialog routesDialog;
	
	
	public MineGUI() {
		mine = new Mine("MazeLayout.csv");
		try {
			mine.loadMine();
		} catch (BadConfigFormatException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		buttonPanel= new ButtonPanel(mine);
		routesDialog = new JDialog();
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
		
		JMenuItem robotRoutes = new JMenuItem("Robot routes");
		robotRoutes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				robotRouteDialog();
			}
		});
		fileMenu.add(robotRoutes);
		fileMenu.add(exitItem);
		menu.add(fileMenu);
		setJMenuBar(menu);
	}
	
	public void robotRouteDialog() {
		
		routesDialog.setVisible(true);
		routesDialog.setSize(300, 300);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,2));
		JLabel robot1 = new JLabel("Robot 1");
		panel.add(robot1);
		JTextField robot1Routes = new JTextField();
		panel.add(robot1Routes);
		JLabel robot2 = new JLabel("Robot 2");
		panel.add(robot2);
		JTextField robot2Routes = new JTextField();
		panel.add(robot2Routes);
		JLabel robot3 = new JLabel("Robot 3");
		panel.add(robot3);
		JTextField robot3Routes = new JTextField();
		panel.add(robot3Routes);
		JLabel robot4 = new JLabel("Robot 4");
		panel.add(robot4);
		JTextField robot4Routes = new JTextField();
		panel.add(robot4Routes);
		
		routesDialog.add(panel);
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
