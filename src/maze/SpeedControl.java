package maze;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class SpeedControl extends JPanel {
	private Button fastButton, slowButton;
	
	public SpeedControl() {
		setLayout(new GridLayout(1,2));
		setBorder(new TitledBorder(new EtchedBorder(), "Speed Control"));
		fastButton = new Button("Quick Find");
		fastButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Robot.pause = 10;
			}
		});
		slowButton = new Button("Normal Speed");
		slowButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Robot.pause = 100;
			}
		});
		add(fastButton);
		add(slowButton);
	}
}
