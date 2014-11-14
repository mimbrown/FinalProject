package Tests;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.util.ArrayList;

import maze.Direction;
import maze.Maze;
import maze.Mine;
import maze.Robot;

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
		routeToB.add(Direction.LEFT);
		routeToB.add(Direction.LEFT);
		routeToB.add(Direction.LEFT);
		routeToB.add(Direction.LEFT);
		routeToB.add(Direction.LEFT);

		// route to Cavern A from the start
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
		routeToD.add(Direction.LEFT);
		routeToD.add(Direction.LEFT);
		routeToD.add(Direction.LEFT);
		routeToD.add(Direction.UP);
		routeToD.add(Direction.UP);
		routeToD.add(Direction.UP);
		routeToD.add(Direction.UP);
		routeToD.add(Direction.UP);
		routeToD.add(Direction.UP);

		// route to Cavern d from the start
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
		routeToD.add(Direction.LEFT);
		routeToD.add(Direction.LEFT);
		routeToD.add(Direction.LEFT);
		routeToD.add(Direction.LEFT);
		routeToD.add(Direction.LEFT);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.RIGHT);
		routeToD.add(Direction.RIGHT);
		routeToD.add(Direction.DOWN);
		routeToD.add(Direction.RIGHT);
		routeToD.add(Direction.RIGHT);
		routeToD.add(Direction.UP);
		routeToD.add(Direction.UP);
		routeToD.add(Direction.UP);

	}

	// test to make sure the robot is finding the caverns it is supposed to, both the caverns
	// it's looking for and the caverns it happens to pass through.
	@Test
	public void testFindCaverns() {
		robot.updateGoal("f");
		assertTrue(robot.findRoute());
		assertTrue(robot.getKnownRoutes().contains(routeToF));
		robot.updateGoal("b");
		assertTrue(robot.findRoute());
		assertTrue(robot.getKnownRoutes().contains(routeToB));
		assertFalse(robot.getKnownRoutes().contains(routeToA));
		robot.updateGoal("d");
		assertTrue(robot.findRoute());
		assertTrue(robot.getKnownRoutes().contains(routeToD));
	}
	
	// test to make sure the robot does not find an inaccessible cavern.
	@Test
	public void testFindClosedCavern() {
		Mine mine2 = new Mine("MazeLayoutClosedCavern.csv");
		mine2.loadMine();
		robot = mine2.getRobots().get(0);
		robot.updateGoal("h");
		assertFalse(robot.findRoute());
		// robot should traverse the whole maze, so should know the route to a cavern on the
		// other side of the mine.
		assertTrue(robot.getKnownRoutes().contains(routeToA));
	}

}
