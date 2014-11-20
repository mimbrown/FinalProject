package maze;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;

public class MineGUI extends JFrame {
	private Mine mine;
	private ButtonPanel buttonPanel;
	
	public MineGUI() {
		mine = new Mine("MazeLayout.csv");
		try {
			mine.loadMine();
		} catch (BadConfigFormatException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		buttonPanel = new ButtonPanel();
		setSize(600, 600);
		setLayout(new BorderLayout());
		add(mine, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.EAST);
	}
	
	public static void main(String[] args) {
		MineGUI ourMine = new MineGUI();
		ourMine.setVisible(true);
	}
}
