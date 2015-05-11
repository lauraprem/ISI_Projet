package model.graph.edge;

import model.graph.Node;
import model.graph.ground.Ground;
import model.graph.ground.GroundType;
import model.graph.label.impl.IntegerLabel;
import model.graph.label.impl.StringLabel;
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
        edge = new Edge(new Node(new StringLabel("test1"), new Point(1, 2)),
                new Node(new StringLabel("test2"), new Point(-1, -2)),
                new IntegerLabel(3),
                new Ground(GroundType.FLAT, 1.0));
    }

    @Test
    public void testUpdateGround() throws Exception {
        GroundType expected = GroundType.FLOODED;
        edge.updateGround();
        assertEquals(String.format("Ground type is %s should be %s", edge.getGround().getType(), expected), expected, edge.getGround().getType());
    }
}