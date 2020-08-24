package nl.sogyo.mancala.domain;

import org.junit.Assert;

class Pit extends Container {

    public Pit(){
        super();
        int i = 0;
        this.addBead(4);
        owner = new Player(true, "Player 1");
        this.setNextContainer(new Pit(i+1,owner));
        this.getNextContainer(13).setNextContainer(this);  // Completes the circle
        this.setOpposites();
    }

    public Pit(int i, Player owner){
        super();
        this.owner = owner;
        this.addBead(4);
        if (i<5) {
            this.setNextContainer(new Pit(i + 1,owner));
        } else if (i==5) {
            this.setNextContainer(new Kalaha(owner));
        }
    }

    public void setOpposites(){
        this.opposite = this.getNextContainer(12);
        for (int i = 1; i<13; i++){
            this.getNextContainer(i).opposite =this.getNextContainer(12-i);
        }
    }

    public void stealFromOpposite(){
        int beadsStolen = this.opposite.emptyPit();
        this.myKalaha().addBead(beadsStolen);
        this.emptyPit();
        this.myKalaha().addBead();
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
        int beadsPassed = this.emptyPit();
        this.getNextContainer().passBeads(beadsPassed);
    }

    public void endTurn(){
        if((this.getNumberOfBeads() == 1)&&(this.isOwnersTurn())){
            this.stealFromOpposite();
        }
        this.owner.flipSelf();
        this.owner.flipOpponent();
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

        System.out.println(this.owner + " has scored " + ownScore + " points");
        System.out.println(this.owner.getOpponent() + " has scored " + opponentScore + " points");

        if (ownScore > opponentScore) {
            System.out.println("The winner is: " + this.owner );
        } else if (ownScore < opponentScore){
            System.out.println("The winner is: " + this.owner.getOpponent());
        } else {
            System.out.println("The game has ended in a draw!");
        }
    }
}
