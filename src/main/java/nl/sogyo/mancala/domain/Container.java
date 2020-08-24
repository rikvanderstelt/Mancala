package nl.sogyo.mancala.domain;

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

    public abstract void stealFromOpposite();
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
