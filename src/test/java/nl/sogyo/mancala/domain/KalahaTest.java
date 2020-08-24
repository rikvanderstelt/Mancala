package nl.sogyo.mancala.domain;

import org.junit.Assert;
import org.junit.Test;

public class KalahaTest {
    @Test
    public void testSettingUpKalahas(){
        Pit pit1 = new Pit();

        Assert.assertTrue(pit1.getNextContainer(6) instanceof Kalaha);
        Assert.assertTrue(pit1.getNextContainer(13) instanceof Kalaha);
    }
    @Test
    public void testPassOverKalahas() {
        Pit pit1 = new Pit();
        pit1.addBead(12);

        pit1.playPit();

        Assert.assertEquals(1,pit1.getNumberOfBeads());
        Assert.assertEquals(6,pit1.getNextContainer(3).getNumberOfBeads());
        Assert.assertEquals(1,pit1.getNextContainer(6).getNumberOfBeads());
        Assert.assertEquals(0,pit1.getNextContainer(13).getNumberOfBeads());
    }

    @Test
    public void testEndingTurnOnKalaha(){
        Pit pit1 = new Pit();

        pit1.getNextContainer(2).playPit();

        Assert.assertTrue(pit1.isOwnersTurn());
        Assert.assertTrue(!pit1.getNextContainer(12).isOwnersTurn());
    }


}
