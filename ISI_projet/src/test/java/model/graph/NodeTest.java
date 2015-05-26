package model.graph;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexandre
 *         11/05/2015
 */
public class NodeTest {
    private Node node;

    @Before
    public void setUp() throws Exception {
        node = new Node("test1", new Point(1, 2));
        node.setFireLevel(5);
    }

    @Test
    public void testDecreaseFireLevelBelow0() throws Exception {
        Integer expected = 0;
        node.decreaseFireLevel(8);
        Integer got = node.getFireLevel();
        assertEquals(String.format("Fire level is %s should be %s", got, expected), expected, got);
    }

    @Test
    public void testDecreaseFireLevelNormal() throws Exception {
        Integer expected = 3;
        node.decreaseFireLevel(2);
        Integer got = node.getFireLevel();
        assertEquals(String.format("Fire level is %s should be %s", got, expected), expected, got);
    }

    @Test
    public void testDecreaseFireLevelNegative() throws Exception {
        Integer expected = 10;
        node.decreaseFireLevel(-5);
        Integer got = node.getFireLevel();
        assertEquals(String.format("Fire level is %s should be %s", got, expected), expected, got);

    }

    @Test
    public void testIncreaseFireLevelNormal() throws Exception {
        Integer expected = 10;
        node.increaseFireLevel(5);
        Integer got = node.getFireLevel();
        assertEquals(String.format("Fire level is %s should be %s", got, expected), expected, got);
    }

    @Test
    public void testIncreaseFireLevelNegative() throws Exception {
        Integer expected = 1;
        node.increaseFireLevel(-4);
        Integer got = node.getFireLevel();
        assertEquals(String.format("Fire level is %s should be %s", got, expected), expected, got);
    }

    @Test
    public void testIncreaseFireLevelNegativeBelow0() throws Exception {
        Integer expected = 0;
        node.increaseFireLevel(-8);
        Integer got = node.getFireLevel();
        assertEquals(String.format("Fire level is %s should be %s", got, expected), expected, got);
    }

    @Test
    public void testIsOnFireFalse() throws Exception {
        Boolean expected = Boolean.FALSE;
        node.decreaseFireLevel(10);
        Boolean got = node.isOnFire();
        assertEquals(String.format("Fire level is %s should be %s", got, expected), expected, got);
    }

    @Test
    public void testIsOnFireTrue() throws Exception {
        Boolean expected = Boolean.TRUE;
        Boolean got = node.isOnFire();
        assertEquals(String.format("Fire level is %s should be %s", got, expected), expected, got);
    }
}