package maze;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		cavernAButton = new Button("Cavern A");
		cavernBButton = new Button("Cavern B");
		cavernCButton = new Button("Cavern C");
		cavernDButton = new Button("Cavern D");
		cavernEButton = new Button("Cavern E");
		cavernFButton = new Button("Cavern F");
		cavernGButton = new Button("Cavern G");
		cavernHButton = new Button("Cavern H");
		cavernIButton = new Button("Cavern I");
		setLayout(new GridLayout(5,2));
		
		setBorder(new TitledBorder(new EtchedBorder(), "Find Cavern"));
		add(cavernAButton);
		add(cavernBButton);
		add(cavernCButton);
		add(cavernDButton);
		add(cavernEButton);
		add(cavernFButton);
		add(cavernGButton);
		add(cavernHButton);
		add(cavernIButton);
		
		setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
	}
}
