package nl.sogyo.mancala.domain;

import org.junit.Assert;

class Kalaha extends Container{

    public Kalaha(Player owner){
        super(owner);
    }

    public void stealFromOpposite(){}
    public Container getOpposite(){
        return this;
    }

    public void playPit()  {
        //Assert.assertTrue(false);
        System.out.println("Error: Tried playing a kalaha");
    }

    // Ending the turn in a kalaha changes nothing about the turn state, so no need to implement any
    // method for ending the turn.
    public void passBeads(int beadsPassed){
        if (isOwnersTurn()){
            this.addBead();
            this.getNextContainer().passBeads(beadsPassed-1);
        } else {
            this.getNextContainer().passBeads(beadsPassed);
        }
    }

    public Kalaha findMyKalaha(){
        return this;
    }

    public boolean gameOverCheck(){
        return true;
    }
}