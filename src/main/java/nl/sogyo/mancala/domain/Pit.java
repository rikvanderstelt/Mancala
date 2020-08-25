package nl.sogyo.mancala.domain;

import org.junit.Assert;

class Pit extends Container {

    public Pit(){
        super();
    }

    public Pit(int i, Player owner){
        super(i,owner);
    }

    public Kalaha myKalaha(){
        int j = 0;

        for (int i=1;i<7;i++){             // Your kalaha is between 1 and 6 steps away from a pit
            if(this.getNextContainer(i) instanceof Kalaha){
                j=i;
            }
        }
        return (Kalaha) this.getNextContainer(j);
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
        Pit output = this;
        for (int i=1;i<7;i++){
            if (this.getNextContainer(i) instanceof Kalaha){
                output = (Pit) myKalaha().getNextContainer(i);
            }
        }
        return output;
    }

    public void stealFromOpposite(){
        int beadsStolen = this.getOpposite().emptyPit();
        this.myKalaha().addBead(beadsStolen);
        this.emptyPit();
        this.myKalaha().addBead();
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
            finalScores[0] = this.myKalaha().emptyPit();
            finalScores[1] = this.myKalaha().getNextContainer(7).emptyPit();

            this.printFinalScores(finalScores[0],finalScores[1]);
        }
        return finalScores;
    }
    // It feels wrong to pass the scores in as variables here, but putting the printing in the above function
    // would make it really long.
    public void printFinalScores(int ownScore, int opponentScore){

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
        //    System.out.println(this.getNextContainer(i).getNumberOfBeads());
        }
        return totalBeadNumber;
    }
}
