package Tests;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.Set;

import maze.Direction;
import maze.Maze;
import maze.Robot;
import maze.Route;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RepeatRouteTests {
	
	/*
	 * This is the method that is called on a robot before it attempts to find a route for a given cavern. If it has 
	 * the route to a given cavern, or the robot directly behind it has the route, it will take the route to the cavern. This 
	 * will be tested by the robot having the route, the robot behind it having the route, the robot 2 behind it having the 
	 * route, and no robots having the route.
	 */
	private ArrayList<Robot> robots;
	private Maze maze;
	private ArrayList<Direction> routeToF;
	private ArrayList<Direction> routeToB;
	private Robot robot1;
	private Robot robot2;
	private Robot robot3;
	
	@BeforeClass
	public void Before() throws AWTException {
		Maze maze = new Maze("MazeLayout.csv");
		maze.loadMaze();
		robots = new ArrayList<Robot>();
		robot1 = new Robot();
		robot2 = new Robot();
		robot3 = new Robot();
		robots.add(robot1);
		robots.add(robot2);
		robots.add(robot3);
		
		routeToF = new ArrayList<Direction>();
		routeToB = new ArrayList<Direction>();
		
		// route to Cavern f from the start
		routeToF.add(Direction.DOWN);
		routeToF.add(Direction.DOWN);
		routeToF.add(Direction.DOWN);
		routeToF.add(Direction.DOWN);
		routeToF.add(Direction.DOWN);
		routeToF.add(Direction.DOWN);
		routeToF.add(Direction.DOWN);
		routeToF.add(Direction.DOWN);
		routeToF.add(Direction.DOWN);
		routeToF.add(Direction.DOWN);
		routeToF.add(Direction.RIGHT);
		routeToF.add(Direction.DOWN);
		
		// route to Cavern b from the start
		routeToB.add(Direction.DOWN);
		routeToB.add(Direction.DOWN);
		routeToB.add(Direction.DOWN);
		routeToB.add(Direction.DOWN);
		routeToB.add(Direction.DOWN);
		routeToB.add(Direction.DOWN);
		routeToB.add(Direction.DOWN);
		routeToB.add(Direction.DOWN);
		routeToB.add(Direction.DOWN);
		routeToB.add(Direction.DOWN);
		routeToB.add(Direction.LEFT);
		routeToB.add(Direction.LEFT);
		routeToB.add(Direction.LEFT);
		routeToB.add(Direction.LEFT);
		routeToB.add(Direction.LEFT);
	}

	// current robot has the route
	@Test
	public void RobotHasRoute() {
		Set<Route> route = robot1.getKnownRoutes();
		
		assertTrue(route.contains(routeToF));
		assertTrue(route.contains(routeToB));
	}

	// robot behind the current robot has the route
	@Test
	public void Robot2HasTheRoute() {
		Set<Route> route = robot2.getKnownRoutes();
		
		assertTrue(route.contains(routeToF));
		assertTrue(route.contains(routeToB));
	}
	
	// robot 2 behind the current robot has the route
	@Test
	public void Robot3HasTheRoute() {
		Set<Route> route = robot3.getKnownRoutes();
		
		assertTrue(route.contains(routeToF));
		assertTrue(route.contains(routeToB));
	}
	
	// no robot has the route
	@Test
	public void NoRobotHasTheRoute() {
		Set<Route> route1 = robot1.getKnownRoutes();
		Set<Route> route2 = robot2.getKnownRoutes();
		Set<Route> route3 = robot3.getKnownRoutes();
		
		assertTrue(route1.contains(routeToF));
		assertTrue(route1.contains(routeToB));
		assertTrue(route2.contains(routeToF));
		assertTrue(route2.contains(routeToB));
		assertTrue(route3.contains(routeToF));
		assertTrue(route3.contains(routeToB));
	}
	
	//invert the route to F
	@Test
	public void invertRouteToFTests()
	{
		Route testRoute = new Route();
		testRoute.setRoute(routeToF);
		ArrayList<Direction> invertedRoute = testRoute.invertRoute(routeToF);
		
		Assert.assertEquals(invertedRoute.get(0), Direction.UP);
		Assert.assertEquals(invertedRoute.get(1), Direction.LEFT);
		Assert.assertEquals(invertedRoute.get(2), Direction.UP);
		Assert.assertEquals(invertedRoute.get(3), Direction.UP);
		Assert.assertEquals(invertedRoute.get(4), Direction.UP);
		Assert.assertEquals(invertedRoute.get(5), Direction.UP);
		Assert.assertEquals(invertedRoute.get(6), Direction.UP);
		Assert.assertEquals(invertedRoute.get(7), Direction.UP);
		Assert.assertEquals(invertedRoute.get(8), Direction.UP);
		Assert.assertEquals(invertedRoute.get(9), Direction.UP);
		Assert.assertEquals(invertedRoute.get(10), Direction.UP);
		Assert.assertEquals(invertedRoute.get(11), Direction.UP);
		Assert.assertEquals(invertedRoute.get(12), Direction.UP);
		
		
		
	}
	
	//invert the route to B
		@Test
		public void invertRouteToBTests()
		{
			Route testRoute = new Route();
			testRoute.setRoute(routeToB);
			ArrayList<Direction> invertedRoute = testRoute.invertRoute(routeToB);
			
			Assert.assertEquals(invertedRoute.get(0), Direction.RIGHT);
			Assert.assertEquals(invertedRoute.get(1), Direction.RIGHT);
			Assert.assertEquals(invertedRoute.get(2), Direction.RIGHT);
			Assert.assertEquals(invertedRoute.get(3), Direction.RIGHT);
			Assert.assertEquals(invertedRoute.get(4), Direction.RIGHT);
			Assert.assertEquals(invertedRoute.get(5), Direction.UP);
			Assert.assertEquals(invertedRoute.get(6), Direction.UP);
			Assert.assertEquals(invertedRoute.get(7), Direction.UP);
			Assert.assertEquals(invertedRoute.get(8), Direction.UP);
			Assert.assertEquals(invertedRoute.get(9), Direction.UP);
			Assert.assertEquals(invertedRoute.get(10), Direction.UP);
			Assert.assertEquals(invertedRoute.get(11), Direction.UP);
			Assert.assertEquals(invertedRoute.get(12), Direction.UP);
		}

}
