package maze;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		buttonPanel= new ButtonPanel(mine);
		setSize(800, 650);
		setLayout(new BorderLayout());
		add(mine, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.LINE_END);
	}
	
	public static void main(String[] args) {
		MineGUI ourMine = new MineGUI();
		ourMine.setVisible(true);
	}
}
