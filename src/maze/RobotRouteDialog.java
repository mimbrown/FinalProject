package maze;

import java.awt.GridLayout;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RobotRouteDialog extends JDialog {
	
	private JDialog routesDialog;
	private JPanel panel;
	private JLabel robot1;
	private JLabel robot2;
	private JLabel robot3;
	private JLabel robot4;
	private JTextField robot1Routes;
	private JTextField robot2Routes;
	private JTextField robot3Routes;
	private JTextField robot4Routes;
	private String routes1;
	private String routes2;
	private String routes3;
	private String routes4;

	public RobotRouteDialog() {
		routesDialog = new JDialog();
		routesDialog.setVisible(true);
		routesDialog.setSize(300, 300);
		routesDialog.setTitle("Robot Routes");
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,2));
		
		robot1 = new JLabel("Robot 1");
		panel.add(robot1);
		robot1Routes = new JTextField();
		panel.add(robot1Routes);
		
		robot2 = new JLabel("Robot 2");
		panel.add(robot2);
		robot2Routes = new JTextField();
		panel.add(robot2Routes);
		
		robot3 = new JLabel("Robot 3");
		panel.add(robot3);
		robot3Routes = new JTextField();
		panel.add(robot3Routes);
		
		robot4 = new JLabel("Robot 4");
		panel.add(robot4);
		robot4Routes = new JTextField();
		panel.add(robot4Routes);
		
		routesDialog.add(panel);
	} 
	
	
	public void setRobotRoutes(int robot, Map<String,Route> knownRoutes) {
		String caverns = "";
		for(Map.Entry<String, Route> entry : knownRoutes.entrySet())
			caverns += entry.getKey();
		
		switch(robot) {
		case 0:
			robot1Routes.setText(caverns);
			break;
		case 1:
			robot2Routes.setText(caverns);
			break;
		case 2:
			robot3Routes.setText(caverns);
			break;
		case 3:
			robot4Routes.setText(caverns);
			break;
		}
		System.out.println("caverns: " + caverns);
	}
	
	public String getRobot1Routes() {
		return routes1;
	}

	public void setRobot2Routes(String routes2) {
		this.routes2 = routes2;
	}
	
	public String getRobot2Routes() {
		return routes2;
	}
	public void setRobot3Routes(String routes3) {
		this.routes3 = routes3;
	}
	
	public String getRobot3Routes() {
		return routes3;
	}
	public void setRobot4Routes(String routes4) {
		this.routes4 = routes4;
	}
	
	public String getRobot4Routes() {
		return routes4;
	}
}
