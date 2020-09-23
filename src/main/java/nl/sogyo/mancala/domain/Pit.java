package nl.sogyo.mancala.domain;

class Pit extends Container {

    public Pit(Player owner){
        super(4, owner,new Pit(2,owner), true);
    }

    public Pit(int pitNumber, Player owner){
        super(4, owner, pitNumber<6 ? new Pit(pitNumber+1, owner) : new Kalaha(owner), false);
    }


    public Kalaha findMyKalaha(){
        return this.getNextContainer().findMyKalaha();
    }

    public void playPit(){
        if ((this.isOwnersTurn()) && (this.getNumberOfBeads() != 0)){
            int beadsPassed = this.emptyPit();
            this.getNextContainer().passBeads(beadsPassed);
        } else {
            System.out.println("Error: cannot play this pit.");
        }
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

    public Container getOpposite(int pitsBeforeKalaha){
        return this.getNextContainer().getOpposite(pitsBeforeKalaha+1);
    }

    public void stealFromOpposite(){
        int beadsStolen = this.getOpposite().emptyPit();
        this.findMyKalaha().addBead(beadsStolen);
        this.emptyPit();
        this.findMyKalaha().addBead();
    }

    public boolean isGameOver(){
        return this.findMyKalaha().isGameOver();
    }

    public boolean allPitsEmptyCheck(){
        if(this.getNumberOfBeads() == 0){
            return this.getNextContainer().allPitsEmptyCheck();
        } else {
            return false;
        }
    }


    // The final scores are returned in an array to make it possible to test for them.
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
