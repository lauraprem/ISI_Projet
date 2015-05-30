package model.graph.edge;

import model.graph.Node;
import model.graph.Point;
import model.graph.ground.Ground;
import model.graph.ground.GroundType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexandre
 *         11/05/2015
 */
public class EdgeTest {
    private Edge edge;
    private Node source = new Node("test1", new Point(1, 2));
    private Node destination = new Node("test2", new Point(-1, -2));

    @Before
    public void setUp() throws Exception {
        edge = new Edge(source.clone(),
                destination.clone(),
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
    public void testEquals() throws Exception {
        Edge edge2 = new Edge(source.clone(),
                destination.clone(),
                3.0,
                new Ground(GroundType.FLAT, 1.0));
        assertTrue(edge2.equals(edge));

    }
}