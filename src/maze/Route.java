package maze;
import java.util.ArrayList;


public class Route {
	private ArrayList<Direction> route;
	private String cavern;
	
	public Route() {
		
	}
	
	public void stepForward(Direction direction) {
		
	}
	
	public void stepBack() {
		
	}
	
	public ArrayList<Direction> invertRoute(ArrayList<Direction> route) {
		return null;
		
	}
	//for test purposes
	public ArrayList<Direction> getRoute()
	{
		return route;
	}
	//for test purposes
	public void setRoute(ArrayList<Direction> newRoute)
	{
		this.route = newRoute;
	}
	
	public String getCavern() {
		return cavern;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cavern == null) ? 0 : cavern.hashCode());
		result = prime * result + ((route == null) ? 0 : route.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Route) {
			if(this.route.equals(((Route) obj).getRoute()) && this.cavern.equals(((Route) obj).getCavern())) {
				return true;
			}
		}
		return false;
	}
	
}
