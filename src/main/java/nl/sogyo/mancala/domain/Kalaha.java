package nl.sogyo.mancala.domain;

import org.junit.Assert;

class Kalaha extends Container{

    public Kalaha(Player owner){
        super(owner);
    }

    public void stealFromOpposite(){}

    public Container getOpposite(){
        return this.getNextContainer(7);
    }

    public Container getOpposite(int beadsBeforeKalaha) {
        return this.getNextContainer(beadsBeforeKalaha);
    }

    public void playPit()  {
        //Assert.assertTrue(false);
        System.out.println("Error: Tried playing a kalaha");
    }

    public void  passBeads(int beadsPassed){
        if (isOwnersTurn()){
            super.passBeads(beadsPassed);
        } else {
            this.getNextContainer().passBeads(beadsPassed);
        }
    }

    public void endTurn(){}

    public Kalaha findMyKalaha(){
        return this;
    }

    public boolean isGameOver(){
        if (this.isOwnersTurn()){
            return this.getNextContainer(8).emptyPitCheck();
        } else {
            return this.getNextContainer(1).emptyPitCheck();
        }
    }

    public boolean emptyPitCheck(){
        return true;
    }
}