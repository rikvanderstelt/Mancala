package nl.sogyo.mancala.domain;

import org.junit.Test;
import org.junit.Assert;

import java.util.Arrays;


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

        Assert.assertEquals(mancala.getStonesForPit(2), 4);
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

    @Test
    public void testBugInPlayingFirstPits(){
        Mancala mancala1 = new MancalaImpl("Rik", "Tom");
        Mancala mancala2 = new MancalaImpl("Rik", "Tom");

        mancala1.playRecess(1);
        mancala2.playRecess(2);

        Assert.assertArrayEquals(mancala1.exportGameState(),new int[] {0,5,5,5,5,4,0,4,4,4,4,4,4,0,2});
        Assert.assertArrayEquals(mancala2.exportGameState(),new int[] {4,0,5,5,5,5,0,4,4,4,4,4,4,0,2});
    }

    @Test
    public void testIsEndOfGame(){
        Mancala mancala = new MancalaImpl("Rik", "Tom");
        int[] playOrder = {3,6,9,13,2,6,4,8,13,10,6,3,13,11,6,4,6,3,13,12,6,5,9,6,2,12,10,1,11,10,6,5,6,4,6,5,13,3,12,2,9,3};
        Assert.assertFalse(mancala.isEndOfGame());

        for(int index : playOrder){
            mancala.playRecess(index);
        }

        Assert.assertTrue(mancala.isEndOfGame());
    }

    @Test
    public void testGetWinnersName(){
        Mancala mancala = new MancalaImpl("Rik", "Tom");
        int[] playOrder = {3,6,9,13,2,6,4,8,13,10,6,3,13,11,6,4,6,3,13,12,6,5,9,6,2,12,10,1,11,10,6,5,6,4,6,5,13,3,12,2,9,3};
        Assert.assertEquals(mancala.getWinnersName(),null);
        
        for(int index : playOrder){
            mancala.playRecess(index);
        }

        Assert.assertEquals(mancala.getWinnersName(),"Rik");

    }
}
