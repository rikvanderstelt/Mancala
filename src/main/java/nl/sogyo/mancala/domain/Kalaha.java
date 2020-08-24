package nl.sogyo.mancala.domain;

import org.junit.Assert;

class Kalaha extends Container{
    public Kalaha(Player owner){
        super();
        this.owner = owner;
        if(this.isOwnersTurn()){     // Only creates a second player the first time a kalaha is made
            Player player2 = new Player(false, "Player 2");
            owner.makeOpponents(player2);
            this.setNextContainer(new Pit(0,player2));
        }
    }

    public void stealFromOpposite(){}

    public void playPit()  {
        Assert.assertTrue(false);  // TODO: throw a proper exception
        System.out.println("Error: Tried playing a kalaha");
    }

    // Ending the turn in a kalaha changes nothing about the turn state, so no need to implement any
    // method for ending the turn.
    public void passBeads(int beadsPassed){
        if (isOwnersTurn()){
            this.addBead();
            this.getNextContainer().passBeads(beadsPassed-1);
        } else{
            this.getNextContainer().passBeads(beadsPassed);
        }
    }

    public boolean gameOverCheck(){
        return true;
    }
}