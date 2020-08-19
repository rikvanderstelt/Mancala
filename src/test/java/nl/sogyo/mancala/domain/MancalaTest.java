package nl.sogyo.mancala.domain;


import org.junit.Assert;
import org.junit.Test;

public class MancalaTest {

    @Test
    public void testgetNumberOfBeads()
    {
        Pit pit1 = new Pit();
        Assert.assertEquals(4,pit1.getNumberOfBeads());
    }
}
