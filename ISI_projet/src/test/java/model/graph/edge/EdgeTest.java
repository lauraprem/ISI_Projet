package model.graph.edge;

import model.graph.Node;
import model.graph.ground.Ground;
import model.graph.ground.GroundType;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * @author Alexandre
 *         11/05/2015
 */
public class EdgeTest {
    private Edge edge;

    @Before
    public void setUp() throws Exception {
        edge = new Edge(new Node("test1", new Point(1, 2)),
                new Node("test2", new Point(-1, -2)),
                3.0,
                new Ground(GroundType.FLAT, 1.0));
    }

    @Test
    public void testUpdateGround() throws Exception {
        GroundType expected = GroundType.FLOODED;
        edge = new Edge(new Node("test1", new Point(1, 2)),
                new Node("test2", new Point(-1, -2)),
                3.0,
                new Ground(GroundType.FLAT, 1.0));
        edge.updateGround();
        assertEquals(String.format("Ground type is %s should be %s", edge.getGround().getType(), expected), expected, edge.getGround().getType());
    }

    @Test
    public void testIds() throws Exception {
        Long expected = edge.getId() + 1;
        edge = new Edge(new Node("test1", new Point(1, 2)),
                new Node("test2", new Point(-1, -2)),
                3.0,
                new Ground(GroundType.FLAT, 1.0));
        assertEquals(String.format("Id is %s should be %s", edge.getId(), expected), expected, edge.getId());
    }
}