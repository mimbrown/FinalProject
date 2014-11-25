package Tests;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.util.ArrayList;

import maze.BadConfigFormatException;
import maze.Direction;
import maze.Maze;
import maze.Mine;
import maze.Robot;
import maze.Route;

import org.junit.BeforeClass;
import org.junit.Test;

public class FindRouteTests {

	/*
	 * This tests that the robot is finding the correct route to various caverns.
	 */
	private static Robot robot;
	private static Mine mine;
	private static ArrayList<Direction> routeToF;
	private static ArrayList<Direction> routeToB;
	private static ArrayList<Direction> routeToA;
	private static ArrayList<Direction> routeToD;

	@BeforeClass
	public static void Before() throws AWTException {
		mine = new Mine("MazeLayout.csv");
		try {
			mine.loadMine();	
		} catch(BadConfigFormatException e) {
			System.out.println(e.getMessage());
		}
		robot = mine.getRobots().get(0);

		routeToF = new ArrayList<Direction>();
		routeToB = new ArrayList<Direction>();
		routeToA = new ArrayList<Direction>();
		routeToD = new ArrayList<Direction>();

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
		routeToB.add(Direction.RIGHT);
		routeToB.add(Direction.DOWN);
		routeToB.add(Direction.DOWN);
		routeToB.add(Direction.DOWN);
		routeToB.add(Direction.RIGHT);
		routeToB.add(Direction.DOWN);
		routeToB.add(Direction.DOWN);
		routeToB.add(Direction.LEFT);
		routeToB.add(Direction.DOWN);
		routeToB.add(Direction.DOWN);
		routeToB.add(Direction.LEFT);
		routeToB.add(Direction.LEFT);
		routeToB.add(Direction.LEFT);
		routeToB.add(Direction.LEFT);
		routeToB.add(Direction.UP);
		routeToB.add(Direction.LEFT);
		routeToB.add(Direction.LEFT);
		routeToB.add(Direction.UP);
		routeToB.add(Direction.UP);
		routeToB.add(Direction.UP);
		routeToB.add(Direction.UP);

		// route to Cavern D from the start
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.RIGHT);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.RIGHT);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.LEFT);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.LEFT);
		routeToD.add(Direction.LEFT);
		routeToD.add(Direction.UP);
		routeToD.add(Direction.UP);
		routeToD.add(Direction.UP);

	}

	// test to make sure the robot is finding the caverns it is supposed to, both the caverns
	// it's looking for and the caverns it happens to pass through.
	@Test
	public void testFindCaverns() throws InterruptedException {
		Route f = new Route("f");
		f.setRoute(routeToF);
		robot.updateGoal("f");
		assertTrue(robot.findRoute(mine.getMaze()));
		ArrayList<Direction> testerf = robot.getKnownRoutes().get("f").getRoute();
		assertTrue(robot.getKnownRoutes().containsKey("f"));
		assertTrue(robot.getKnownRoutes().get("f").equals(f));
		
		
		Route b = new Route("b");
		b.setRoute(routeToB);
		robot.updateGoal("b");
		assertTrue(robot.findRoute(mine.getMaze()));
		ArrayList<Direction> testerb = robot.getKnownRoutes().get("b").getRoute();
		assertTrue(robot.getKnownRoutes().containsKey("b"));
		assertTrue(robot.getKnownRoutes().get("b").equals(b));
		assertFalse(robot.getKnownRoutes().containsKey(routeToA));

		Route d = new Route("d");
		d.setRoute(routeToD);
		robot.updateGoal("d");
		assertTrue(robot.findRoute(mine.getMaze()));
		ArrayList<Direction> testerd = robot.getKnownRoutes().get("d").getRoute();
		assertTrue(robot.getKnownRoutes().containsKey("d"));
		assertTrue(robot.getKnownRoutes().get("d").equals(d));
		assertFalse(robot.getKnownRoutes().containsKey(routeToA));
	}
	
	// test to make sure the robot does not find an inaccessible cavern.
	@Test
	public void testFindClosedCavern() throws InterruptedException {
		Mine mine2 = new Mine("MazeLayoutClosedCavern.csv");
		try {
			mine2.loadMine();
		} catch(BadConfigFormatException e) {}
		robot = mine2.getRobots().get(0);
		robot.updateGoal("h");
		assertFalse(robot.findRoute(mine2.getMaze()));
	}

}
