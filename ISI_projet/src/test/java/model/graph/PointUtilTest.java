package model.graph;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexandre
 *         29/05/2015
 */
public class PointUtilTest {
    private Node n1;
    private Node n2;
    private Node delta;

    @Before
    public void setUp() throws Exception {
        n1 = new Node("Node 1", new Point(-5, 1));
        n2 = new Node("Node 2", new Point(-2, 1));
        delta = new Node("Delta", new Point(3, 0));
    }

    @Test
    public void testGetDistance() throws Exception {
        assertEquals((Double) 3.0, PointUtil.getDistance(n1, n2));
    }

    @Test
    public void testGetSquaredDistance() throws Exception {
        assertEquals((Double) 9.0, PointUtil.getSquaredDistance(n1, n2));
    }

    @Test
    public void testGetDelta() throws Exception {
        assertEquals(new Point(delta.getX(), delta.getY()), PointUtil.getDelta(n1, n2));
    }
}