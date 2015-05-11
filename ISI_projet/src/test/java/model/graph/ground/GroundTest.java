package model.graph.ground;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexandre
 *         11/05/2015
 */
public class GroundTest {
    private Ground ground;

    @Before
    public void setUp() throws Exception {
        ground = new Ground(GroundType.FLAT);
        ground.setChancesOfGettingFlooded(1.0);
    }

    @Test
    public void testUpdateTypeFlood() throws Exception {
        GroundType expected = GroundType.FLOODED;
        ground.updateType();
        assertEquals(String.format("Ground type is %s should be %s", ground.getType(), expected), expected, ground.getType());
    }

    @Test
    public void testUpdateTypeNotFlood() throws Exception {
        GroundType expected = GroundType.FLAT;
        ground.setChancesOfGettingFlooded(0.0);
        ground.updateType();
        assertEquals(String.format("Ground type is %s should be %s", ground.getType(), expected), expected, ground.getType());
    }

    @Test
    public void testSetChancesOfGettingFloodedBelow0() throws Exception {
        Double expected = 0.0;
        ground.setChancesOfGettingFlooded(-3.5);
        assertEquals(String.format("Chances of getting flooded are %s should be %s", ground.getChancesOfGettingFlooded(), expected), expected, ground.getChancesOfGettingFlooded());
    }

    @Test
    public void testSetChancesOfGettingFlooded() throws Exception {
        Double expected = 0.5;
        ground.setChancesOfGettingFlooded(expected);
        assertEquals(String.format("Chances of getting flooded are %s should be %s", ground.getChancesOfGettingFlooded(), expected), expected, ground.getChancesOfGettingFlooded());
    }

    @Test
    public void testSetChancesOfGettingFloodedAbove1() throws Exception {
        Double expected = 1.0;
        ground.setChancesOfGettingFlooded(10.0);
        assertEquals(String.format("Chances of getting flooded are %s should be %s", ground.getChancesOfGettingFlooded(), expected), expected, ground.getChancesOfGettingFlooded());

    }
}