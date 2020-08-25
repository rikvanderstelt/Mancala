package nl.sogyo.mancala.domain;

abstract class Container{
    private int numberOfBeads;
    private Container nextContainer;
    private final Player owner;

    public Container(){  // Constructor for the first pit
        numberOfBeads = 4;
        owner = new Player();
        this.nextContainer = new Pit(1,owner);
        this.getNextContainer(13).nextContainer = this;  // Completes the circle
    }


    public Container(int i, Player owner) {  // Constructor for other Pits
        numberOfBeads = 4;
        this.owner = owner;
        if (i < 5) {
            this.nextContainer = new Pit(i + 1, owner);
        } else if (i == 5) {
            this.nextContainer = new Kalaha(owner);
        }
    }

    public Container(Player owner){ // Constructor for Kalahas
        numberOfBeads = 0;
        this.owner = owner;
        if(this.isOwnersTurn()){
            this.nextContainer = new Pit(0,owner.getOpponent());
        }
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

    public Player getOwner() {
        return owner;
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
    public abstract void stealFromOpposite();
    public abstract void playPit();
    public abstract void passBeads(int beadsPassed);
    public abstract boolean gameOverCheck();
    public abstract Container getOpposite();
    public abstract Kalaha findMyKalaha();

    public boolean isOwnersTurn() {
        return owner.isMyTurn();
    }

    public int emptyPit(){
        int output = numberOfBeads;
        numberOfBeads = 0;
        return output;
    }

    public void endTurn(){
    this.owner.switchTurn();
    }
}
