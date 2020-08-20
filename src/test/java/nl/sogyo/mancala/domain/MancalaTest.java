package nl.sogyo.mancala.domain;


import org.junit.Assert;
import org.junit.Test;
import java.util.stream.IntStream;

import java.util.ArrayList;

public class MancalaTest {

    @Test
    public void testgetNumberOfBeads() {
        Pit pit1 = new Pit();
        Player player1 = new Player(true);

        Assert.assertEquals("Newly made pit should have 4 beads",4, pit1.getNumberOfBeads());
        Kalaha kalaha1 = new Kalaha(player1);
        Assert.assertEquals("Newly made kalaha should have 0 beads",0, kalaha1.getNumberOfBeads());
    }

    @Test
    public void testGetNextPit(){
        Player player1 = new Player(true);
        Pit pit1 = new Pit();
        Assert.assertTrue("A neighbouring pit must be able to be found ", pit1.getNextContainer() instanceof Pit);
    }

    @Test
    public void testIsOwnersTurn(){
        Pit pit1 = new Pit();
        Player player1 = new Player(true);

        Assert.assertTrue(pit1.isOwnersTurn());
    }

    @Test
    public void testEmptyPit(){
        Pit pit1 = new Pit();

        int testbeads = pit1.emptyPit();

        Assert.assertEquals(4,testbeads);
        Assert.assertEquals(pit1.getNumberOfBeads(),0);
    }


    @Test
    public void testFlipSelf() {
        Player player1 = new Player(true);

        player1.flipSelf();

        Assert.assertFalse(player1.isMyTurn());
    }

    @Test
    public void testFlipOpponent() {
        Player player1 = new Player(true);
        Player player2 = new Player(false);
        player1.makeOpponents(player2);

        player2.flipOpponent();

        Assert.assertFalse(player1.isMyTurn());
    }

    @Test
    public void testChainBuilding(){
        Pit pit1 = new Pit();

        Assert.assertEquals(pit1,pit1
                .getNextContainer(14));
    }

    @Test
    public void testSettingUpKalahas(){
        Pit pit1 = new Pit();

        Assert.assertTrue(pit1.getNextContainer(6) instanceof Kalaha);
        Assert.assertTrue(pit1.getNextContainer(13) instanceof Kalaha);
    }

    @Test
    public void testContainerOwnership(){
        Pit pit1 = new Pit();

        Assert.assertTrue(pit1.isOwnersTurn() == pit1.getNextContainer(6).isOwnersTurn());
        Assert.assertTrue(pit1.getNextContainer(13).isOwnersTurn() != pit1.getNextContainer(4).isOwnersTurn());
        Assert.assertTrue(pit1.getNextContainer(10).isOwnersTurn() == pit1.getNextContainer(7).isOwnersTurn());
    }

    @Test
    public void testPitOpposites(){
        Pit pit1 = new Pit();

        Assert.assertEquals(pit1.getOpposite(), pit1.getNextContainer(12));
        Assert.assertEquals(pit1.getNextContainer(3).getOpposite(),pit1.getNextContainer(9));
    }

    @Test
    public void testEmptyOpposite(){
        Pit pit1 = new Pit();

        pit1.getNextContainer(8).emptyOpposite();

        Assert.assertEquals(0,pit1.getNextContainer(4).getNumberOfBeads());
        Assert.assertEquals(4,pit1.getNextContainer(6).getNumberOfBeads());
    }
}
