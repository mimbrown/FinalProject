package maze;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RobotRouteDialog extends JDialog {
	
	private JDialog routesDialog;
	private JPanel panel;
	private Set<String> caverns;
	private Map<String, ArrayList<JCheckBox>> columns;

	public RobotRouteDialog(ArrayList<Robot> robots, Set<String> caverns) {
		routesDialog = new JDialog();
		routesDialog.setVisible(true);
		routesDialog.setSize(800, 300);
		routesDialog.setTitle("Robot Routes");
		panel = new JPanel();
		this.caverns = caverns;
		columns = new HashMap<String, ArrayList<JCheckBox>>();
		panel.setLayout(new GridLayout(robots.size()+1,caverns.size()+1));
		panel.add(new JLabel(""));
		for(String c : caverns) {
			panel.add(new JLabel("Cavern " + c.toUpperCase()));
			ArrayList<JCheckBox> newColumn = new ArrayList<JCheckBox>();
			for(int j=0; j<robots.size(); j++) {
				JCheckBox temp = new JCheckBox();
				temp.setEnabled(false);
				newColumn.add(temp);
			}
			columns.put(c, newColumn);
		}
		for(int i=0; i<robots.size(); i++) {
			panel.add(new JLabel("Robot " + (i+1)));
			for(String c : caverns) {
				panel.add(columns.get(c).get(i));
			}
		}
		routesDialog.add(panel);
	} 
	
	
	public void setRobotRoutes(int robot, Map<String,Route> knownRoutes) {
		for(String c : caverns) {
			if(knownRoutes.keySet().contains(c)) {
				columns.get(c).get(robot).setSelected(true);
			}
		}
	}
	
}
