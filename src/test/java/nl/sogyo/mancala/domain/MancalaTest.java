package nl.sogyo.mancala.domain;


import org.junit.Assert;
import org.junit.Test;

public class MancalaTest {

    @Test
    public void testgetNumberOfBeads() {
        Pit pit1 = new Pit();
        Assert.assertEquals("Newly made pit should have 4 beads",4, pit1.getNumberOfBeads());
        Kalaha kalaha1 = new Kalaha();
        Assert.assertEquals("Newly made kalaha should have 0 beads",0, kalaha1.getNumberOfBeads());
    }

    @Test
    public void testGetNextPit(){
        Pit pit2 = new Pit();
        Pit pit1 = new Pit(pit2);
        Assert.assertEquals("A neighbouring pit must be able to be found ", pit1.getNextContainer(),pit2);
    }
}
