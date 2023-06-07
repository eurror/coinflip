package coins.state;

import org.junit.jupiter.api.Test;

import java.util.BitSet;

import org.apache.commons.math3.util.CombinatoricsUtils;

import static org.junit.jupiter.api.Assertions.*;

class CoinsTest {

    private Coins state1 = new Coins(7, 3); // the original initial state

    private Coins state2; // the goal state
    {
        BitSet bs = new BitSet(7);
        bs.set(0, 7);
        state2 = new Coins(7, 3, bs);
    }

    @Test
    void testIllegalArgumentsForCoins(){
        assertThrows(IllegalArgumentException.class,()->{
            new Coins(0,0);
        });
    }

    @Test
    void testEquality(){
        assertTrue(state1.equals(state1));
    }

    @Test
    void testClone(){
        assertEquals(state1,state1.clone());
    }

    @Test
    void testIsGoal(){
        assertTrue(state2.isGoal());
    }

    @Test
    void testCanFlip(){
        assertFalse(state2.canFlip(state1.getCoins()));
    }

    @Test
    void testGenerateFlips(){
        assertArrayEquals(state1.getFlips().toArray(),Coins.generateFlips(7,3).toArray());
    }

    @Test
    void testToString(){
        assertEquals("O|O|O|O|O|O|O",state1.toString());
    }

    @Test
    void testHashcode(){
        assertNotEquals(state1.hashCode(),state2.hashCode());
    }

    @Test
    void testGenerateFlips2(){
        assertEquals((int)CombinatoricsUtils.binomialCoefficient(7,3),Coins.generateFlips(7,3).size());
    }


    @Test
    void testFlip(){
        Coins stateBefore = state1.clone();
        state1.flip(state1.getCoins());


        assertEquals(state1,stateBefore);
    }

}