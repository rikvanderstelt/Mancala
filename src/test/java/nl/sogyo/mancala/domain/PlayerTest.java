package nl.sogyo.mancala.domain;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {
    @Test
    public void testFlipSelf() {
        Player player1 = new Player(true, "player1");

        player1.flipSelf();

        Assert.assertFalse(player1.isMyTurn());
    }

    @Test
    public void testFlipOpponent() {
        Player player1 = new Player(true,"player1");
        Player player2 = new Player(false,"player2");
        player1.makeOpponents(player2);

        player2.flipOpponent();

        Assert.assertFalse(player1.isMyTurn());
    }

    @Test
    public void testContainerOwnership(){
        Pit pit1 = new Pit();

        Assert.assertEquals(pit1.isOwnersTurn(), pit1.getNextContainer(6).isOwnersTurn());
        Assert.assertTrue(pit1.getNextContainer(13).isOwnersTurn() != pit1.getNextContainer(4).isOwnersTurn());
        Assert.assertEquals(pit1.getNextContainer(10).isOwnersTurn(),pit1.getNextContainer(7).isOwnersTurn());
    }

    @Test
    public void testPlayerCreation(){
        Pit pit1 = new Pit();

        Assert.assertEquals(pit1.getOwner(),pit1.getNextContainer(7).getOwner().getOpponent());
    }


}
