package nl.sogyo.mancala.domain;


import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertEquals(4,pit1.getNextContainer(13).getNumberOfBeads());
    }

    @Test
    public void testPlayerCreation(){
        Pit pit1 = new Pit();

        Assert.assertEquals(pit1.owner,pit1.getNextContainer(7).owner.getOpponent());
    }

    @Test
    public void testPassBeads(){
        Pit pit1 = new Pit();

        pit1.passBeads(3);

        Assert.assertEquals(5,pit1.getNumberOfBeads());
        Assert.assertEquals(5, pit1.getNextContainer().getNumberOfBeads());
        Assert.assertEquals(5, pit1.getNextContainer(2).getNumberOfBeads());
        Assert.assertEquals(4, pit1.getNextContainer(3).getNumberOfBeads());
    }
    @Test
    public void testPlayPit(){
        Pit pit1 = new Pit();

        pit1.playPit();

        Assert.assertEquals(0,pit1.getNumberOfBeads());
        Assert.assertEquals(5, pit1.getNextContainer().getNumberOfBeads());
        Assert.assertEquals(5, pit1.getNextContainer(4).getNumberOfBeads());
        Assert.assertEquals(4, pit1.getNextContainer(5).getNumberOfBeads());
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
    public void testEndingTurnOnPit(){  // No further special cases for the turn ending
        Pit pit1 = new Pit();

        pit1.getNextContainer(1).playPit();

        Assert.assertTrue(!pit1.isOwnersTurn());
        Assert.assertTrue(pit1.getNextContainer(12).isOwnersTurn());
    }

    @Test
    public void testEndingTurnOnKalaha(){
        Pit pit1 = new Pit();

        pit1.getNextContainer(2).playPit();

        Assert.assertTrue(pit1.isOwnersTurn());
        Assert.assertTrue(!pit1.getNextContainer(12).isOwnersTurn());
    }

    @Test
    public void testStealing(){ //Situation: pit 1 empty, pit 6 filled with 8, pit 13 with 4.
        Pit pit1 = new Pit();
        int i = pit1.emptyPit();
        pit1.getNextContainer(5).addBead(4);

        pit1.getNextContainer(5).playPit();

        Assert.assertEquals(0,pit1.getNumberOfBeads());
        Assert.assertEquals(0,pit1.getNextContainer(12).getNumberOfBeads());
        Assert.assertEquals(7,pit1.myKalaha().getNumberOfBeads());
        // Kalaha should have 1 dropped in it during the play, 5 from pit 13(1 is added by the play) and 1 from pit 1.
    }

    @Test
    public void testMultipleMoves(){
        Pit pit1 = new Pit();

        pit1.getNextContainer(2).playPit();
        pit1.getNextContainer(5).playPit();
        pit1.getNextContainer(8).playPit();
        pit1.getNextContainer(9).playPit();
        pit1.getNextContainer(4).playPit();

        Assert.assertEquals(3,pit1.myKalaha().getNumberOfBeads());
        Assert.assertEquals(1,pit1.getNextContainer(9).getNumberOfBeads());
        Assert.assertEquals(6,pit1.getNextContainer(12).getNumberOfBeads());
        Assert.assertEquals(5,pit1.getNumberOfBeads());
    }

    @Test
    public void testGameEndCheck(){
        Pit pit1 = new Pit();
        pit1.playPit();
        for(int i=7; i<13;i++){
            pit1.getNextContainer(i).emptyPit();
        }

        Assert.assertTrue("Game should be over",pit1.isGameOver());
    }

}
