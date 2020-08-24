package nl.sogyo.mancala.domain;

import org.junit.Assert;

abstract class Container{
    private int numberOfBeads;
    private Container nextContainer;
    protected Player owner;
    protected Container opposite;

    public Container(){
        numberOfBeads = 0;
    }

    public void addBead(){
        numberOfBeads++ ;
    }

    public void addBead(int n){
        numberOfBeads += n;
    }

    public int getNumberOfBeads(){
        return numberOfBeads;
    }

    public Container getNextContainer() {
        return nextContainer;
    }

    public Container getNextContainer(int numberOfSteps){
        Container output = this.getNextContainer();
        for (int j=0; j<(numberOfSteps -1); j++) {
            output = output.getNextContainer();
        }
        return output;
    }
    public Container getOpposite() {
        return opposite;
    }

    public abstract void emptyOpposite();
    public abstract void playPit();
    public abstract void passBeads(int beadsPassed);
    public abstract boolean gameOverCheck();

    public boolean isOwnersTurn() {
        return owner.isMyTurn();
    }

    public void setNextContainer(Container container){
        this.nextContainer = container;
    }

    public int emptyPit(){
        int output = numberOfBeads;
        numberOfBeads = 0;
        return output;
    }
}

class Pit extends Container {

    public Pit(){
        super();
        int i = 0;
        this.addBead(4);
        owner = new Player(true);
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

    public void emptyOpposite(){
        int beadsStolen = this.opposite.emptyPit();
        this.myKalaha().addBead(beadsStolen);
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
            this.emptyOpposite();
            this.emptyPit();
            this.myKalaha().addBead();
        }
        this.owner.flipSelf();
        this.owner.flipOpponent();
    }

   public boolean isGameOver(){
        if(isOwnersTurn()){
            boolean output = this.gameOverCheck();
            return output;
        } else{
            boolean output = this.getNextContainer(7).gameOverCheck();
            return output;
        }
    }

    public boolean gameOverCheck(){
        boolean output = false;
        if(this.getNumberOfBeads() == 0){
            output = this.getNextContainer().gameOverCheck();
        }
        return output;
    }

}

class Kalaha extends Container{
    public Kalaha(Player owner){
        super();
        this.owner = owner;
        if(this.isOwnersTurn()){     // Only creates a second player the first time a kalaha is made
            Player player2 = new Player(false);
            owner.makeOpponents(player2);
            this.setNextContainer(new Pit(0,player2));
        }
    }

    public void emptyOpposite(){}

    public void playPit() {System.out.println("Error: Tried playing a kalaha");}

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


class Player {
    private boolean myTurn;
    private Player opponent;

    public Player getOpponent(){
        return opponent;
    }

    public Player(boolean myTurn){
        this.myTurn = myTurn;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public void makeOpponents(Player player2){
        if(myTurn != player2.myTurn){
            player2.opponent = this;
            this.opponent = player2;
        } else {
            System.out.println("Error: two players in same turn state");
        }
    }

    public void flipOpponent(){
        opponent.flipSelf();
    }

    public void flipSelf() {
        myTurn = !myTurn;
    }
}