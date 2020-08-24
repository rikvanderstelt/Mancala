package nl.sogyo.mancala.domain;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {
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
    public void testContainerOwnership(){
        Pit pit1 = new Pit();

        Assert.assertTrue(pit1.isOwnersTurn() == pit1.getNextContainer(6).isOwnersTurn());
        Assert.assertTrue(pit1.getNextContainer(13).isOwnersTurn() != pit1.getNextContainer(4).isOwnersTurn());
        Assert.assertTrue(pit1.getNextContainer(10).isOwnersTurn() == pit1.getNextContainer(7).isOwnersTurn());
    }

    @Test
    public void testPlayerCreation(){
        Pit pit1 = new Pit();

        Assert.assertEquals(pit1.owner,pit1.getNextContainer(7).owner.getOpponent());
    }


}
