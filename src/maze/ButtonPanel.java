package maze;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ButtonPanel extends JPanel {
	private Button cavernAButton;
	private Button cavernBButton;
	private Button cavernCButton;
	private Button cavernDButton;
	private Button cavernEButton;
	private Button cavernFButton;
	private Button cavernGButton;
	private Button cavernHButton;
	private Button cavernIButton;
	
	public ButtonPanel(Mine mine)
	{

		Set<String> names = mine.getMaze().getCaverns();
		setLayout(new GridLayout((int) (names.size() / 2) + 1,2));
		for(String a : names)
		{
		Button cavernButton = new Button("Cavern " + a.toUpperCase());
		add(cavernButton);
		}
		
		setBorder(new TitledBorder(new EtchedBorder(), "Find Cavern"));
		
		setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
	}
}
