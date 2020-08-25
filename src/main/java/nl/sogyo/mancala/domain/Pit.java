package nl.sogyo.mancala.domain;

import org.junit.Assert;

class Pit extends Container {

    public Pit(){
        super();
    }

    public Pit(int i, Player owner){
        super(i,owner);
    }

    public Kalaha findMyKalaha(){
        return this.getNextContainer().findMyKalaha();
    }

    public void passBeads(int beadsPassed){

        if (beadsPassed > 1){
            this.addBead();
            this.getNextContainer().passBeads(beadsPassed-1);
        } else if (beadsPassed == 1){
            this.addBead();
            this.endTurn();
        }
    }

    public void playPit(){
        Assert.assertTrue(this.isOwnersTurn());
        Assert.assertTrue(this.getNumberOfBeads() != 0);

        int beadsPassed = this.emptyPit();

        this.getNextContainer().passBeads(beadsPassed);
    }

    public void endTurn(){
        if((this.getNumberOfBeads() == 1)&&(this.isOwnersTurn())){
            this.stealFromOpposite();
        }
        super.endTurn();

    }

    public Container getOpposite(){
        return this.getOpposite(0);
    }

    public Container getOpposite(int beadsBeforeKalaha){
        return this.getNextContainer().getOpposite(beadsBeforeKalaha+1);
    }

    public void stealFromOpposite(){
        int beadsStolen = this.getOpposite().emptyPit();
        this.findMyKalaha().addBead(beadsStolen);
        this.emptyPit();
        this.findMyKalaha().addBead();
    }

    public boolean isGameOver(){
        if(isOwnersTurn()){
            return this.gameOverCheck();
        } else{
            return this.getNextContainer(7).gameOverCheck(); // Checks only for the player whose turn it is.
        }
    }

    public boolean gameOverCheck(){
        boolean output = false;
        if(this.getNumberOfBeads() == 0){
            output = this.getNextContainer().gameOverCheck();
        }
        return output;
    }
    // The final scores are returned in an array to make it easy to test them.
    public int[] gameEndCheck(){
        int[] finalScores = new int[2];

        if(isGameOver()){
            finalScores[0] = this.findMyKalaha().getNumberOfBeads();
            finalScores[1] = this.findMyKalaha().getNextContainer(7).getNumberOfBeads();

            this.printFinalScores();
        }
        return finalScores;
    }

    public void printFinalScores(){

        int ownScore = this.findMyKalaha().getNumberOfBeads();
        int opponentScore = this.findMyKalaha().getNextContainer(7).getNumberOfBeads();

        System.out.println(this.getOwner() + " has scored " + ownScore + " points");
        System.out.println(this.getOwner().getOpponent() + " has scored " + opponentScore + " points");

        if (ownScore > opponentScore) {
            System.out.println("The winner is: " + this.getOwner() );
        } else if (ownScore < opponentScore){
            System.out.println("The winner is: " + this.getOwner().getOpponent());
        } else {
            System.out.println("The game has ended in a draw!");
        }
    }

    public int totalBeadNumber(){  // This function was used for debugging the randomly played game.
        int totalBeadNumber = this.getNumberOfBeads();
        for(int i=1; i<14; i++){
            totalBeadNumber += this.getNextContainer(i).getNumberOfBeads();
        }
        return totalBeadNumber;
    }
}
