package maze;
import java.util.ArrayList;


public class Route {
	private ArrayList<Direction> route;
	private String cavern;
	
	public Route() {
		this.cavern = null;
		route = null;
	}
	
	public Route(String cavern) {
		this.cavern = cavern;
		route = new ArrayList<Direction>();
	}
	
	public void stepForward(Direction direction) {
		route.add(direction);
	}
	
	public void stepBack() {
		route.remove(route.size()-1);
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
		Boolean identical = true;
		Route other = (Route) obj;
		if (!other.cavern.equals(this.cavern))
		{
			identical = false;
		}
		if (other.route.size() != this.route.size())
		{
			identical = false;
			return identical;
		}
		for (int i = 0; i < this.route.size(); i++)
		{
			if (this.route.get(i) != other.route.get(i))
			{
				identical = false;
			}
		}
		return identical;
	}
	
}
