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
			System.out.println("names don't match");
			identical = false;
		}
		if (other.route.size() != this.route.size())
		{
			System.out.println("length doesn't match");
			System.out.println(other.route.size());
			System.out.println(this.route.size());
			identical = false;
			return identical;
		}
		for (int i = 0; i < this.route.size(); i++)
		{
			if (this.route.get(i) != other.route.get(i))
			{
				System.out.println("direction doesn't match");
				identical = false;
			}
		}
		return identical;
	}
	
	// When robots talk, they should give each other copies of routes,
	// so that if one robot learns a better route, the other robots
	// don't automatically update their own routes.
	public Route copy() {
		Route copy = new Route(this.cavern);
		for(Direction d : this.route){
			copy.stepForward(d);
		}
		return copy;
	}
	
}
