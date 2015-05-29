package model.manager;

import model.graph.Node;
import model.graph.graph.impl.Graph;
import model.robot.Robot;
import model.robot.specialized.RobotChenille;
import model.robot.specialized.RobotToutTerrain;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;

/**
 * @author Alexandre
 *         29/05/2015
 */
public class ManagerUtilTest {
    private Manager manager = new Manager();
    private Robot busy =  new RobotToutTerrain(new Graph(), new Node(), null);
    @Before
    public void setUp() throws Exception {
        busy.setBusy(true);
        Integer rand = new Random().nextInt(50);
        for(int i = 0; i < rand; i++) {
            manager.addRobot(new RobotChenille(new Graph(), new Node(), null));
        }
        manager.addRobot(busy);
        rand = new Random().nextInt(50);
        for(int i = 0; i < rand; i++) {
            manager.addRobot(new RobotChenille(new Graph(), new Node(), null));
        }
    }

    @Test
    public void testGetUnoccupiedRobots() throws Exception {
        List<Robot> unoccupiedRobots = manager.getRobots();
        unoccupiedRobots.remove(busy);

    }
}