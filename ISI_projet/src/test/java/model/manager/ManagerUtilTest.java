package model.manager;

import model.graph.Node;
import model.graph.graph.impl.Graph;
import model.pathSearch.impl.Djikstra;
import model.robot.Robot;
import model.robot.specialized.RobotChenille;
import model.robot.specialized.RobotToutTerrain;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;
import static org.junit.Assert.*;

/**
 * @author Alexandre
 *         29/05/2015
 */
public class ManagerUtilTest {
    private Manager manager = new Manager();
    private Robot busy =  new RobotToutTerrain(new Graph(), new Node(), new Djikstra());
    @Before
    public void setUp() throws Exception {
        busy.setBusy(true);
        Integer rand = new Random().nextInt(50);
        for(int i = 0; i < rand; i++) {
            manager.addRobot(new RobotChenille(new Graph(), new Node(), new Djikstra()));
        }
        manager.addRobot(busy);
        rand = new Random().nextInt(50);
        for(int i = 0; i < rand; i++) {
            manager.addRobot(new RobotChenille(new Graph(), new Node(), new Djikstra()));
        }
    }

    @Test
    public void testGetUnoccupiedRobots() throws Exception {
        List<Robot> unoccupiedRobots = manager.getRobots();
        unoccupiedRobots.remove(busy);
        assertArrayEquals(unoccupiedRobots.toArray(), ManagerUtil.getUnoccupiedRobots(manager).toArray());
    }
}