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
        Player player1 = new Player(true);
        Pit pit2 = new Pit();
        Pit pit1 = new Pit(pit2,player1);
        Assert.assertEquals("A neighbouring pit must be able to be found ", pit1.getNextContainer(),pit2);
    }

    @Test
    public void testIsOwnersTurn(){
        Pit pit2 = new Pit();
        Player player1 = new Player(true);
        Pit pit1 = new Pit(pit2,player1);

        Assert.assertTrue(pit1.isOwnersTurn());
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
}
