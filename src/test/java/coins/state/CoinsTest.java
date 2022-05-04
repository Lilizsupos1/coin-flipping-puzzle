package coins.state;

import org.apache.commons.math3.util.CombinatoricsUtils;
import org.junit.jupiter.api.Test;

import java.util.BitSet;
import java.util.List;

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
    void isGoalTest(){
        assertTrue(state2.isGoal());
        assertFalse(state1.isGoal());



    }

    @Test
    void getFlipsTest(){
        List<BitSet> flips = Coins.generateFlips(7,3);
        assertEquals(flips.size(),state1.getFlips().size());
    }

    @Test
    void flipTest(){
        BitSet bitSet = new BitSet(3);
        bitSet.set(3,7);
        state1 = new Coins(8,3);
        state1.flip(bitSet);
        assertEquals(bitSet,state1.getCoins());
        bitSet = new BitSet(5);
        bitSet.set(0,7);
        state1.flip(bitSet);
        assertEquals(bitSet,state2.getCoins());

    }

    @Test
    void generateFlipsTest(){
        long num = CombinatoricsUtils.binomialCoefficient(15,8);
        List<BitSet> flips = Coins.generateFlips(15,8);
        assertEquals((int) num,flips.size());
        num = CombinatoricsUtils.binomialCoefficient(3,1);
        flips = Coins.generateFlips(3,1);
        assertEquals((int) num,flips.size());
    }

    @Test
    void generateFlipsThrowingException() {
        assertThrows(IllegalArgumentException.class, () -> Coins.generateFlips(0, 7));
        assertThrows(IllegalArgumentException.class, () -> Coins.generateFlips(7, 0));
        assertThrows(IllegalArgumentException.class, () -> Coins.generateFlips(7, 10));
    }


}