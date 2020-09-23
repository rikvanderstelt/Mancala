package nl.sogyo.mancala.domain;

abstract class Container{
    private int numberOfBeads;
    private Container nextContainer;
    private final Player owner;

    public Container(int numberOfBeads, Player owner, Container nextContainer, boolean isFirstContainer){
        this.numberOfBeads = numberOfBeads;
        this.owner = owner;
        this.nextContainer = nextContainer;

        if(isFirstContainer){
            this.getNextContainer(13).nextContainer = this;
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
    public abstract boolean allPitsEmptyCheck();
    public abstract Container getOpposite();
    public abstract Container getOpposite(int i);
    public abstract Kalaha findMyKalaha();

    public void passBeads(int beadsPassed) {
        this.addBead();
        if (beadsPassed > 1){
            this.getNextContainer().passBeads(beadsPassed-1);
        } else if (beadsPassed == 1){
            this.endTurn();
        }
    }

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
