package nl.sogyo.mancala.domain;

import org.junit.Test;
import org.junit.Assert;


public class MancalaImplTest {

    @Test
    public void testGetPlayerName(){
        Mancala mancala = new MancalaImpl("Rik", "Tom");

        Assert.assertEquals(mancala.getPlayerName(1),"Rik");
        Assert.assertEquals(mancala.getPlayerName(2),"Tom");
    }

    @Test
    public void testSetPlayerName(){
        Mancala mancala = new MancalaImpl("Rik", "Tom");

        mancala.setPlayerName("Thomas",2);
        mancala.setPlayerName("Richard",1);

        Assert.assertEquals(mancala.getPlayerName(1),"Richard");
        Assert.assertEquals(mancala.getPlayerName(2),"Thomas");
    }

    @Test
    public void testIsToMovePlayer(){
        Mancala mancala = new MancalaImpl("Rik", "Tom");

        Assert.assertTrue(mancala.isToMovePlayer(1));
        Assert.assertFalse(mancala.isToMovePlayer(2));
    }

    @Test
    public void testGetStonesForPit(){
        Mancala mancala = new MancalaImpl("Rik", "Tom");

        Assert.assertEquals(mancala.getStonesForPit(1), 4);
        Assert.assertEquals(mancala.getStonesForPit(7),0);

    }

    @Test
    public void testExportGameState(){
        Mancala mancala = new MancalaImpl("Rik", "Tom");
        int[] beginState = {4,4,4,4,4,4,0,4,4,4,4,4,4,0,1};

        Assert.assertArrayEquals(mancala.exportGameState(),beginState);
    }


    @Test
    public void testPlayRecess(){
        Mancala mancala = new MancalaImpl("Rik", "Tom");
        int[] outputState = {4,4,0,5,5,0,2,5,5,5,5,4,4,0,2};

        mancala.playRecess(3);

        Assert.assertArrayEquals(outputState, mancala.playRecess(6));

    }
}
